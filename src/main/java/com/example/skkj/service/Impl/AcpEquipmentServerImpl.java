package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.AcpEquipment;
import com.example.skkj.mapper.AcpEquipmentMapper;
import com.example.skkj.service.AcpEquipmentServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("acpEquipmentServer")
public class AcpEquipmentServerImpl implements AcpEquipmentServer {
    @Autowired
    private AcpEquipmentMapper acpEquipmentMapper;

    @Override
    public String insertUpda(AcpEquipment acpEquipment) throws Exception {
        String message = acpEquipmentMapper.insert(acpEquipment) > 0 ? "true" : "false";
        return message;
        }

    @Override
    public void selectByDeverId(HttpServletRequest request) throws Exception {
        //每页显示多少条
        int pageSize = 10;
        //当前页
        int currentPage = 1;

        Map<String,Object> map = new HashMap<String, Object>();
        String deviceNumber = request.getParameter("deviceNumber");
        String substationId = request.getParameter("substationId");
        String substationImage = request.getParameter("substationImage");
        //当前页
        String dqpagestr = request.getParameter("currentPage");
        String currentPageSb = request.getParameter("currentPageSb");
        if(null!=dqpagestr && !"".equals(dqpagestr)){
            currentPage = Integer.parseInt(dqpagestr);
        }
        if(null!=currentPageSb && !"".equals(currentPageSb)){
            request.setAttribute("currentPageSb", currentPageSb);
        }
        map.put("deviceNumber", deviceNumber);
        map.put("start", ((currentPage-1)*pageSize));
        map.put("pageSize", pageSize);
        Integer pageCount = null;
        List<AcpEquipment> acpequi = new LinkedList<AcpEquipment>();
        acpequi = acpEquipmentMapper.selectByDeverId(map);
        pageCount =  acpEquipmentMapper.selectByDeverIdCount(map);
        pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
        request.setAttribute("substationId", substationId);
        request.setAttribute("substationImage", substationImage);
        request.setAttribute("deviceNumber", deviceNumber);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("acpequi", acpequi);
        request.setAttribute("pageCount", pageCount);
    }

    @Override
    public Map<String,Object> appSelectByDeverId(HttpServletRequest request) throws Exception {
        //每页显示多少条
        int pageSize = 10;
        //当前页
        int currentPage = 1;

        Map<String,Object> map = new HashMap<String, Object>();
        String deviceNumber = request.getParameter("deviceNumber");
        //当前页
        String dqpagestr = request.getParameter("currentPage");
        if(null!=dqpagestr && !"".equals(dqpagestr)){
            currentPage = Integer.parseInt(dqpagestr);
        }
        String pageSizes = request.getParameter("pageSize");
        if(pageSizes != null && !"".equals(pageSizes)){
            pageSize = Integer.parseInt(pageSizes);
        }
        map.put("deviceNumber", deviceNumber);
        map.put("start", ((currentPage-1)*pageSize));
        map.put("pageSize", pageSize);
        Integer pageCount = null;
        List<AcpEquipment> acpequi = new LinkedList<AcpEquipment>();
        acpequi = acpEquipmentMapper.selectByDeverId(map);
        pageCount =  acpEquipmentMapper.selectByDeverIdCount(map);
        pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
           map.clear();
           map.put("currentPage",currentPage);
           map.put("acpequi",acpequi);
           map.put("pageCount",pageCount);
        return map;
    }
}
