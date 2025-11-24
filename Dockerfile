# INTENTIONALLY BAD DOCKERFILE
# For security scanning demonstration (Trivy, Dockle)

FROM debian:bullseye

LABEL maintainer="root@example.com"

# Install many unnecessary packages including sshd + sudo
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
      curl wget ca-certificates openssh-server sudo gnupg apt-transport-https && \
    rm -rf /var/lib/apt/lists/*

# Create user but still run as root
RUN useradd -m -s /bin/bash developer && \
    echo "developer:Password123!" | chpasswd && \
    usermod -aG sudo developer

WORKDIR /app

# Copy whole repo (may contain secrets)
COPY . /app

# Store secrets inside image (intentionally insecure)
RUN printf "DB_PASSWORD=super-secret-password\nAPI_KEY=ak_test_1234" > /app/.env

# Expose SSH port
EXPOSE 22 8080

# Continue running as root
USER root

# No healthcheck, start sshd
CMD ["/usr/sbin/sshd", "-D"]
