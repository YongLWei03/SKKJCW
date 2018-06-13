package com.example.skkj.mapper;


import com.example.skkj.entity.CalculationWd;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component(value = "calculationWdMapper")
public interface CalculationWdMapper {

    int insert(CalculationWd calculationWd)throws Exception;

    int update(CalculationWd calculationWd)throws Exception;

    CalculationWd select(String deviceNumber)throws Exception;

    //根据ID删除
    int deleteByDeviceNumber(String deviceNumber)throws Exception;


     /**
          * @Author ZhouNan
          * @Description 批量添加
          * @params
          * @Date 2018/5/4 0004  09:13
          */
     int insertAll(List<CalculationWd> calculationWdList)throws Exception;
}
