// src/BadSecretExample.java
// File dùng để test Gitleaks (fake secrets — KHÔNG PHẢI SECRET THẬT)

public class BadSecretExample {
    // Fake GitHub token pattern (will be detected)
    private static final String GITHUB_TOKEN = "ghp_FAKESECRETEXAMPLE1234567890abcDEF";

    // Fake AWS key (will be detected)
    private static final String AWS_KEY = "AKIAIOSFODNN7EXAMPLE";

    // Fake Slack token (will be detected)
    private static final String SLACK_TOKEN = "xoxb-123456789012-0987654321-AbCdEfGhIjKlMnOpQrStUvWx";

    // Fake db password string
    private static final String DB_PASS = "SuperSecret123!";
}
