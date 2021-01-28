package helper;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadExternalData {
    static String excelSheetPath = Constants.OBJECT_REPO_PATH;
    static File file = new File(excelSheetPath);

    static Map<String, String> map1 = new HashMap<>();
    static Map<String, String> map2 = new HashMap<>();
    static Map<String, String> map3 = new HashMap<>();

    public static Map<String, String> readObjectRepo(String elementName) {

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet("home");
            XSSFCell elementNameCell, findByCell, valueCell;
            int rowCount = sheet.getLastRowNum() + 1;
            for (int i = 0; i < rowCount; i++) {
                elementNameCell = sheet.getRow(i).getCell(0);
                if (elementNameCell.getStringCellValue().equals(elementName)) {
                    int cellRowIndex = elementNameCell.getRowIndex();
                    findByCell = sheet.getRow(cellRowIndex).getCell(1);
                    valueCell = sheet.getRow(cellRowIndex).getCell(2);
                    map1.put(elementNameCell.getStringCellValue(), findByCell.getStringCellValue());
                    map2.put(elementNameCell.getStringCellValue(), valueCell.getStringCellValue());
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }
        map3.put(map1.get(elementName), map2.get(elementName));
        return map3;
    }
}
