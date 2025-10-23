# INTENTIONALLY BAD Dockerfile (for testing security scanners)
FROM debian:bullseye

LABEL maintainer="root@example.com"

# 1) install many packages as root (including sshd & sudo) -> increases attack surface
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
      curl wget ca-certificates openssh-server sudo gnupg apt-transport-https \
    && rm -rf /var/lib/apt/lists/*

# 2) create a "developer" user but we continue to run as root later anyway
RUN useradd -m -s /bin/bash developer && \
    echo "developer:Password123!" | chpasswd && \
    usermod -aG sudo developer

# 3) copy entire repo (may include secrets if present)
WORKDIR /app
COPY . /app

# 4) create a bad secret file in image (simulate leaked secret)
RUN printf "DB_PASSWORD=super-secret-password\nAPI_KEY=ak_test_1234" > /app/.env

# 5) expose SSH port and app port - leaving SSH running as root
EXPOSE 22 8080

# 6) DON'T switch to non-root user (image will run as root)
USER root

# 7) no HEALTHCHECK, no read-only filesystem, starts sshd (dangerous)
CMD ["/usr/sbin/sshd", "-D"]
