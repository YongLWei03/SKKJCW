package com.example.skkj.service;

import com.example.skkj.entity.BaiDuAddr;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BaiduServer {
    //百度地图收缩精确地址
    @Transactional
    List<BaiDuAddr> serche(HttpServletRequest request)throws Exception;
}
