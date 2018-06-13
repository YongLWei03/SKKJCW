package com.example.skkj.mapper;


import com.example.skkj.entity.AdministratorAndUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 管理员和用户的ID
 * @Author ZhouNan
 * @Description
 * @params
 * @Date 16:47 2017/12/11 0011
 */
@Mapper
@Component(value = "administratorAndUserMapper")
public interface AdministratorAndUserMapper {
        /**
         * @Author ZhouNan
         * @Description
         * @params userId 添加用户的ID
         * @params adminId 管理员ID
         * @Date 16:49 2017/12/11 0011
         */
        int insert(AdministratorAndUser administratorAndUser)throws Exception;

         /**
              * @Author ZhouNan
              * @Description 删除管理员操作的信息
              * @params
              * @Date 2018/2/28 0028  11:07
              */
         int delet(String userId)throws Exception;
}
