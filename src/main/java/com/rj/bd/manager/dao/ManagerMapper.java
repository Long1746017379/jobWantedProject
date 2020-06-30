package com.rj.bd.manager.dao;

import java.util.List;
import java.util.Map;

import com.rj.bd.user.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.rj.bd.manager.entity.Manager;

@Repository("managerMapper")
public interface ManagerMapper {

	@Select("select * from manager")
	List<Manager> queryAll();

	@Select("select * from manager where maccount = #{maccount} and mpassword = #{mpassword}")
    Map queryManager(Manager m);

	@Insert("insert into manager values(0, #{mname}, #{maccount}, #{mpassword})")
    void save(Manager m);

	@Delete("delete from manager where mid = #{id}")
    void delete(String id);

	@Select("select * from manager where mid = #{id}")
	Manager queryById(String id);

	@Update("update manager set mname = #{mname}, maccount = #{maccount}, mpassword = #{mpassword} where mid = #{mid}")
	void edit(Manager m);

	@Select("select * from user")
	List<User> queryUserAll();

	@Select("SELECT * FROM blacklist AS b LEFT JOIN USER AS u ON b.uid = u.uid")
	/*@Results({
			@Result(id=true,column="s_id",property="sid"),
			@Result(column="s_name",property="sname"),
			@Result(column="c_id",property="classes.cid"),
			@Result(column="c_name",property="classes.cname"),
			@Result(column="classes",property="c_id",one=@One(select="com.rj.bd.classes.entity.Classes"))
	})*/
	List<User> queryUserBlackList();

	@Insert("insert into blacklist values(0, #{id}, '无' )")
	void addBlackList(String id);

	@Delete("delete from blacklist where uid = #{id}")
	void outBlackList(String id);

	@Select("select * from user where uid = #{id}")
	Map queryByIdToUser(String id);
}
