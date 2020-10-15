package com.zhiyou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyou.dao.UserDao;
import com.zhiyou.model.User;
import com.zhiyou.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;
	public User login(String name, String password) {
		List<User> list = dao.login(name, password);
		return list.size() != 0 ? list.get(0) : null;
	}

	public void add(User user) {
		dao.add(user);
	}

	public void delete(int id) {
		dao.delete(id);
	}

	public void update(User user) {
		dao.update(user);
	}

	public List<User> selectAll() {
		List<User> list = dao.selectAll();
		return list;
	}

	public List<User> selectLike(User user) {
		return dao.selectLike(user);
	}

	public void deleteAll(int[] ids) {
		dao.deleteAll(ids);
	}

	public List<User> select(User user) {
		return	dao.select(user);
	}

	public User selectById(int id) {	
		return dao.selectById(id);
	}

}
