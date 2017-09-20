package cn.edu.ahpu.maven.demo1.Service.impl;

import cn.edu.ahpu.maven.demo1.Service.IUesrService;
import cn.edu.ahpu.maven.demo1.model.User;
import cn.edu.ahpu.redis.RedisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author: jhs
 * @Desc:
 * @Date: Create in 2017/9/18  16:56
 */
public class UesrServiceImpl implements IUesrService {
	public static String USER_REDIS_KEY = "USER_REDIS_KEY";
	public void addUser(User user) {
		String json = RedisUtil.getJedis().hget(USER_REDIS_KEY,user.getUserId());
		if(json != null){
			System.out.println("已存在重复的id");
		}else{
			RedisUtil.getJedis().hset(USER_REDIS_KEY, user.getUserId(), JSONObject.fromObject(user).toString());
		}
	}
	
	public void delUser(String userId) {
		RedisUtil.getJedis().hdel(USER_REDIS_KEY,userId);
	}
	
	public User getUser(String userId) {
		String json = RedisUtil.getJedis().hget(USER_REDIS_KEY,userId);
		if(json != null){
			return (User) JSONObject.toBean(JSONObject.fromObject(json), User.class);
		}
		return null;
	}
	
	public List<User> listAll(){
		Map<String, String> list =  RedisUtil.getJedis().hgetAll(USER_REDIS_KEY);
		
		System.out.println(list);
		return null;
	}
	
	private static UesrServiceImpl instance = null;
	private UesrServiceImpl() {
	}
	
	public static UesrServiceImpl getInstance(){
		if(instance == null){
			instance = new UesrServiceImpl();
		}
		return instance;
	}
	
	
}
