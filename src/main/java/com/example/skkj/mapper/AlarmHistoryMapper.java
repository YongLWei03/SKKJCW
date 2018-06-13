package com.example.skkj.mapper;


import com.example.skkj.entity.AlarmHistory;
import com.example.skkj.entity.AlarmInformation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Mapper
@Component(value = "alarmHistoryMapper")
public interface AlarmHistoryMapper {

    //添加报警信息之前的数据
    int insert(AlarmHistory alarmHistory)throws Exception;

    //查看报警信息
    List<AlarmHistory> selectAlarmHistory(Map<String, Object> map)throws Exception;

    //报警状态信息个数
    int selectAlarmHistoryCount(Map<String, Object> map)throws Exception;


    //删除温度
    int deleteByTimeDNum(Map<String, Object> map)throws Exception;

    ////批量条件删除
    int deleteByAlarmHistory(List<AlarmInformation> alarmInformation)throws Exception;

    //根据设备ID删除
    int deletByDevierNumber(String deviceNumber)throws Exception;

}
