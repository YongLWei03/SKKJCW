package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.AddTable;
import com.example.skkj.mapper.AddTableMapper;
import com.example.skkj.service.AddTableServer;

import java.util.List;

@Service("addTableServer")
public class AddTableServerImpl implements AddTableServer{

    @Autowired
    private AddTableMapper addTableMapper;

    @Override
    public String insert(AddTable addTable) throws Exception {
        return addTableMapper.insert(addTable)>0?"true":"false";
    }

    @Override
    public AddTable selectByTime() throws Exception {
        return addTableMapper.selectByTime();
    }

    @Override
    public List<AddTable> selectByse(String startTime, String endTime) throws Exception {
        return addTableMapper.selectByse(startTime,endTime);
    }

    @Override
    public AddTable selectByEndTime() throws Exception {
        return addTableMapper.selectByEndTime();
    }

    @Override
    public int update(AddTable addTable) throws Exception {
        return addTableMapper.update(addTable);
    }
}
