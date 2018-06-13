package com.example.skkj.mapper;


import com.example.skkj.entity.PowerOn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Mapper
@Component(value = "powerOnMapper")
public interface PowerOnMapper {

    int  insert(PowerOn powerOn)throws Exception;

    List<PowerOn> select(Map<String, Object> map)throws Exception;

    int selectCount(Map<String, Object> map)throws Exception;

}
