package utilities;

import java.io.File;
import java.io.PrintWriter;

public class K6ReportHelper {

    public static void generateHtmlFromString(String output) throws Exception {

        File outDir = new File("k6_results");
        File htmlFile = new File(outDir, "report.html");

        try (PrintWriter pw = new PrintWriter(htmlFile, "UTF-8")) {
            pw.println("<!DOCTYPE html>");
            pw.println("<html lang=\"en\">");
            pw.println("<head>");
            pw.println("  <meta charset=\"utf-8\" />");
            pw.println("  <title>k6 output</title>");
            pw.println("  <style>");
            pw.println("    body { font-family: Arial, sans-serif; margin: 20px; }");
            pw.println("    h1 { color: #333; }");
            pw.println("    pre { background: #f5f5f5; padding: 10px; border-radius: 4px;");
            pw.println("          white-space: pre-wrap; word-wrap: break-word; }");
            pw.println("  </style>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("  <pre>");
            pw.print(output);
            pw.println("  </pre>");
            pw.println("</body>");
            pw.println("</html>");
        }

        System.out.println("Filtered HTML report created at: " + htmlFile.getAbsolutePath());
    }
}
