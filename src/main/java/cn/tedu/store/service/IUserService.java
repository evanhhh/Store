package cn.tedu.store.service;

import cn.tedu.store.entity.User;

public interface IUserService {
	/**
	 * 用户注册
	 * @param user 注册用户信息
	 * @return	用户信息，包括用户id
	 * @throws UsernameConflictException
	 */
	User reg(User user);
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户信息，如果没有匹配的用户信息，则返回null
	 */
	User findUserByUsername(String username);
	/**
	 * 根据id查找用户信息
	 * @param id 用户id
	 * @return 用户信息，如果没有匹配的用户信息，则返回null
	 */
	User findUserById(Integer id);
	/**
	 * 获取加密的密码
	 * @param password 明文密码
	 * @param salt 盐
	 * @return 密文密码
	 */
	String getEncrpytedPassword(String password,String salt);
	/**
	 * 用户登陆
	 * @param username 用户名
	 * @param password 用户密码
	 * @return 成功登陆的用户信息
	 * @throws UserNotExistsException
	 * @throws  PasswordNotMatchException
	 */
	User login(String username,String password);
	/**
	 *  修改密码
	 * @param id 用户id
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return 返回受影响行数
	 * @throws UserNotExistsException
	 * @throws PasswordNotMatchException
	 */
	Integer changePassword(Integer id,String oldPassword,String newPassword);
	/**
	 * 修改个人信息
	 * @param id 用户id
	 * @param username 用户名
	 * @param gender 性别
	 * @param phone 电话
	 * @param email 邮箱
	 * @return 返回受影响行数
	 * @throws UserNotExistsException
	 */
	Integer changeInfo(Integer id,String username,Integer gender, String phone,String email);
	
	
	
	
	
	
}
