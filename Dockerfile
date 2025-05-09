# Dockerfile dùng để test Trivy scan 
FROM alpine:3.12

# Cài thử một package dễ có lỗ hổng
RUN apk add --no-cache curl
