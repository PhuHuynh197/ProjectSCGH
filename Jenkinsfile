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

        stage("Checkout Source") {
            steps {
                cleanWs()
                checkout scm
                bat 'if not exist security mkdir security'
            }
        }

        stage("Hadolint - Dockerfile Lint") {
            steps {
                bat '''
                docker run --rm -i hadolint/hadolint < Dockerfile > security/hadolint.txt || exit 0
                '''
            }
        }

        stage("Build Docker Image") {
            steps {
                bat '''
                docker build -t %IMAGE_NAME%:%IMAGE_TAG% .
                '''
            }
        }

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

        stage("Trivy - Config Scan") {
            steps {
                bat '''
                docker run --rm -v "%cd%:/workdir" aquasec/trivy:latest config /workdir ^
                  --format json ^
                  --output /workdir/security/trivy-config.json || exit 0
                '''
            }
        }

        stage("Trivy - Image Scan") {
            steps {
                bat '''
                docker run --rm ^
                  -v "%cd%:/workdir" ^
                  -v "\\\\.\\pipe\\docker_engine:\\\\.\\pipe\\docker_engine" ^
                  aquasec/trivy:latest image %IMAGE_NAME%:%IMAGE_TAG% ^
                  --severity HIGH,CRITICAL ^
                  --format json ^
                  --output /workdir/security/trivy-image.json || exit 0
                '''
            }
        }

        stage("Grype - Image Scan") {
            steps {
                bat '''
                docker run --rm ^
                  -v "\\\\.\\pipe\\docker_engine:\\\\.\\pipe\\docker_engine" ^
                  anchore/grype:latest %IMAGE_NAME%:%IMAGE_TAG% ^
                  -o json > security/grype.json || exit 0
                '''
            }
        }

        stage("Dockle - Best Practice") {
            steps {
                bat '''
                docker run --rm ^
                  -v "\\\\.\\pipe\\docker_engine:\\\\.\\pipe\\docker_engine" ^
                  goodwithtech/dockle:latest %IMAGE_NAME%:%IMAGE_TAG% ^
                  --format json > security/dockle.json || exit 0
                '''
            }
        }

        stage("Publish Security Artifacts") {
            steps {
                archiveArtifacts artifacts: "security/**", fingerprint: true
            }
        }

        stage("Fail On Critical Vulns") {
            steps {
                bat '''
                findstr /I "CRITICAL" security\\trivy-image.json security\\grype.json security\\dockle.json > nul
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
