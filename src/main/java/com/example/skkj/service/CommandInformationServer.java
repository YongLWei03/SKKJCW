package com.example.skkj.service;

import com.example.skkj.entity.CommandInformation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CommandInformationServer {

    //修改命令状态
    @Transactional
    String updateByDeviceNumber(CommandInformation commandInformation)throws Exception;

    //根据DeviceNumber查询出信息操作
    @Transactional
    void select(HttpServletRequest request)throws Exception;

    //删除命令信息
    @Transactional
    String  deleteById(String Id)throws Exception;

    //查看发送命令信息
    @Transactional
    List<CommandInformation> selectByDeviceNumber(String deviceNumber)throws Exception;

    //查询出是否存在没有成功  失败  超时的命令
    @Transactional
    List<CommandInformation> selectTime()throws Exception;

    //清空命令数据
    @Transactional
    String deleteAll()throws Exception;
    @Transactional
    String deletByEqId(String deviceNumber)throws Exception;
    @Transactional
    List<CommandInformation> selectByEqId(String deviceNumber)throws Exception;
}
