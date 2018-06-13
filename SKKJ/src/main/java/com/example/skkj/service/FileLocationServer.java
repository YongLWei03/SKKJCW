package com.example.skkj.service;

import com.example.skkj.entity.FileLocation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FileLocationServer {
    //根据时间段查询出文件地址
    @Transactional
    List<FileLocation> sectByTime(String startTime, String endTime)throws Exception;

    //查询当前时间的问题
    @Transactional
    String selectByTime(String time)throws Exception;
}
