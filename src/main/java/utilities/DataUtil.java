package utilities;

import java.io.FileInputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtil {

	public static Object[][] getTestData(String filePath, String testName) throws Exception {
	    List<Object[]> dataList = new ArrayList<>();

	    try (FileInputStream fis = new FileInputStream(filePath);
	         Workbook wb = new XSSFWorkbook(fis)) {

	        Sheet sheet = wb.getSheet("Data");

	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) continue; // skip the first header

	            String rowTestName = getCellValue(row.getCell(0));
	            if (!rowTestName.equalsIgnoreCase(testName)) continue; // Search for the calling test method

	            List<Object> params = new ArrayList<>();

	            for (int c = 1; c < row.getLastCellNum(); c++) {
	                String value = getCellValue(row.getCell(c));

	                if (value.isBlank()) break;  // Break when reaching an empty cell.

	                // Convert to ${ENV}
	                if (value.startsWith("${") && value.endsWith("}")) {
	                    String envKey = value.substring(2, value.length() - 1);
	                    String envValue = System.getenv(envKey);
	                    value = envValue != null ? envValue : value;
	                }
	                // Convert CSV to String[]
	                params.add(value.contains(",") ? value.split(",") : value);
	            }
	            dataList.add(params.toArray());
	        }
	    }
	    return dataList.toArray(new Object[0][]);
	}

	private static String getCellValue(Cell cell) {
	    return cell == null ? "" : cell.toString().trim();
	}
}


