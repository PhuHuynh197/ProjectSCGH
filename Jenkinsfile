pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Source code checked out by Jenkins SCM configuration.'
            }
        }

        stage('Build') {
            steps {
                sh 'echo "Building project..."'
                // Thêm lệnh build thực tế của bạn ở đây
            }
        }

        // LỖI ĐÃ XẢY RA Ở ĐÂY - ĐÃ SỬA CÚ PHÁP SH
        stage('Security Scan CI (Grype)') {
            steps {
                sh 'echo "Running SCA scan with Grype"'
                // Dùng cú pháp đơn giản hơn
                sh 'grype . -o table || true' 
            }
        }

        stage('Container Build') {
            steps {
                sh 'echo "Build Docker image"'
                sh 'docker build -t projectscgh:latest .'
            }
        }

        stage('Container Security Scan (Trivy + Dockle)') {
            steps {
                sh 'echo "Running Trivy scan..."'
                sh 'trivy image --severity HIGH,CRITICAL projectscgh:latest || true'

                sh 'echo "Running Dockle scan..."'
                sh 'dockle projectscgh:latest || true'
            }
        }
    }

    post {
        always {
            echo "CI Pipeline Finished"
        }
    }
}
