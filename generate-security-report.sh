#!/bin/bash

OUTPUT_FILE="security-report.md"
echo "# 📋 Security Report (Generated on $(date))" > $OUTPUT_FILE

function extract_trivy_json() {
    local FILE=$1
    echo -e "\n## 🔍 Trivy Scan Report from $FILE" >> $OUTPUT_FILE
    jq -r '.Results[] | select(.Vulnerabilities != null) | .Target as $target | .Vulnerabilities[] | 
        "* **Target**: \($target)
        * **Package**: \(.PkgName)
        * **Version**: \(.InstalledVersion)
        * **CVE**: \(.VulnerabilityID)
        * **Severity**: \(.Severity)
        * **CVSS**: \(.CVSS."nvd".V3Score // "N/A")
        * **Link**: \(.PrimaryURL)
        "' $FILE >> $OUTPUT_FILE
}

function extract_snyk_json() {
    local FILE=$1
    echo -e "\n## 🧪 Snyk Scan Report from $FILE" >> $OUTPUT_FILE
    jq -r '.runs[0].results[] |
        "* **Rule ID**: \(.ruleId)
        * **Message**: \(.message.text)
        * **Severity**: \(.level)
        * **Location**: \(.locations[0].physicalLocation.artifactLocation.uri):\(.locations[0].physicalLocation.region.startLine)
        "' $FILE >> $OUTPUT_FILE
}

# Chạy extract nếu file tồn tại
[ -f trivy-fs.json ] && extract_trivy_json "trivy-fs.json"
[ -f trivy-image.json ] && extract_trivy_json "trivy-image.json"
[ -f snyk.sarif ] && extract_snyk_json "snyk.sarif"

echo -e "\n✅ Done. Generated $OUTPUT_FILE"
