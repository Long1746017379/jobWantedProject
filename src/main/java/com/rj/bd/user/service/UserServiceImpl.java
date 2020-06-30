package com.rj.bd.user.service;


import com.rj.bd.appeal.entity.Appeal;
import com.rj.bd.user.dao.UserMapper;
import com.rj.bd.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public void save(User u) {
        userMapper.save(u);
    }

    public Map queryUser(User u) {
        return userMapper.queryUser(u);
    }

    public Map queryByIdToBlackList(Map userMap) {
        return userMapper.queryByIdToBlackList(userMap);
    }

    public void saveAppeal(Appeal appeal) { userMapper.saveAppeal(appeal); }


}
