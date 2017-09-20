package cn.edu.ahpu.maven.demo1.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: jhs
 * @Desc:
 * @Date: Create in 2017/9/18  16:55
 */
public class User {
	private String userId;
	private String username;
	
	public User() {
	}
	
	public User(String userId, String username) {
		this.userId = userId;
		this.username = username;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
