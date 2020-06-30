package com.rj.bd.manager.service;

import java.util.List;
import java.util.Map;

import com.rj.bd.manager.entity.Manager;
import com.rj.bd.user.entity.User;

public interface IManagerService {

	List<Manager> queryAll();

    Map queryManager(Manager m);

    void save(Manager m);

    void delete(String id);

    Manager queryById(String id);

    void edit(Manager m);

    List<User> queryUserAll();

    List<User> queryUserBlackList();

    void addBlackList(String id);

    void outBlackList(String id);

    Map queryByIdToUser(String id);
}
