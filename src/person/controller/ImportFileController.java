/*
 * This project is created by ShodowBoy all copyright reserved.
 * No distribute is approved unless authorized.
 * Any problem please contact youli9056@126.com
 */
package person.controller;

import hrs.HrsMain;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.contact.entity.Department;
import org.contact.entity.Incumbency;
import org.contact.entity.Project;
import person.entity.Person;

/**
 *
 * @author youli
 */
public class ImportFileController extends Task<Boolean> {

    String fileUrl;

    public ImportFileController(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    protected Boolean call() throws Exception {
        try {
            parseXLS(fileUrl);
            HrsMain.getHrsMain().hideModalMessage();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void parseXLS(String fileUrl) {
       /** for test
        * for(int i = 1; i < 10000; i++){
            updateProgress(i,10000);
            System.out.println(i);
        }
        * */
        File file = new File(fileUrl);
        try {
            Workbook workbook = null;
            String ext = fileUrl.substring(fileUrl.lastIndexOf(".")+1, fileUrl.length());
            if(ext.equals("xls"))
                   workbook = new HSSFWorkbook(new FileInputStream(file));
            else if(ext.equals("xslx")){
                workbook = new  XSSFWorkbook(new FileInputStream(file));
            }
                
            Sheet sheet = workbook.getSheetAt(0);
            int lines = sheet.getLastRowNum();
            for (int rowNum = 1; rowNum <= lines; rowNum++) {
                Row row = sheet.getRow(rowNum);
                Cell cell = null;

                Project poj = new Project();
                Department depart = new Department();
                Incumbency incb = new Incumbency();
                Person person = new Person();
                
                int colNum = 0;
                //计算标志
                cell = row.getCell(colNum++);

                //序号
                
                //项目类型
                
                //项目名称
                
                //专业组
                
                //任职
                
                //姓名
                
                //员工编号
                
                //出生年月
                
                //部门
                
                //职称
                
                //学历
                
                if(rowNum%5==0)
                    updateProgress(rowNum,lines);
            }
        } catch (IOException ex) {
            Logger.getLogger(ImportFileController.class.getName()).log(Level.SEVERE, fileUrl + "do not found", ex);
        }
        
    }
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private Date getDateValue(HSSFCell cell) {
        //如果为空值，如何处理
        Date date = new Date();
        try {
            date = cell.getDateCellValue();
        } catch (Exception e) {

        }
        return date;
    }

    private double getDoubleValue(HSSFCell cell) {
        double res = 0;
        try {
            res = cell.getNumericCellValue();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return res;
    }

    private String getStringValue(HSSFCell cell) {
        String res = "";
        try {
            res = cell.getRichStringCellValue().toString();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return res;
    }

    private Date genDateByString(String datestr) {
        Date dt;
        try {
            dt = sdf.parse(datestr);
        } catch (ParseException e) {
            dt = new Date();
        }
        return dt;
    }

}
