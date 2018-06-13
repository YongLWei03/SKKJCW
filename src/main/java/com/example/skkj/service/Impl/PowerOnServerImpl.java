package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.PowerOn;
import com.example.skkj.mapper.PowerOnMapper;
import com.example.skkj.service.PowerOnServer;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("powerOnServer")
public class PowerOnServerImpl implements PowerOnServer {
    @Autowired
    private PowerOnMapper powerOnMapper;
    @Override
    public int insert(PowerOn powerOn) throws Exception {
        return powerOnMapper.insert(powerOn);
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
        String currentPages = request.getParameter("currentPages");
        String equipmentxi = request.getParameter("equipmentxi");
        String substationId = request.getParameter("substationId");
        String substationImage = request.getParameter("substationImage");
        if(equipmentxi != null && !"".equals(equipmentxi)){
            equipmentxi =  URLDecoder.decode(equipmentxi,"UTF-8");
            request.setAttribute("equipmentxi",equipmentxi);
        }
        if(substationId != null && !"".equals(substationId)){
            request.setAttribute("substationId",substationId);
        }
        if(substationImage != null && !"".equals(substationImage)){
            request.setAttribute("substationImage",substationImage);
        }
        if(null!=dqpagestr && !"".equals(dqpagestr)){
            currentPage = Integer.parseInt(dqpagestr);
        }
        map.put("start", ((currentPage-1)*pageSize));
//			map.put("end", pageSize*currentPage);
        map.put("pageSize", pageSize);
        map.put("deviceNumber",deviceNumber);
        List<PowerOn> powerOnList = powerOnMapper.select(map);
        int pageCount1 = powerOnMapper.selectCount(map);

        int pageCount = pageCount1 % pageSize == 0 ? pageCount1 / pageSize : (pageCount1 / pageSize) + 1;
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("powerOnList", powerOnList);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("pageCount1", pageCount1);
        request.setAttribute("deviceNumber",deviceNumber);
        request.setAttribute("currentPages",currentPages);
        request.setAttribute("equipmentxi",equipmentxi);
    }

}
