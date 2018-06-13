package com.example.skkj.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

public interface CommandServer {
     /**
          * @Author ZhouNan
          * @Description 设置板级命令
          * @params
          * @Date 2018/1/10 0010  14:29
          */
     @Transactional
     String boardLevel(HttpServletRequest request)throws Exception;
      /**
           * @Author ZhouNan
           * @Description 查看板级设置
           * @params
           * @Date 2018/1/11 0011  09:24
           */
      @Transactional
     String selectBoar(HttpServletRequest request)throws Exception;

      /**
           * @Author ZhouNan
           * @Description 采样史能
           * @params
           * @Date 2018/1/11 0011  16:28
           */
      @Transactional
      String samplingEnable(HttpServletRequest request)throws Exception;

      /**
           * @Author ZhouNan
           * @Description 立即采样
           * @params
           * @Date 2018/1/11 0011  16:28
           */
      @Transactional
      String immediateSampling(HttpServletRequest request)throws Exception;

    /**
     * @Author ZhouNan
     * @Description 效验设置
     * @params
     * @Date 2018/1/11 0011  16:28
     */
    @Transactional
    String calibration(HttpServletRequest request)throws Exception;


    /**
     * @Author ZhouNan
     * @Description app设置板级命令
     * @params
     * @Date 2018/1/10 0010  14:29
     */
    @Transactional
    String appBoardLevel(JSONObject jsonObject)throws Exception;
    /**
     * @Author ZhouNan
     * @Description app查看板级命令
     * @params
     * @Date 2018/1/10 0010  14:29
     */
    @Transactional
    String appSelectBoar(JSONObject jsonObject)throws Exception;
    /**
     * @Author ZhouNan
     * @Description app设置使能采样
     * @params
     * @Date 2018/1/10 0010  14:29
     */
    @Transactional
    String appSamplingEnable(JSONObject jsonObject)throws Exception;
    /**
     * @Author ZhouNan
     * @Description app立即采样
     * @params
     * @Date 2018/1/10 0010  14:29
     */
    @Transactional
    String appImmediateSampling(JSONObject jsonObject)throws Exception;

/**
     * @Author ZhouNan
     * @Description app效验设置
     * @params
     * @Date 2018/1/10 0010  14:29
     */
    @Transactional
    String appCalibration(JSONObject jsonObject)throws Exception;

     /**
          * @Author ZhouNan
          * @Description 相位算法设置
          * @params
          * @Date 2018/5/17 0017  16:25
          */
     @Transactional
     String algorithm(HttpServletRequest request)throws Exception;
     @Transactional
     String appAlgorithm(JSONObject json)throws Exception;


}
