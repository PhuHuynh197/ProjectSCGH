pipeline {
    agent any

    stages {
        // JENKINS SẼ TỰ ĐỘNG CHECKOUT MÃ NGUỒN dựa trên cấu hình SCM của Job.
        // Stage này được giữ lại chỉ để xác nhận hoặc thêm các thao tác chuẩn bị khác nếu cần.
        stage('Checkout Code') {
            steps {
                echo 'Source code checked out by Jenkins SCM configuration.'
                // Lệnh 'git branch: "main", url: "..."' đã được xóa.
            }
        }

        stage('Build') {
            steps {
                sh 'echo "Building project..."'
                // Thêm lệnh build thực tế của bạn ở đây (ví dụ: mvn clean install)
            }
        }

        stage('Security Scan CI (Grype)') {
            steps {
                sh '''
                    echo "Running SCA scan with Grype"
                    // Chạy quét SCA (Security Component Analysis) trên mã nguồn
                    grype . -o table || true
                '''
            }
        }

        stage('Container Build') {
            steps {
                sh '''
                    echo "Build Docker image"
                    // Build image Docker, đảm bảo bạn có file Dockerfile ở thư mục gốc
                    docker build -t projectscgh:latest .
                '''
            }
        }

        stage('Container Security Scan (Trivy + Dockle)') {
            steps {
                sh '''
                    echo "Running Trivy scan..."
                    // Quét Image bằng Trivy cho các lỗ hổng HIGH/CRITICAL
                    trivy image --severity HIGH,CRITICAL projectscgh:latest || true

                    echo "Running Dockle scan..."
                    // Quét Image bằng Dockle cho các thực tiễn bảo mật tốt nhất
                    dockle projectscgh:latest || true
                '''
            }
        }
    }

    post {
        always {
            echo "CI Pipeline Finished"
        }
    }
}
