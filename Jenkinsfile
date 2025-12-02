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
       9. FAIL IF CRITICAL
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
