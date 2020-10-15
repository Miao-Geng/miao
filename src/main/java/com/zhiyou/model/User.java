package com.zhiyou.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{
	Integer id;
	String name;
	String password;
	String money;
	
}
