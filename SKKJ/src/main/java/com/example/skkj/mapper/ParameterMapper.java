package com.example.skkj.mapper;


import com.example.skkj.entity.Parameter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "parameterMapper")
public interface ParameterMapper {

    int insert(Parameter parameter)throws Exception;

    //查询出最近一次添加的信息
    Parameter selectByDeviceNumber(String deviceNumber)throws Exception;

    //修改板级参数设置最近的一次
    int updateByTimeAndDeviceNumber(Parameter parameter)throws Exception;

}
