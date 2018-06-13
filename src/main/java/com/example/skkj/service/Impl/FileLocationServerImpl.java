package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.FileLocation;
import com.example.skkj.mapper.FileLocationMapper;
import com.example.skkj.service.FileLocationServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("fileLocationServer")
public class FileLocationServerImpl implements FileLocationServer {
    @Autowired
    private FileLocationMapper fileLocationMapper;

    @Override
    public List<FileLocation> sectByTime(String startTime,String endTime) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return fileLocationMapper.sectByTime(map);
    }

    @Override
    public String selectByTime(String time) throws Exception {
        return fileLocationMapper.selectByTime(time);
    }
}
