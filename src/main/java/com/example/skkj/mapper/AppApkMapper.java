package com.example.skkj.mapper;



import com.example.skkj.entity.AppApk;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Mapper
@Component(value = "appApkMapper")
public interface AppApkMapper {

      int  insert(AppApk appApk)throws Exception;

      List<AppApk> select(Map<String, Object> map)throws Exception;

      int selectCount()throws Exception;

       /**
            * @Author ZhouNan
            * @Description 查看时候是最新版本
            * @params
            * @Date 2018/6/4 0004  11:30
            */
       AppApk selectByVesion()throws Exception;

}
