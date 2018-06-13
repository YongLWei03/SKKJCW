package com.example.skkj.service;

import com.example.skkj.entity.AcpEquipment;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AcpEquipmentServer {
    @Transactional
    String insertUpda(AcpEquipment acpEquipment)throws Exception;
    @Transactional
   void selectByDeverId(HttpServletRequest request)throws Exception;
    @Transactional
    Map<String,Object> appSelectByDeverId(HttpServletRequest request)throws Exception;
}
