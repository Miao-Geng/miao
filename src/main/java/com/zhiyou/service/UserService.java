package com.zhiyou.service;

import java.util.List;

import com.zhiyou.model.User;

public interface UserService {
	void add(User user);
	void delete(int id);
	void update(User user);
	List<User> selectAll();
	User selectById(int id);
	// 可以用各种条件查询的sql
	List<User> select(User user);
	//可以对所有列进行模糊查询
	List<User> selectLike(User user);
	//批量删除
	void deleteAll(int[] ids);
	User login(String name,String password);
}
