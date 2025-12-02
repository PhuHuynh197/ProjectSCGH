pipeline {
    agent any

    environment {
        IMAGE_NAME = "projectscgh-devsecops"
        IMAGE_TAG  = "latest"
        REPORT_DIR = "security"
    }

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {

        // 0. CHECKOUT
        stage("Checkout Source") {
            steps {
                cleanWs()
                checkout scm
                bat 'if not exist security mkdir security'
            }
        }

        // 1. HADOLINT
        stage("Hadolint - Dockerfile Lint") {
            steps {
                bat '''
                docker run --rm -i hadolint/hadolint < Dockerfile > security/hadolint.txt || exit 0
                '''
            }
        }

        // 2. BUILD IMAGE
        stage("Build Docker Image") {
            steps {
                bat '''
                docker build -t %IMAGE_NAME%:%IMAGE_TAG% .
                '''
            }
        }

        // 3. GITLEAKS
        stage("Gitleaks - Secret Scan") {
            steps {
                bat '''
                docker run --rm -v "%cd%:/repo" zricethezav/gitleaks:latest detect ^
                  --source="/repo" ^
                  --report-format json ^
                  --report-path="/repo/security/gitleaks.json" ^
                  --no-banner || exit 0
                '''
            }
        }

        // 4. TRIVY - CONFIG
        stage("Trivy - Config Scan") {
            steps {
                bat '''
                docker run --rm -v "%cd%:/workdir" aquasec/trivy:latest config /workdir ^
                  --format json ^
                  --output /workdir/security/trivy-config.json || exit 0
                '''
            }
        }

        // 5. TRIVY - IMAGE
        stage("Trivy - Image Scan") {
            steps {
                bat '''
                docker run --rm -v "//var/run/docker.sock:/var/run/docker.sock" ^
                  aquasec/trivy:latest image %IMAGE_NAME%:%IMAGE_TAG% ^
                  --severity HIGH,CRITICAL ^
                  --format json ^
                  --output /workdir/security/trivy-image.json || exit 0
                '''
            }
        }

        // 6. GRYPE
        stage("Grype - Image Scan") {
            steps {
                bat '''
                docker run --rm -v "//var/run/docker.sock:/var/run/docker.sock" ^
                  anchore/grype:latest %IMAGE_NAME%:%IMAGE_TAG% ^
                  -o json > security/grype.json || exit 0
                '''
            }
        }

        // 7. DOCKLE
        stage("Dockle - Best Practice") {
            steps {
                bat '''
                docker run --rm -v "//var/run/docker.sock:/var/run/docker.sock" ^
                  goodwithtech/dockle:latest %IMAGE_NAME%:%IMAGE_TAG% ^
                  --format json > security/dockle.json || exit 0
                '''
            }
        }

        // 8. GENERATE REPORT
        stage("Generate Security Report") {
            steps {
                bat '''
                bash generate-security-report.sh
                '''
            }
        }

        // 9. ARCHIVE ARTIFACT
        stage("Publish Security Artifacts") {
            steps {
                archiveArtifacts artifacts: "security/**", fingerprint: true
            }
        }

        // 10. FAIL IF CRITICAL
        stage("Fail On Critical Vulns") {
            steps {
                bat '''
                findstr /S /I "CRITICAL" security\\* > nul
                if %errorlevel%==0 (
                    echo CRITICAL vulnerabilities found!
                    exit /b 1
                ) else (
                    echo No CRITICAL vulnerabilities.
                )
                '''
            }
        }
    }

    post {
        always {
            echo "Jenkins DevSecOps Pipeline Finished"
        }

        success {
            echo "Build SUCCESS"
        }

        failure {
            echo "Build FAILED due to SECURITY"
        }
    }
}
