package com.verifone.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;

public class DataDrivenUtils {

    private static HSSFSheet ExcelWSheet;
    private static HSSFWorkbook ExcelWBook;
    private static HSSFCell Cell;

    /**
     * This method implements Data Driven mechanism
     *
     * @return SheetName
     * @author Fred Shniper
     * // 	 * @param FulePath
     */

    public static Object[][] getExcelData(String FilePath, String SheetName) throws Exception {
        String[][] tabArray = null;

        try {
            // Access the required testLog data sheet
            FileInputStream ExcelFile = new FileInputStream(FilePath);
            ExcelWBook = new HSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            int totalNoOfCols = ExcelWSheet.getRow(0).getLastCellNum();
            int totalNoOfRows = ExcelWSheet.getLastRowNum();

            tabArray = new String[totalNoOfRows][totalNoOfCols];

            for (int i = 1; i <= totalNoOfRows; i++) {
                for (int j = 0; j < totalNoOfCols; j++) {
                    Cell = ExcelWSheet.getRow(i).getCell(j);
                    int cel_Type = Cell.getCellType();
                    switch (cel_Type) {
                        case HSSFCell.CELL_TYPE_NUMERIC: // 0
                            if (DateUtil.isCellDateFormatted(Cell)) {
                                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                                tabArray[i - 1][j] = df.format(Cell.getDateCellValue());
                            } else {
                                tabArray[i - 1][j] = String.format("%d", (long) Cell.getNumericCellValue());
                            }
                            break;
                        case HSSFCell.CELL_TYPE_STRING: // 1
                            tabArray[i - 1][j] = Cell.getStringCellValue();
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return tabArray;
    }

    /**
     * This method return number of rows in csv file
     *
     * @return SheetName
     * @author Giora Tovim
     * // 	 * @param FulePath
     */
    public static int getRowNumberExcelData(String FilePath, String SheetName) throws Exception {
        String[][] tabArray = null;
        Integer totalNoOfRow = 0;

        // Access the required testLog data sheet
        FileInputStream ExcelFile = new FileInputStream(FilePath);
        ExcelWBook = new HSSFWorkbook(ExcelFile);
        ExcelWSheet = ExcelWBook.getSheet(SheetName);

        int totalNoOfCols = ExcelWSheet.getRow(0).getLastCellNum();
        int totalNoOfRows = ExcelWSheet.getLastRowNum();
        System.out.println("Number of Rows is: " + totalNoOfRows);

        return totalNoOfRows;
    }


    public static HashMap<String, String> getMapFromStr(String headers) {
        HashMap<String, String> headersMap = new HashMap<String, String>();
        if(headers == null) {
            return headersMap;
        }

        String[] keyValue = getListFrromString(headers);
        for (String pair : keyValue) {
            String[] entry = pair.split(":");
            headersMap.put(entry[0].trim(), entry[1].trim());
            if (pair.contains("https:")) {
                headersMap.put(entry[0].trim(), entry[1].trim() + ":" + entry[2]);
            }
        }

        return headersMap;
    }

    public static String[] getListFrromString(String headers) {
        headers = StringUtils.substringBetween(headers, "{", "}");
        return headers.split(",");
    }


}