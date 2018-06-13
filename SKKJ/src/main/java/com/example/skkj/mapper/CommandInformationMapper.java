package com.example.skkj.mapper;


import com.example.skkj.entity.CommandInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Mapper
@Component(value = "commandInformationMapper")
public interface CommandInformationMapper {
    //添加命令信息
    int insertCommand(CommandInformation commandInformation)throws Exception;

    //修改命令状态
    int updateByDeviceNumber(CommandInformation commandInformation)throws Exception;

    //根据DeviceNumber查询出信息操作
    List<CommandInformation> select(Map<String, Object> map)throws Exception;

    int selectCount(Map<String, Object> map)throws Exception;

    //删除命令信息
    int  deleteById(String Id)throws Exception;

    //根据DeviceNumber删除
    int  deletByDeviceNumber(String deviceNumber)throws Exception;

    //查看立即采集命令信息
    List<CommandInformation> selectByDeviceNumber(String deviceNumber)throws Exception;

    //查询出是否存在没有成功  失败  超时的命令
    List<CommandInformation> selectTime()throws Exception;

    //清空命令数据
    int deleteAll()throws Exception;

    String findByDeviceNumber(Map<String, Object> map)throws Exception;

    int deletByEqId(String deviceNumber)throws Exception;

    List<CommandInformation> selectByEqId(String deviceNumber)throws Exception;

}
