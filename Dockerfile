FROM debian:bullseye
RUN apt-get update && apt-get install -y curl bash openssl
