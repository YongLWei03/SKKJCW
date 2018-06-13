package com.example.skkj.service;

import com.example.skkj.entity.Adjust;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdjustServer {
    @Transactional
    String isnertAll(List<Adjust> adjust, String commandId)throws Exception;
    @Transactional
    List<Adjust> selectByCommandId(String commandId)throws Exception;
    @Transactional
    String delet()throws Exception;
}
