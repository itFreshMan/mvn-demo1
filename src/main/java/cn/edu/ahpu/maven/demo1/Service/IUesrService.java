package cn.edu.ahpu.maven.demo1.Service;

import cn.edu.ahpu.maven.demo1.model.User;

import java.util.List;

/**
 * @Author: jhs
 * @Desc:
 * @Date: Create in 2017/9/18  16:55
 */
public interface IUesrService {
	void addUser(User user);
	
	void delUser(String userId);
	
	User getUser(String userId);
	
	List<User> listAll();
}
