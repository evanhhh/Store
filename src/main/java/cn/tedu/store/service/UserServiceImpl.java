package cn.tedu.store.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.service.ex.UserNotExisException;
@Service("userService")
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper userMapper;
	public User reg(User user) {
		//根据尝试注册的用户名进行查询
		User u = findUserByUsername(user.getUsername());
		//判断用户名是否存在
		if(u!=null) {
			//被占用，则
			throw new UsernameConflictException("用户名"+user.getUsername()+"已经存在！");
		}else {
			//用户名没有被占用，则执行注册
			//加密密码
			String salt = UUID.randomUUID().toString();
			String md5Password = getEncrpytedPassword(user.getPassword(), salt).toUpperCase();
			user.setPassword(md5Password);
			//保存uuid
			user.setUuid(salt);
			//保存日志信息
			Date now = new Date();
			user.setCreatedUser("System");
			user.setCreateTime(now);
			user.setModifiedUser("System");
			user.setModifiedTime(now);
			// 使用Mybatis处理insert后
			// 数据的id将被封装到参数对象中
			// 在执行以下代码之前，参数user中并没有id
			// 执行完后，MyBatis将把id封装到参数对象user中
			userMapper.insert(user);
			return user;
		}

	}

	public User findUserByUsername(String username) {
		//确定where子句的内容
		String where = "username='"+username+"'";
		//调用持久层对象实现查询
		List<User> users = userMapper.select(where, null,null,null);
		//判断查询结果
		if(users.size()==0) {
			//没有该用户
			return null;
		}else {
			//找到用户，由于用户名唯一，则集合中第一个就是要查询的数据
			return users.get(0);
		}
	}

	public User findUserById(Integer id) {
		//确定where子句的内容
		String where = "id='"+id+"'";
		//调用持久层对象实现查询
		List<User> users = userMapper.select(where, null,null,null);
		//判断查询结果
		if(users.size()==0) {
			//没有该用户
			return null;
		}else {
			//找到用户，由于用户名唯一，则集合中第一个就是要查询的数据
			return users.get(0);
		}
	}
	
	public String getEncrpytedPassword(String password, String salt) {
		//密码+盐
		String str = password+salt;
		//使用MD5加密获取拼接后的字符串消息摘要（密文）
		String md5 = DigestUtils.md5DigestAsHex(str.getBytes());
		//返回密文
		return md5;
	}

	public User login(String username, String password) {
		//根据用户名查询用户信息
		User user = findUserByUsername(username);
		//判断用户名是否存在，如果不存在，抛出异常
		if(user==null) {
			throw new UserNotExisException("用户名不存在！");
			//如果存在，继续
		}else {
			//拿到密码盐
			String salt = user.getUuid();
			//拿到加密后的密文
			String md5password = getEncrpytedPassword(password, salt).toUpperCase();
			//判断密文是否匹配
			if(user.getPassword().equals(md5password)) {
				//匹配，返回user
				return user;
			}else {
				//不匹配，抛出密码错误异常
				throw new PasswordNotMatchException("您输入的密码有误");
			}
		}

	}


	public Integer changePassword(Integer id, String oldPassword, String newPassword) {
		//1获取id匹配的用户信息
		User data = findUserById(id);
		if(data == null) {
			throw new UserNotExisException("尝试操作用户信息不存在，有可能被删除，请联系管理员！");
		}else {
			//获取盐
			String salt = data.getUuid();
			//验证旧密码是否正确 先对旧密码进行加密再比较
			String md5oldPassword = getEncrpytedPassword(oldPassword, salt).toUpperCase();
			if(md5oldPassword.equals(data.getPassword())) {
				//对新的密码进行加密处理
				String md5newPassword = getEncrpytedPassword(newPassword, salt).toUpperCase();
				//执行修改
				User user = new User();
				user.setId(id);
				user.setPassword(md5newPassword);
				//保存日志信息
				Date now = new Date();
				user.setModifiedUser(data.getUsername());
				user.setModifiedTime(now);
				return userMapper.update(user);
				
			}else {
				throw new PasswordNotMatchException("您输入的旧密码有误！");
			}
		}	
	}

	public Integer changeInfo(Integer id, String username, Integer gender, String phone, String email) {
		
		User data = findUserById(id);
		//根据id判断用户是否存在
		if(data==null) {
			throw new UserNotExisException("尝试操作用户信息不存在，有可能被删除，请联系管理员！");
		}else {
			//判断用户名是否为空
			if(username!=null) {
				//如果不为空，表示用户想修改用户名，进一步判断用户名
				User  u =  findUserByUsername(username);
				if(u!=null) {
					//如果不为空，则说明被占用，要进一步判断是自己的还是别人占用的
					if(data.getUsername().equals(u.getUsername())) {
						//找到的用户名是自己现在占用的，则不需要修改
						username =  null;
					}else {
						//找到的用户名已经被别人使用，则抛出异常
						throw new UsernameConflictException("该用户名已被注册！");
					}
				}	
			}
			User user = new User();
			user.setId(id);
			user.setUsername(username);
			user.setGender(gender);
			user.setPhone(phone);
			user.setEmail(email);
			//保存日志信息
			Date now = new Date();
			user.setModifiedUser(data.getUsername());
			user.setModifiedTime(now);
			return userMapper.update(user);
		}
	}

	


}
