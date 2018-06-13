package com.example.skkj.service;

import com.example.skkj.entity.PowerOn;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

public interface PowerOnServer {
    @Transactional
    int  insert(PowerOn powerOn)throws Exception;
    @Transactional
    void select(HttpServletRequest request)throws Exception;

}
