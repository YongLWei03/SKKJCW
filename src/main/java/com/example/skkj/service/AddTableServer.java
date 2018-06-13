package com.example.skkj.service;

import com.example.skkj.entity.AddTable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddTableServer {
    @Transactional
    String  insert(AddTable addTable)throws Exception;
    @Transactional
    //查看最近添加表记录
    AddTable selectByTime()throws Exception;
    @Transactional
    List<AddTable> selectByse(String startTime, String endTime)throws Exception;
    @Transactional
    //最近一次结束表的记录
    AddTable selectByEndTime()throws Exception;
    @Transactional
    //修改数据
    int update(AddTable addTable)throws Exception;
}
