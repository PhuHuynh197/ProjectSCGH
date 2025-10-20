// src/BadSecretExample.java
// ⚠️ File chỉ dùng để kiểm thử Gitleaks — KHÔNG chứa thông tin thật

public class BadSecretExample {

    // ✅ Fake GitHub Token (pattern: ghp_[A-Za-z0-9]{36})
    private static final String GITHUB_TOKEN = "ghp_1234567890abcdefABCDEF1234567890abcdef";

    // ✅ Fake AWS Access Key (pattern: AKIA[0-9A-Z]{16})
    private static final String AWS_ACCESS_KEY_ID = "AKIAIOSFODNN7EXAMPLE";

    // ✅ Fake Slack Token (pattern: xox[baprs]-)
    private static final String SLACK_TOKEN = "xoxb-123456789012-098765432109-AbCdEfGhIjKlMnOpQrStUvWx";

    // ✅ Fake Google API Key (pattern: AIza[0-9A-Za-z-_]{35})
    private static final String GOOGLE_API_KEY = "AIzaSyA-1234567890abcdefGhIjKlMnOpQrStUvWxYz1";

    // ✅ Fake database password
    private static final String DB_PASSWORD = "SuperSecret123!";
}
