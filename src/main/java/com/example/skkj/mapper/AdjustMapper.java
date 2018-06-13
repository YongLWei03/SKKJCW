package com.example.skkj.mapper;


import com.example.skkj.entity.Adjust;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component(value = "adjustMapper")
public interface AdjustMapper {
    int isnertAll(List<Adjust> adjust)throws Exception;

    List<Adjust> selectByCommandId(String commandId)throws Exception;

    int delet()throws Exception;
}
