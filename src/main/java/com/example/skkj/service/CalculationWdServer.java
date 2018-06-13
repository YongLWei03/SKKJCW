package com.example.skkj.service;

import com.example.skkj.entity.CalculationWd;
import org.springframework.transaction.annotation.Transactional;

/**
      * @Author ZhouNan
      * @Description 温度计算方法
      * @params
      * @Date 2018/3/12 0012  09:43
      */
public interface CalculationWdServer {
    @Transactional
     String insert(CalculationWd calculationWd)throws Exception;
    @Transactional
    String update(CalculationWd calculationWd)throws Exception;
    @Transactional
     CalculationWd select(String deviceNumber)throws Exception;
}
