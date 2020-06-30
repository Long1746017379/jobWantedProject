package com.rj.bd.user.dao;

import com.rj.bd.appeal.entity.Appeal;
import com.rj.bd.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("userMapper")
public interface UserMapper {

    @Insert("insert into user values(0, #{uname}, #{uaccount}, #{upassword}, #{uemail})")
    void save(User u);

    @Select("select * from user where uaccount = #{uaccount} and upassword = #{upassword}")
    Map queryUser(User u);

    @Select("SELECT * FROM blacklist WHERE uid = #{uid}")
    Map queryByIdToBlackList(Map userMap);

    @Insert("INSERT INTO appeal VALUES (0, #{uid}, #{ainfo})")
    void saveAppeal(Appeal appeal);
}
