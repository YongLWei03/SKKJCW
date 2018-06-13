package com.example.skkj.test;


import com.example.skkj.entity.Equipment;
import com.example.skkj.entity.Temperature;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class ReadExcel {

    public List<Equipment> loadScoreInfo(InputStream is) throws IOException{
        List<Equipment> temp = new LinkedList<Equipment>();
        //装载流
        POIFSFileSystem fs = new POIFSFileSystem(is);
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new HSSFWorkbook(fs);
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        //对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if(r.getRowNum()<2){
                continue;
            }
            //创建实体类
            Equipment equi=new Equipment();
            //取出当前行第1个单元格数据，并封装在info实体stuName属性上

            if(r.getCell(0).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                String deviceNames = r.getCell(0).getStringCellValue();
                String deviceName = deviceNames.split("-")[1];
                equi.setDeviceName(deviceName.substring(5));
            }else {
                String deviceNames = r.getCell(0).getStringCellValue();
                String deviceName = deviceNames.split("-")[1];
                equi.setDeviceName(deviceName.substring(5));
            }
            if(r.getCell(1).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String deviceSbIds = r.getCell(1).getStringCellValue();
                String deviceSbId = deviceSbIds.split("-")[1];
                equi.setDeviceSbId(deviceSbId.substring(5));
            }else {
                String deviceSbIds = r.getCell(1).getStringCellValue();
                String deviceSbId = deviceSbIds.split("-")[1];
                equi.setDeviceSbId(deviceSbId.substring(5));
            }
            if(r.getCell(2).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                String codes = r.getCell(2).getStringCellValue();
                String[] code = codes.split(";");
                equi.setCode(code[0]);
            }else {
                String codes = r.getCell(2).getStringCellValue();
                String[] code = codes.split(";");
                equi.setCode(code[0]);
            }
            if(r.getCell(3).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                equi.setNumberDevices(r.getCell(3).getStringCellValue());
            }else {
                equi.setNumberDevices(r.getCell(3).getStringCellValue());
            }
            if(r.getCell(4).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                equi.setEptId(r.getCell(4).getStringCellValue());
            }else {
                equi.setEptId(r.getCell(4).getStringCellValue());
            }
            if(r.getCell(5).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                equi.setDeviceType(r.getCell(5).getStringCellValue());
            }else {
                equi.setDeviceType(r.getCell(5).getStringCellValue());
            }
            if(r.getCell(6).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                equi.setIsRoot(r.getCell(6).getStringCellValue());
            }else {
                equi.setIsRoot(r.getCell(6).getStringCellValue());
            }

            temp.add(equi);
        }
        is.close();
        return temp;
    }


     /**
          * @Author ZhouNan
          * @Description  温度上传
          * @params
          * @Date 2018/5/2 0002  14:46
          */
    public List<Temperature> loadScoreInfoBytemp(InputStream is, String dernum) throws IOException{
        List<Temperature> temp = new LinkedList<Temperature>();
        //装载流
        POIFSFileSystem fs = new POIFSFileSystem(is);
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new HSSFWorkbook(fs);
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        //对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if(r.getRowNum()<1){
                continue;
            }
            //创建实体类
            Temperature temps = new Temperature();
            //取出当前行第1个单元格数据，并封装在info实体stuName属性上

            if(r.getCell(0).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                temps.setTime(r.getCell(0).getStringCellValue());
            }else {
                temps.setTime(r.getCell(0).getStringCellValue());
            }
            if(r.getCell(1).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(1).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInA(str[i]);
                    }else {
                        temps.setInAxh(str[i]);
                    }
                }

            }else {
                String inaxh = r.getCell(1).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInA(str[i]);
                    }else {
                        temps.setInAxh(str[i]);
                    }
                }
            }
            if(r.getCell(2).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(2).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInB(str[i]);
                    }else {
                        temps.setInBxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(2).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInB(str[i]);
                    }else {
                        temps.setInBxh(str[i]);
                    }
                }
            }
            if(r.getCell(3).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(3).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInC(str[i]);
                    }else {
                        temps.setInCxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(3).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInC(str[i]);
                    }else {
                        temps.setInCxh(str[i]);
                    }
                }
            }
            if(r.getCell(4).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(4).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutA(str[i]);
                    }else {
                        temps.setOutAxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(4).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutA(str[i]);
                    }else {
                        temps.setOutAxh(str[i]);
                    }
                }
            }
            if(r.getCell(5).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(5).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutB(str[i]);
                    }else {
                        temps.setOutBxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(5).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutB(str[i]);
                    }else {
                        temps.setOutBxh(str[i]);
                    }
                }
            }
            if(r.getCell(6).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(6).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutC(str[i]);
                    }else {
                        temps.setOutCxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(6).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutC(str[i]);
                    }else {
                        temps.setOutCxh(str[i]);
                    }
                }
            }
            if(r.getCell(7).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(7).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        String type = str[i];
                        if(type == "正常" || "正常".equals(type)){
                            temps.setType(1);
                        }else if(type == "报警" || "报警".equals(type)){
                            temps.setType(2);
                        }else {
                            temps.setType(3);
                        }
                    }else {
                        temps.setAntSignal(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(7).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        String type = str[i];
                        if(type == "正常" || "正常".equals(type)){
                            temps.setType(1);
                        }else if(type == "报警" || "报警".equals(type)){
                            temps.setType(2);
                        }else {
                            temps.setType(3);
                        }
                    }else {
                        temps.setAntSignal(str[i]);
                    }
                }
            }
//            System.out.println(temps);
            temps.setDeviceNumber(dernum);
            temps.setObject("temperatures2018032301");
            temp.add(temps);
        }
        is.close();
        return temp;
    }


    /**
     * @Author ZhouNan
     * @Description  温度上传
     * @params
     * @Date 2018/5/2 0002  14:46
     */
    public List<Temperature> loadScoreInfoBytempSr(InputStream is,String dernum) throws IOException{
        List<Temperature> temp = new LinkedList<Temperature>();
        //装载流
        POIFSFileSystem fs = new POIFSFileSystem(is);
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new HSSFWorkbook(fs);
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        //对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if(r.getRowNum()<1){
                continue;
            }
            //创建实体类
            Temperature temps = new Temperature();
            //取出当前行第1个单元格数据，并封装在info实体stuName属性上

            if(r.getCell(0).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                temps.setTime(r.getCell(0).getStringCellValue());
            }else {
                temps.setTime(r.getCell(0).getStringCellValue());
            }
            if(r.getCell(1).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(1).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInA(str[i]);
                    }else {
                        temps.setInAxh(str[i]);
                    }
                }

            }else {
                String inaxh = r.getCell(1).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInA(str[i]);
                    }else {
                        temps.setInAxh(str[i]);
                    }
                }
            }
            if(r.getCell(2).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(2).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInB(str[i]);
                    }else {
                        temps.setInBxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(2).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInB(str[i]);
                    }else {
                        temps.setInBxh(str[i]);
                    }
                }
            }
            if(r.getCell(3).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(3).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInC(str[i]);
                    }else {
                        temps.setInCxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(3).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setInC(str[i]);
                    }else {
                        temps.setInCxh(str[i]);
                    }
                }
            }
            if(r.getCell(4).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(4).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutA(str[i]);
                    }else {
                        temps.setOutAxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(4).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutA(str[i]);
                    }else {
                        temps.setOutAxh(str[i]);
                    }
                }
            }
            if(r.getCell(5).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(5).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutB(str[i]);
                    }else {
                        temps.setOutBxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(5).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutB(str[i]);
                    }else {
                        temps.setOutBxh(str[i]);
                    }
                }
            }
            if(r.getCell(6).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(6).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutC(str[i]);
                    }else {
                        temps.setOutCxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(6).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setOutC(str[i]);
                    }else {
                        temps.setOutCxh(str[i]);
                    }
                }
            }
            if(r.getCell(7).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(7).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactA(str[i]);
                    }else {
                        temps.setContactAxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(7).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactA(str[i]);
                    }else {
                        temps.setContactAxh(str[i]);
                    }
                }
            }
            if(r.getCell(8).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(8).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactB(str[i]);
                    }else {
                        temps.setContactBxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(8).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactB(str[i]);
                    }else {
                        temps.setContactBxh(str[i]);
                    }
                }
            }

            if(r.getCell(9).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(9).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactC(str[i]);
                    }else {
                        temps.setContactCxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(9).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactC(str[i]);
                    }else {
                        temps.setContactCxh(str[i]);
                    }
                }
            }
            if(r.getCell(10).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(10).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactD(str[i]);
                    }else {
                        temps.setContactDxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(10).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactD(str[i]);
                    }else {
                        temps.setContactDxh(str[i]);
                    }
                }
            }
            if(r.getCell(11).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(11).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactE(str[i]);
                    }else {
                        temps.setContactExh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(11).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactE(str[i]);
                    }else {
                        temps.setContactExh(str[i]);
                    }
                }
            }
            if(r.getCell(12).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(12).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactF(str[i]);
                    }else {
                        temps.setContactFxh(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(12).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        temps.setContactF(str[i]);
                    }else {
                        temps.setContactFxh(str[i]);
                    }
                }
            }


//            ---
            if(r.getCell(13).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
                String inaxh = r.getCell(13).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        String type = str[i];
                        if(type == "正常" || "正常".equals(type)){
                            temps.setType(1);
                        }else if(type == "报警" || "报警".equals(type)){
                            temps.setType(2);
                        }else {
                            temps.setType(3);
                        }
                    }else {
                        temps.setAntSignal(str[i]);
                    }
                }
            }else {
                String inaxh = r.getCell(13).getStringCellValue();
                String[] str = inaxh.split("/");
                for (int i = 0; i < str.length; i++) {
                    if(i==0){
                        String type = str[i];
                        if(type == "正常" || "正常".equals(type)){
                            temps.setType(1);
                        }else if(type == "报警" || "报警".equals(type)){
                            temps.setType(2);
                        }else {
                            temps.setType(3);
                        }
                    }else {
                        temps.setAntSignal(str[i]);
                    }
                }
            }
//            System.out.println(temps);
            temps.setDeviceNumber(dernum);
            temps.setObject("temperatures2018032301");
            temp.add(temps);
        }
        is.close();
        return temp;
    }


    public List<Equipment> loadScoreInfosq(InputStream is,String subId) throws IOException{
        List<Equipment> temp = new LinkedList<Equipment>();
        //装载流
        POIFSFileSystem fs = new POIFSFileSystem(is);
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        Workbook wb0 = new HSSFWorkbook(fs);
        //获取Excel文档中的第一个表单
        Sheet sht0 = wb0.getSheetAt(0);
        //对Sheet中的每一行进行迭代
        for (Row r : sht0) {
            //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
            if(r.getRowNum()<2){
                continue;
            }
            //创建实体类
            Equipment equi=new Equipment();
            //取出当前行第1个单元格数据，并封装在info实体stuName属性上

            if(r.getCell(0).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                String deviceSbIds = r.getCell(0).getStringCellValue();
                String deviceSbId = deviceSbIds.split("-")[1];
                equi.setDeviceSbId(deviceSbId.substring(4));
            }else {
                String deviceSbIds = r.getCell(0).getStringCellValue();
                String deviceSbId = deviceSbIds.split("-")[1];
                equi.setDeviceSbId(deviceSbId.substring(4));
            }
            if(r.getCell(1).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
              equi.setHeadOfEquipment(r.getCell(1).getStringCellValue());
            }else {
                equi.setHeadOfEquipment(r.getCell(1).getStringCellValue());
            }
            if(r.getCell(2).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                equi.setPhone(r.getCell(2).getStringCellValue());
            }else {
                equi.setPhone(r.getCell(2).getStringCellValue());
            }
            if(r.getCell(3).CELL_TYPE_NUMERIC == Cell.CELL_TYPE_NUMERIC){
                r.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                equi.setEquipmentxi(r.getCell(3).getStringCellValue());
            }else {
                equi.setEquipmentxi(r.getCell(3).getStringCellValue());
            }
            equi.setSubstationId(subId);
            temp.add(equi);
        }
        is.close();
        return temp;
    }

    public static void main(String[] args) {

    }
}
