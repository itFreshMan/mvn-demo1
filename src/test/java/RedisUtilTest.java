import cn.edu.ahpu.maven.demo1.controller.UserController;
import cn.edu.ahpu.maven.demo1.model.User;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: jhs
 * @Desc:
 * @Date: Create in 2017/9/18  17:04
 */

public class RedisUtilTest {
	UserController controller ;
	@Before
	public void setUp(){
		controller =  new UserController();
	}
	
	@Test
	public void testAddUser(){
		controller.addUser("1","蒋怀双");
		
		controller.addUser("2","阿特兹");
	}
	
	@Test
	public void testGetUser(){
		User user = controller.getUser("1");
		System.out.println(user);
	}
	
	@Test
	public void testListAll(){
		controller.listAllUser();
	}
	
	@Test
	public void testDelUser(){
		controller.delUser("1");
	}
	
	
	
}
