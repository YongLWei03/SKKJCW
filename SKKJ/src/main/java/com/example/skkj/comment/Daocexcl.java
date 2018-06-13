package com.example.skkj.comment;



import com.example.skkj.entity.Temperature;
import org.apache.poi.hssf.usermodel.*;

import java.util.List;

public class Daocexcl {

    public HSSFWorkbook export(String[] excelHeader, List<Temperature> temperature) {
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("温度记录表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
            //设置指定列的列宽，256 * 50这种写法是因为width参数单位是单个字符的256分之一
            sheet.setColumnWidth(cell.getColumnIndex(), 100 * 50);
        }
        for (int i = 0; i < temperature.size(); i++) {
            row = sheet.createRow(i + 1);
            Temperature temperature1 = temperature.get(i);
            String contactA = temperature1.getContactA();
            String contactB = temperature1.getContactA();
            String contactC = temperature1.getContactA();
            String contactD = temperature1.getContactA();
            String contactE = temperature1.getContactA();
            String contactF = temperature1.getContactA();
            row.createCell(0).setCellValue(temperature1.getTime());
            row.createCell(1).setCellValue(temperature1.getInA() + "/" + temperature1.getInAxh());
            row.createCell(2).setCellValue(temperature1.getInB() + "/" + temperature1.getInBxh());
            row.createCell(3).setCellValue(temperature1.getInC() + "/" + temperature1.getInCxh());
            row.createCell(4).setCellValue(temperature1.getOutA() + "/" + temperature1.getOutAxh());
            row.createCell(5).setCellValue(temperature1.getOutB() + "/" + temperature1.getOutBxh());
            row.createCell(6).setCellValue(temperature1.getOutC() + "/" + temperature1.getOutCxh());
            if(contactA != null && !"".equals(contactA)){
                row.createCell(7).setCellValue(contactA + "/" + temperature1.getContactAxh());
            }
            if(contactB != null && !"".equals(contactB)){
                row.createCell(8).setCellValue(contactB + "/" + temperature1.getContactBxh());
            }
            if(contactC != null && !"".equals(contactC)){
                row.createCell(9).setCellValue(contactC + "/" + temperature1.getContactCxh());
            }
            if(contactD != null && !"".equals(contactD)){
                row.createCell(10).setCellValue(contactD + "/" + temperature1.getContactDxh());
            }
            if(contactE != null && !"".equals(contactE)){
                row.createCell(11).setCellValue(contactE + "/" + temperature1.getContactExh());
            }
            if(contactF != null && !"".equals(contactF)){
                row.createCell(12).setCellValue(contactF + "/" + temperature1.getContactFxh());
            }
            Integer type = temperature1.getType();
            if(contactF != null && !"".equals(contactF)){
                if (type == 1) {
                    row.createCell(13).setCellValue("正常/" + temperature1.getOutCxh());
                } else if (type == 2) {
                    row.createCell(13).setCellValue("报警/" + temperature1.getOutCxh());
                } else {
                    row.createCell(13).setCellValue("异常/" + temperature1.getOutCxh());
                }
            }else {
                if (type == 1) {
                    row.createCell(7).setCellValue("正常/" + temperature1.getOutCxh());
                } else if (type == 2) {
                    row.createCell(7).setCellValue("报警/" + temperature1.getOutCxh());
                } else {
                    row.createCell(7).setCellValue("异常/" + temperature1.getOutCxh());
                }
            }


        }
        return wb;
    }
}
