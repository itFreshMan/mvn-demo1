package cn.edu.ahpu.maven.demo1.controller;

import cn.edu.ahpu.maven.demo1.Service.IUesrService;
import cn.edu.ahpu.maven.demo1.Service.impl.UesrServiceImpl;
import cn.edu.ahpu.maven.demo1.model.User;

import java.util.Date;

/**
 * @Author: jhs
 * @Desc:
 * @Date: Create in 2017/9/18  16:54
 */
public class UserController {
	private IUesrService service = UesrServiceImpl.getInstance();
	
	
	public void addUser(String id , String name ){
		service.addUser(new User(id,name));
	}
	
	public void delUser(String id){
		service.delUser(id);
	}
	
	public User getUser(String id){
		return service.getUser(id);
	}
	
	public void listAllUser(){
		service.listAll();
	}
}
