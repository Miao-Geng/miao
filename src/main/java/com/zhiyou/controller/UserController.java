package com.zhiyou.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhiyou.model.User;
import com.zhiyou.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService service;
	

	@RequestMapping("login")
	public String login(User user,HttpServletRequest req,HttpServletResponse resp) {
		req.getSession().setAttribute("user", user);
		return user==null?"index":"forward:show";
		
	}
	
	@RequestMapping("show")
	public String show(HttpServletRequest req,HttpServletResponse resp) {
		List<User> list = service.selectAll();
		req.setAttribute("list", list);
		return "show";
		
	}
	
	@RequestMapping("add")
	public String add(User user,HttpServletRequest req,HttpServletResponse resp) {
		service.add(user);
		return "forward:show";
	}
	
	@RequestMapping("delete")
	public String detele(int id){
		service.delete(id);
		return "forward:show";
	}
	@RequestMapping("byId")
	public String byId(int id,HttpServletRequest req,HttpServletResponse resp){
		User user = service.selectById(id);
		req.setAttribute("user", user);
		return "update";
	}
	
	@RequestMapping("update")
	public String update(User user,HttpServletRequest req, HttpServletResponse resp){
		service.update(user);
		return "forward:show";
	}
	
	@RequestMapping("exit")
	public String exit(HttpServletRequest req, HttpServletResponse resp){
		req.getSession().invalidate();
		return "index";
	}
	
}
