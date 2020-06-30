package com.rj.bd.user.service;


import com.rj.bd.appeal.entity.Appeal;
import com.rj.bd.user.entity.User;

import java.util.Map;

public interface IUserService {

    void save(User u);

    Map queryUser(User u);

    Map queryByIdToBlackList(Map userMap);

    void saveAppeal(Appeal appeal);
}
