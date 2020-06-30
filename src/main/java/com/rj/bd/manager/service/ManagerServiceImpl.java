package com.rj.bd.manager.service;

import java.util.List;
import java.util.Map;

import com.rj.bd.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rj.bd.manager.dao.ManagerMapper;
import com.rj.bd.manager.entity.Manager;

@Service("managerServiceImpl")
public class ManagerServiceImpl implements IManagerService {

	@Autowired
	private ManagerMapper managerMapper;

	public List<Manager> queryAll() {
		return managerMapper.queryAll();
	}

	public Map queryManager(Manager m) {
		return managerMapper.queryManager(m);
	}

	public void save(Manager m) {
		managerMapper.save(m);
	}

	public void delete(String id) {
		managerMapper.delete(id);
	}

	public Manager queryById(String id) {
		return managerMapper.queryById(id);
	}

	public void edit(Manager m) {
		managerMapper.edit(m);
	}

	public List<User> queryUserAll() { return managerMapper.queryUserAll(); }

	public List<User> queryUserBlackList() {
		return managerMapper.queryUserBlackList();
	}

	public void addBlackList(String id) {
		managerMapper.addBlackList(id);
	}

	public void outBlackList(String id) {
		managerMapper.outBlackList(id);
	}

	public Map queryByIdToUser(String id) {
		return managerMapper.queryByIdToUser(id);
	}


}
