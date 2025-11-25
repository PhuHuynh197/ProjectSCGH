pipeline {
    // 1. Cấu hình Agent sử dụng Docker
    // Lệnh này khởi chạy một container 'docker:latest' để chạy toàn bộ Pipeline.
    agent {
        docker {
            image 'docker:latest'
            // Option này cho phép container vừa tạo kết nối với Docker daemon của máy chủ (Host)
            // để nó có thể chạy các lệnh Docker (như build, run)
            args '-v /var/run/docker.sock:/var/run/docker.sock' 
        }
    }
    
    // Các biến môi trường cần thiết, ví dụ: định nghĩa tên image
    environment {
        GITHUB_CREDENTIALS_ID = 'github-ci-cd-token'
    }

    stages {
        // Stage 1: Kiểm tra mã nguồn (Tự động bởi Jenkins)
        stage('Checkout Code') {
            steps {
                // Lệnh này đã được đơn giản hóa vì Jenkins tự động lấy mã nguồn
                echo 'Source code checked out by Jenkins SCM configuration.'
            }
        }

        // Stage 2: Build Project (Giả định là Java/Maven, Node/npm, hoặc chỉ đơn giản là in ra)
        stage('Build') {
            steps {
                echo "Building project..."
                // Thay thế bằng lệnh build thực tế của dự án của bạn (ví dụ: 'mvn clean package')
            }
        }

        // Stage 3: Quét thành phần bảo mật (SCA) bằng Grype
        // Grype cần mã nguồn để quét, và nó được chạy bên trong Agent Docker đã cấu hình
        stage('Security Scan CI (Grype)') {
            steps {
                echo "Running SCA scan with Grype"
                // Grype được giả định đã được cài sẵn hoặc sử dụng container Grype
                sh 'grype . -o table || true' 
            }
        }

        // Stage 4: Build Container Docker
        // Lệnh 'docker build' sẽ hoạt động nhờ cấu hình agent DinD ở trên
        stage('Container Build') {
            steps {
                echo "Building Docker image ${env.DOCKER_IMAGE_NAME}"
                sh "docker build -t ${env.DOCKER_IMAGE_NAME} ."
            }
        }

        // Stage 5: Quét bảo mật Container bằng Trivy và Dockle
        stage('Container Security Scan (Trivy + Dockle)') {
            steps {
                echo "Running Trivy scan for vulnerabilities..."
                // Trivy cần quyền truy cập vào Docker Daemon để kéo image, đã có sẵn nhờ DinD
                sh "trivy image --severity HIGH,CRITICAL ${env.DOCKER_IMAGE_NAME} || true"

                echo "Running Dockle scan for best practices..."
                // Dockle cũng cần tương tác với Docker image
                sh "dockle ${env.DOCKER_IMAGE_NAME} || true"
            }
        }
    }

    // Hành động sau khi Pipeline hoàn tất
    post {
        always {
            echo "CI Pipeline Finished"
        }
        failure {
            echo "Pipeline failed! Please check the console output."
        }
        success {
            echo "Pipeline succeeded! Ready for Deployment."
        }
    }
}
