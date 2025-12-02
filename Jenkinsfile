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
                sh "mkdir -p ${REPORT_DIR}"
            }
        }
        // 1. HADOLINT
        stage("Hadolint - Dockerfile Lint") {
            steps {
                sh '''
                docker run --rm -i hadolint/hadolint < Dockerfile \
                  > ${REPORT_DIR}/hadolint.txt || true
                '''
            }
        }
        // 2. BUILD IMAGE
        stage("Build Docker Image") {
            steps {
                sh '''
                docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                '''
            }
        }
        // 3. GITLEAKS
        stage("Gitleaks - Secret Scan") {
            steps {
                sh '''
                docker run --rm -v "$PWD:/repo" zricethezav/gitleaks:latest detect \
                  --source="/repo" \
                  --report-format json \
                  --report-path="/repo/${REPORT_DIR}/gitleaks.json" \
                  --no-banner || true
                '''
            }
        }
        // 4. TRIVY - CONFIG (Dockerfile)
        stage("Trivy - Config Scan") {
            steps {
                sh '''
                docker run --rm \
                  -v "$PWD:/workdir" \
                  aquasec/trivy:latest config /workdir \
                  --format json \
                  --output /workdir/${REPORT_DIR}/trivy-config.json || true
                '''
            }
        }
        // 5. TRIVY - IMAGE
        stage("Trivy - Image Scan") {
            steps {
                sh '''
                docker run --rm \
                  -v /var/run/docker.sock:/var/run/docker.sock \
                  aquasec/trivy:latest image ${IMAGE_NAME}:${IMAGE_TAG} \
                  --severity HIGH,CRITICAL \
                  --format json \
                  --output /workdir/${REPORT_DIR}/trivy-image.json || true
                '''
            }
        }
        // 6. GRYPE
        stage("Grype - Image Scan") {
            steps {
                sh '''
                docker run --rm \
                  -v /var/run/docker.sock:/var/run/docker.sock \
                  anchore/grype:${IMAGE_TAG} ${IMAGE_NAME}:${IMAGE_TAG} \
                  -o json > ${REPORT_DIR}/grype.json || true
                '''
            }
        }
        // 7. DOCKLE
        stage("Dockle - Best Practice") {
            steps {
                sh '''
                docker run --rm \
                  -v /var/run/docker.sock:/var/run/docker.sock \
                  goodwithtech/dockle:latest ${IMAGE_NAME}:${IMAGE_TAG} \
                  --format json > ${REPORT_DIR}/dockle.json || true
                '''
            }
        }

        // 8. GENERATE REPORT
        stage("Generate Security Report") {
            steps {
                sh '''
                chmod +x generate-security-report.sh
                ./generate-security-report.sh
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
                sh '''
                if grep -R "CRITICAL" security/; then
                  echo "CRITICAL vulnerabilities found!"
                  exit 1
                else
                  echo "No CRITICAL vulnerabilities."
                fi
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
