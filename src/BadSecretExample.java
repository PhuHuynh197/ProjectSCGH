// src/BadSecretExample.java
// ⚠️ File chỉ dùng để kiểm thử Gitleaks — KHÔNG chứa thông tin thật

public class BadSecretExample {

    // ✅ GitHub PAT (36 ký tự sau "ghp_")
    private static final String GITHUB_TOKEN = "ghp_1234567890abcdefABCDEF1234567890abcdef12";

    // ✅ AWS Access Key ID
    private static final String AWS_ACCESS_KEY_ID = "AKIAIOSFODNN7EXAMPLE";

    // ✅ Slack Bot Token
    private static final String SLACK_TOKEN = "xoxb-123456789012-098765432109-AbCdEfGhIjKlMnOpQrStUvWx";

    // ✅ Google API Key
    private static final String GOOGLE_API_KEY = "AIzaSyA-1234567890abcdefGhIjKlMnOpQrStUvWxYz1";

    // ✅ Fake DB Password
    private static final String DB_PASSWORD = "SuperSecret123!";
}
