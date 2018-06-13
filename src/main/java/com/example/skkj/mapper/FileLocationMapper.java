package com.example.skkj.mapper;


import com.example.skkj.entity.FileLocation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 文件存储
 * */
@Mapper
@Component(value = "fileLocationMapper")
public interface FileLocationMapper {
	
	//添加文件存储位置
	int insert(List<FileLocation> fileLocation)throws Exception;
	
	//通过时间查询出来所有ID
	List<String> selectByFileLocation(Map<String, Object> map)throws Exception;
	
	//通过时间查询出有多少数据
	int selectByFileLocationCount(Map<String, Object> map)throws Exception;
	
	//根据传入得ID查询出文件地址
//	FileLocation findByflId(String flId)throws Exception;

	List<FileLocation> findByflIdList(List<String> flId)throws Exception;

	//根据时间段查询出文件地址
	List<FileLocation> sectByTime(Map<String, String> map)throws Exception;

	//查询当前时间的问题
	String selectByTime(String time)throws Exception;

	//查询出所有的数据路径
		List<FileLocation> select()throws Exception;
}
