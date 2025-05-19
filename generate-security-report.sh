#!/bin/bash

OUTPUT_FILE="security-report.md"
echo "# 🛡️ Security Vulnerability Report (Generated: $(date -u))" > $OUTPUT_FILE
echo "" >> $OUTPUT_FILE

cvss_impact() {
  local score=$1
  if (( $(echo "$score >= 9" | bc -l) )); then
    echo "High / High / High"
  elif (( $(echo "$score >= 7" | bc -l) )); then
    echo "Medium / Medium / Medium"
  elif (( $(echo "$score >= 4" | bc -l) )); then
    echo "Low / Low / Low"
  else
    echo "None / None / None"
  fi
}

asvs_mapping() {
  local cve=$1
  case $cve in
    *12798*) echo "V5.3 - Logging and Encoding";;
    *24813*) echo "V1.4 - Secure Deployment";;
    *22102*) echo "V5.1 - Input Validation";;
    *38821*) echo "V2.1 - Authentication";;
    *22228*) echo "V3.2 - Credential Storage";;
    *4244*) echo "V5.2 - Data Protection";;
    *) echo "V0 - Unclassified";;
  esac
}

extract_trivy_json() {
    local FILE=$1
    echo -e "\n## 🔍 Trivy Scan Report from $FILE" >> $OUTPUT_FILE
    jq -c '.Results[] | select(.Vulnerabilities != null) | .Target as $target | .Vulnerabilities[]?' $FILE | while read -r vuln; do
        cve=$(echo "$vuln" | jq -r '.VulnerabilityID')
        pkg=$(echo "$vuln" | jq -r '.PkgName')
        version=$(echo "$vuln" | jq -r '.InstalledVersion')
        severity=$(echo "$vuln" | jq -r '.Severity')
        link=$(echo "$vuln" | jq -r '.PrimaryURL')
        cvss=$(echo "$vuln" | jq -r '.CVSS.nvd.V3Score // empty')
        cvss=${cvss:-"N/A"}
        cia=$(cvss_impact "$cvss")
        asvs=$(asvs_mapping "$cve")
        echo "- **CVE**: $cve
  - **Package**: $pkg
  - **Version**: $version
  - **Severity**: $severity
  - **CVSS**: $cvss
  - **CIA Impact**: $cia
  - **OWASP ASVS**: $asvs
  - **Reference**: [$link]($link)
" >> $OUTPUT_FILE
    done
}

extract_snyk_sarif() {
    local FILE=$1
    echo -e "\n## 🧪 Snyk Scan Report from $FILE" >> $OUTPUT_FILE
    jq -c '.runs[0].results[]?' $FILE | while read -r result; do
        ruleId=$(echo "$result" | jq -r '.ruleId')
        message=$(echo "$result" | jq -r '.message.text')
        severity=$(echo "$result" | jq -r '.level')
        location=$(echo "$result" | jq -r '.locations[0].physicalLocation.artifactLocation.uri')
        line=$(echo "$result" | jq -r '.locations[0].physicalLocation.region.startLine')
        echo "- **Rule ID**: $ruleId
  - **Message**: $message
  - **Severity**: $severity
  - **Location**: $location:$line
" >> $OUTPUT_FILE
    done
}

[ -f trivy-fs.json ] && extract_trivy_json "trivy-fs.json"
[ -f trivy-image.json ] && extract_trivy_json "trivy-image.json"
[ -f snyk.sarif ] && extract_snyk_sarif "snyk.sarif"

echo -e "\n✅ Done. Generated $OUTPUT_FILE"
