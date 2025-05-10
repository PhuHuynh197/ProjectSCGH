# Dockerfile dùng để test Trivy scan sạch
FROM alpine:3.18

RUN apk add --no-cache curl

CMD ["sh"]
