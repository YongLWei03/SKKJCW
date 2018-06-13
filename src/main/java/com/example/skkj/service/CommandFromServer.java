package com.example.skkj.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.transaction.annotation.Transactional;

public interface CommandFromServer {
    //采样使能命令通知
    @Transactional
    void samplingEnable (JSONObject param)throws Exception;

    //板级设置
    @Transactional
    void boardLevel (JSONObject param)throws Exception;

    //板级查询
    @Transactional
    void selectBoar (JSONObject param)throws Exception;

    //立即采样
    @Transactional
    void immediateSampling (JSONObject param)throws Exception;

    //效验设置
    @Transactional
    void calibration (JSONObject param)throws Exception;
    //相位设置
    @Transactional
    void algorithm (JSONObject param)throws Exception;
}
