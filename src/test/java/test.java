import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.UserServiceImpl;

public class test {
	@Test
	public void testInsert() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper mapper = ac.getBean("userMapper",UserMapper.class);
		User user = new User();
		user.setUsername("java");
		user.setPassword("1223423");
		user.setEmail("12qwe3@qq.com");
		user.setGender(1);
		user.setPhone("123qwe23435");
		mapper.insert(user);
		ac.close();
	}
	
	@Test
	public void testReg() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService userService = ac.getBean("userService",IUserService.class);
		User user = new User();
		user.setUsername("222222");
		user.setPassword("123");
		User data = userService.reg(user);
		System.out.println(data);
		ac.close();
	}
	
	@Test
	public void testSelect() {
		AbstractApplicationContext ac= new ClassPathXmlApplicationContext("spring-dao.xml");
		
		UserMapper mapper = ac.getBean("userMapper", UserMapper.class);	
		String where = null;
		String orderBy = "id";
		Integer offset = 1;
		Integer countPerPage = 10;
		List<User> users = mapper.select(where, orderBy, offset, countPerPage);	
		System.out.println(users.size());
		for(User u : users) {
			System.out.println(u);
		}
		
		ac.close();
	}
	@Test
	public void testLogin() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService userService = ac.getBean("userService",IUserService.class);
		String username = "66666";
		String password = "123";
		User user = userService.login(username, password);
		System.out.println(user);
		ac.close();
	}
	
	@Test
	public void testUpdate() {
		AbstractApplicationContext ac= new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper mapper = ac.getBean("userMapper", UserMapper.class);	
		User user = new User();
		user.setId(15);
		user.setUsername("哈哈");
		user.setPassword("1");
		user.setEmail("adc");
		user.setPhone("000");
		user.setGender(2);
		mapper.update(user);
		ac.close();
	}
	@Test
	public void testUpdatePassword() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService userService = ac.getBean("userService",IUserService.class);
		Integer id = 17;
		String oldPassword = "123";
		String newPassword = "1234";
		Integer user = userService.changePassword(id, oldPassword, newPassword);
		System.out.println(user);
		ac.close();
	}
	@Test
	public void testchangeInfo() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService userService = ac.getBean("userService",IUserService.class);
		Integer id = 16;
		String username = "123";
		Integer gender = 1;
		String phone = "1234567";
		String email = "12345@er";
		Integer user = userService.changeInfo(id, username, gender, phone, email);
		System.out.println(user);
		ac.close();
	}
}
