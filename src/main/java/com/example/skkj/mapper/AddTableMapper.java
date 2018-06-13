package com.example.skkj.mapper;

import com.example.skkj.entity.AddTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
@Component(value = "addTableMapper")
public interface AddTableMapper {

    int  insert(AddTable addTable)throws Exception;

    //查看最近添加表记录
    AddTable selectByTime()throws Exception;

    //最近一次结束表的记录
    AddTable selectByEndTime()throws Exception;

    //修改数据
    int update(AddTable addTable)throws Exception;

    List<AddTable> selectByse(@Param("startTime") String startTime, @Param("endTime") String endTime)throws Exception;
}
