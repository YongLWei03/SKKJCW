package com.example.skkj.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skkj.comment.TimeUtile;
import com.example.skkj.entity.AppApk;
import com.example.skkj.mapper.AppApkMapper;
import com.example.skkj.service.AppApkServer;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("appApkServer")
public class AppApkServerImpl implements AppApkServer{

    @Autowired
    private AppApkMapper appApkMapper;
    @Override
    public String insert(AppApk appApk) throws Exception {
        String time = TimeUtile.dateTimeFomar();
        appApk.setTime(time);
        return appApkMapper.insert(appApk)>0?"true":"false";
    }

    @Override
    public void select(HttpServletRequest request) throws Exception {
        //每页显示多少条
        int pageSize = 10;
        //当前页
        int currentPage = 1;

        Map<String,Object> map = new HashMap<String, Object>();
        //当前页
        String dqpagestr = request.getParameter("currentPage");
        if(null!=dqpagestr && !"".equals(dqpagestr)){
            currentPage = Integer.parseInt(dqpagestr);
        }
        map.put("start", ((currentPage-1)*pageSize));
//			map.put("end", pageSize*currentPage);
        map.put("pageSize", pageSize);
        Integer pageCount = 0;
        List<AppApk> appApkList = appApkMapper.select(map);
        pageCount = appApkMapper.selectCount();
        pageCount = pageCount%pageSize==0?pageCount/pageSize:(pageCount/pageSize)+1;
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("appApkList", appApkList);
    }

    @Override
    public Map<String,Object> selectByVesion(String vesion) throws Exception {
        AppApk appApk = new AppApk();
         appApk = appApkMapper.selectByVesion();

        Map<String, Object> map = new HashMap<String, Object>();
        if(appApk != null && !"".equals(appApk)){
            String vesions = appApk.getVesion();
            if(vesion != vesions && !vesions.equals(vesion)){
                map.put("appApk",appApk);
            }else {
                map.put("appApk","false");
            }
        }else {
                map.put("appApk","wk");
        }
        return map;
    }
}
