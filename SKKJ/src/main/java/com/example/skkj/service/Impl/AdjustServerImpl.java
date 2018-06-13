package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.entity.Adjust;
import com.example.skkj.mapper.AdjustMapper;
import com.example.skkj.service.AdjustServer;

import java.util.ArrayList;
import java.util.List;


@Service("adjustServer")
public class AdjustServerImpl implements AdjustServer{
    @Autowired
    private AdjustMapper adjustMapper;
    @Override
    public String isnertAll(List<Adjust> adjust,String commandId) throws Exception {
        List<Adjust> adgList = new ArrayList<Adjust>();
        for (int i = 0; i <adjust.size() ; i++) {
            Adjust adj = adjust.get(i);
            int idd = adj.getID();
            adj.setID(idd-1);
            adj.setCommandId(commandId);
            adgList.add(adj);
        }
        return adjustMapper.isnertAll(adgList)>0?"true":"false";
    }

    @Override
    public List<Adjust> selectByCommandId(String commandId) throws Exception {
        return adjustMapper.selectByCommandId(commandId);
    }

    @Override
    public String delet() throws Exception {
        return adjustMapper.delet()>0?"true":"false";
    }
}
