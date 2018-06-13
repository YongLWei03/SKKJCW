package com.example.skkj.service;

import com.example.skkj.entity.AppApk;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface AppApkServer {
    @Transactional
    String  insert(AppApk appApk)throws Exception;
    @Transactional
    void select(HttpServletRequest request)throws Exception;

    /**
     * @Author ZhouNan
     * @Description 查看时候是最新版本
     * @params
     * @Date 2018/6/4 0004  11:30
     */
    @Transactional
    Map<String,Object> selectByVesion(String vesion)throws Exception;
}
