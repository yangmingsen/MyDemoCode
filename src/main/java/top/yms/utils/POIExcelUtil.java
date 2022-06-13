package top.yms.utils;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class POIExcelUtil {

    private static Logger logger = Logger.getLogger(POIExcelUtil.class); // 日志打印类

    private static final String HSSF = "org.apache.poi.hssf.usermodel.HSSFWorkbook"; //xls
    private static final String XSSF = "org.apache.poi.xssf.usermodel.XSSFWorkbook"; //xlsx
    private static final String SXSSF = "org.apache.poi.xssf.usermodel.SXSSFWorkbook"; //

    public static Workbook createExcel(List<List<String>> data) throws Exception {
        return doCreateExcel(data, XSSF);
    }

    public static Workbook createExcelByType(List<List<String>> data, String type) throws Exception {
        if ("HSSF".equals(type)) {
            return doCreateExcel(data, HSSF);
        } else if ("SXSSF".equals(type)) {
            return doCreateExcel(data, SXSSF);
        } else {
            return doCreateExcel(data, XSSF);
        }
    }

    public static void writeToLocal(Workbook workbook, String path) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static Workbook doCreateExcel(List<List<String>> data, String type) throws Exception {
        //根据 type 参数生成工作簿实例对象
        Workbook workbook = (Workbook) Class.forName(type).newInstance();
        //这里还可以指定 sheet 的名字
        //Sheet sheet = workbook.createSheet("sheetName");
        Sheet sheet = workbook.createSheet();

        int rowLen = data.size();
        for (int i = 0; i < rowLen; i++) {
            Row row = sheet.createRow(i);

            List<String> colsData = data.get(i);
            int colLen = colsData.size();
            for (int j = 0; j < colLen; j++) {
                Cell cell = row.createCell(j);

                String value = colsData.get(j);
                cell.setCellValue(value);
            }
        }
        return workbook;
    }

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     * @param inputStream 读取文件的输入流
     * @param fileName 文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook = null;
        if (fileName.endsWith(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileName.endsWith(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    //获取单元格各类型值，返回字符串类型
    public static String getCellValueByCell(Cell cell) {
        //判断是否为null或空串
        if (cell==null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        CellType cellType = cell.getCellType();
        if (cellType == CellType.NUMERIC) { // 数字
            short format = cell.getCellStyle().getDataFormat();
            if (DateUtil.isCellDateFormatted(cell)) {
                SimpleDateFormat sdf = null;
                //System.out.println("cell.getCellStyle().getDataFormat()="+cell.getCellStyle().getDataFormat());
                if (format == 20 || format == 32) {
                    sdf = new SimpleDateFormat("HH:mm");
                } else if (format == 14 || format == 31 || format == 57 || format == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    // sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(value);
                    cellValue = sdf.format(date);
                }else {// 日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
                try {
                    cellValue = sdf.format(cell.getDateCellValue());// 日期
                } catch (Exception e) {
                    try {
                        throw new Exception("exception on get date data !".concat(e.toString()));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }finally{
                    sdf = null;
                }
            }  else {
                BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                cellValue = bd.toPlainString();// 数值 这种用BigDecimal包装再获取plainString，可以防止获取到科学计数值
            }
        } else if (cellType == CellType.STRING) { // 字符串
            cellValue =  cell.getStringCellValue();
        } else if (cellType == CellType.FORMULA) {// 公式
            cellValue =  cell.getCellFormula();
        } else if (cellType == CellType.BLANK || cellType == CellType._NONE) {// 空值
            cellValue = " ";
        }else if (cellType == CellType.BOOLEAN) {// 故障
            cellValue =  cell.getBooleanCellValue()+"";
        } else if (cellType == CellType.ERROR) {// 故障
            cellValue = " ";
        } else {
            cellValue = " ";
        }

        return cellValue;
    }

    /**
    //获取单元格各类型值，返回字符串类型
    public static String getCellValueByCell(Cell cell) {
        //判断是否为null或空串
        if (cell==null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        int cellType=cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                short format = cell.getCellStyle().getDataFormat();
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;
                    //System.out.println("cell.getCellStyle().getDataFormat()="+cell.getCellStyle().getDataFormat());
                    if (format == 20 || format == 32) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else if (format == 14 || format == 31 || format == 57 || format == 58) {
                        // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                       // sdf = new SimpleDateFormat("yyyy-MM-dd");
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        double value = cell.getNumericCellValue();
                        Date date = org.apache.poi.ss.usermodel.DateUtil
                                .getJavaDate(value);
                        cellValue = sdf.format(date);
                    }else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }
                    try {
                        cellValue = sdf.format(cell.getDateCellValue());// 日期
                    } catch (Exception e) {
                        try {
                            throw new Exception("exception on get date data !".concat(e.toString()));
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }finally{
                        sdf = null;
                    }
                }  else {
                    BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                    cellValue = bd.toPlainString();// 数值 这种用BigDecimal包装再获取plainString，可以防止获取到科学计数值
                }
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue()+"";;
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                cellValue = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: // 故障
                cellValue = "ERROR VALUE";
                break;
            default:
                cellValue = "UNKNOW VALUE";
                break;
        }
        return cellValue;
    } */

}
