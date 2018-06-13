package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.EquipmentType;
import com.example.skkj.mapper.EquipmentTypeMapper;
import com.example.skkj.service.EquipmentTypeServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("equipmentTypeServer")
public class EquipmentTypeServerImpl implements EquipmentTypeServer{
    @Autowired
    private EquipmentTypeMapper equipmentTypeMapper;

    @Override
    public String insert(EquipmentType equipmentType) throws Exception {
        return equipmentTypeMapper.insert(equipmentType)>0?"true":"false";
    }

    @Override
    public String updateByeqtId(EquipmentType equipmentType) throws Exception {
        return equipmentTypeMapper.updateByeqtId(equipmentType)>0?"true":"false";
    }

    @Override
    public String delete(String eqtId) throws Exception {
        return equipmentTypeMapper.delete(eqtId)>0?"true":"false";
    }

    @Override
    public EquipmentType selectByEqtId(String eqtId) throws Exception {
        return equipmentTypeMapper.selectByEqtId(eqtId);
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
        if(null!=dqpagestr && !"".equals(dqpagestr)){
            currentPage = Integer.parseInt(dqpagestr);
        }
        map.put("start", ((currentPage-1)*pageSize));
        map.put("pageSize", pageSize);

        List<EquipmentType> equipmentTypeList = equipmentTypeMapper.select(map);
        int pageCount = equipmentTypeMapper.selectCount(map);
        pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("equipmentTypeList", equipmentTypeList);
    }
    @Override
    public List<EquipmentType> finde() throws Exception {
        return equipmentTypeMapper.finde();
    }

    @Override
    public String findBydeviceType(String deviceType) throws Exception {
        return equipmentTypeMapper.findBydeviceType(deviceType);
    }
}
