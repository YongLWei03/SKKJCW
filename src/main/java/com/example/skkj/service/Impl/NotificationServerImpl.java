package com.example.skkj.service.Impl;

import com.example.skkj.entity.Notification;
import com.example.skkj.mapper.NotificationMapper;
import com.example.skkj.service.NotificationServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("notificationServer")
public class NotificationServerImpl implements NotificationServer {
    @Autowired
    private NotificationMapper notificationMapper;
    @Override
    public Notification select() throws Exception {
        return notificationMapper.select();
    }
}
