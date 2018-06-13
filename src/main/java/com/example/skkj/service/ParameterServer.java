package com.example.skkj.service;

import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ParameterServer {
    //查询出最近一次添加的信息
    @Transactional
    Map<String,Object> selectByDeviceNumber(HttpServletRequest request)throws Exception;
}
