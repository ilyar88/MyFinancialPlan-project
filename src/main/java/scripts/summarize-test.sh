#!/usr/bin/env bash
# Summarize TestNG + a slice of the HTML report into the GitHub Actions job summary.

XML="target/surefire-reports/testng-results.xml"
FILE="target/surefire-reports/Suite/SanityTest.html"

{
  echo "### Test results"

  if [[ -f "$XML" ]]; then
    # Extract counters from the <testng-results ...> tag
    line=$(grep -o '<testng-results[^>]*>' "$XML" || true)
    total=$(echo "$line"   | grep -o 'total="[0-9]\+"'   | sed 's/[^0-9]//g')
    passed=$(echo "$line"  | grep -o 'passed="[0-9]\+"'  | sed 's/[^0-9]//g')
    failed=$(echo "$line"  | grep -o 'failed="[0-9]\+"'  | sed 's/[^0-9]//g')
    skipped=$(echo "$line" | grep -o 'skipped="[0-9]\+"' | sed 's/[^0-9]//g')
    ignored=$(echo "$line" | grep -o 'ignored="[0-9]\+"' | sed 's/[^0-9]//g')

    echo "| Total | Passed | Failed | Skipped | Ignored |"
    echo "|------:|------:|------:|-------:|-------:|"
    echo "| ${total:-0} | ${passed:-0} | ${failed:-0} | ${skipped:-0} | ${ignored:-0} |"
  else
    echo "No TestNG XML found at $XML"
  fi

  if [[ -f "$FILE" ]]; then
    echo "## SanityTest.html"
    echo "<details><summary>View SanityTest.html</summary>"
    # Print only the first <table> block (usually the summary)
    sed -n '/<table/,/<\/table>/p' "$FILE"
    echo "</details>"
  else
    echo "Not found: $FILE"
  fi
} >> "$GITHUB_STEP_SUMMARY"
