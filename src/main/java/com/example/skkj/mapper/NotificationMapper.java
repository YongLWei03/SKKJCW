package com.example.skkj.mapper;

import com.example.skkj.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "notificationMapper")
public interface NotificationMapper {
    Notification select()throws Exception;
}
