package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.CommandInformation;
import com.example.skkj.mapper.CommandInformationMapper;
import com.example.skkj.service.CommandInformationServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commandInformationServer")
public class CommandInformationServerImpl  implements CommandInformationServer {

    @Autowired
    private CommandInformationMapper commandInformationMapper;

    @Override
    public String updateByDeviceNumber(CommandInformation commandInformation) throws Exception {
        return commandInformationMapper.updateByDeviceNumber(commandInformation)>0?"true":"false";
    }

    @Override
    public void select(HttpServletRequest request) throws Exception {
        //每页显示多少条
        int pageSize = 10;
        //当前页
        int currentPage = 1;

        Map<String,Object> map = new HashMap<String, Object>();
        //当前页
        String dqpagestr = request.getParameter("currentPage");
        String deviceNumber = request.getParameter("deviceNumber");
        String substationId = request.getParameter("substationId");
        String substationImage = request.getParameter("substationImage");
        String currentPageSb = request.getParameter("currentPageSb");
        if(null!=dqpagestr && !"".equals(dqpagestr)){
            currentPage = Integer.parseInt(dqpagestr);
        }
        map.put("start", ((currentPage-1)*pageSize));
//			map.put("end", pageSize*currentPage);
        map.put("pageSize", pageSize);
        if(deviceNumber != null && !"".equals(deviceNumber)){
            map.put("deviceNumber", deviceNumber);
            request.setAttribute("deviceNumber", deviceNumber);
        }
        if(substationId != null && !"".equals(substationId)){
            request.setAttribute("substationId", substationId);
        }
        if(substationImage != null && !"".equals(substationImage)){
            request.setAttribute("substationImage", substationImage);
        }
        if(currentPageSb != null && !"".equals(currentPageSb)){
            request.setAttribute("currentPageSb", currentPageSb);
        }

        int pageCount = commandInformationMapper.selectCount(map);

        pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
        List<CommandInformation> commandInformationList = commandInformationMapper.select(map);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("commandInformationList", commandInformationList);
    }


    @Override
    public String deleteById(String Id) throws Exception {
        return commandInformationMapper.deleteById(Id)>0?"true":"false";
    }


    @Override
    public List<CommandInformation> selectByDeviceNumber(String deviceNumber) throws Exception {
        return commandInformationMapper.selectByDeviceNumber(deviceNumber);
    }

    @Override
    public List<CommandInformation> selectTime() throws Exception {
        return commandInformationMapper.selectTime();
    }

    @Override
    public String deleteAll() throws Exception {
        return commandInformationMapper.deleteAll()>0?"true":"false";
    }

    @Override
    public String deletByEqId(String deviceNumber) throws Exception {
        return commandInformationMapper.deletByEqId(deviceNumber)>0?"true":"false";
    }

    @Override
    public List<CommandInformation> selectByEqId(String deviceNumber) throws Exception {
        return commandInformationMapper.selectByEqId(deviceNumber);
    }


}
