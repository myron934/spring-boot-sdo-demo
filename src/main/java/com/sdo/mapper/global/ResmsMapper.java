package com.sdo.mapper.global;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sdo.entity.ResmsEntity;

public interface ResmsMapper {

	@Select("SELECT * FROM bg_config_resms_number")
	@Results({
		@Result(property = "telecomName",  column = "telecom_name"),
		@Result(property = "resmsNumber", column = "resms_number"),
		@Result(property = "telecomStatus", column = "resms_status"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time")
	})
	public List<ResmsEntity> listAll();
	
	@Select("SELECT * FROM bg_config_resms_number where id==#{id}")
	@Results({
		@Result(property = "telecomName",  column = "telecom_name"),
		@Result(property = "resmsNumber", column = "resms_number"),
		@Result(property = "telecomStatus", column = "resms_status"),
		@Result(property = "createTime", column = "create_time"),
		@Result(property = "updateTime", column = "update_time")
	})
	public ResmsEntity getById(int id);
	
	@Insert("INSERT INTO bg_config_resms_number(telecom, telecom_name, resms_number, telecom_status) VALUES(#{telecom},#{telecomName},#{resmsNumber},#{telecomStatus})")
	public void insert(ResmsEntity resms);
	
	@Insert("INSERT INTO bg_config_resms_number(telecom, telecom_name, resms_number, telecom_status) VALUES(#{telecom},#{telecomName},#{resmsNumber},#{telecomStatus})")
	@Options(useGeneratedKeys=true,keyProperty="id") 
	public int insert2(ResmsEntity resms);
	
	@Update("UPDATE bg_config_resms_number SET resms_number=#{resmsNumber}, telecom_name=#{telecomName} WHERE id=#{id}")
	public void update(ResmsEntity resms);
	
	@Delete("DELETE FROM bg_config_resms_number WHERE id=#{id}")
	public void delete(int id);
	
	
}
