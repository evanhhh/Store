package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

public interface UserMapper {
	/**
	 * 添加用户信息
	 * @param user 用户信息
	 * @return 受影响行数
	 */
	Integer insert(User user);
	/**
	 * 查询用户信息
	 * @param where Where子句，不包含where关键字
	 * @param orderBy orderBy子句，不要包含order by关键字
	 * @param offset 偏移量，用于Limit子句的第一个值
	 * @param countPerPage 每页显示的数据量,
	 * @return 匹配用户信息的集合
	 */
	List<User> select(@Param("where")String where,@Param("orderBy")String orderBy,
			@Param("offset")Integer offset,@Param("countPerPage")Integer countPerPage);
	/**
	 * 修改用户信息
	 * @param user 至少封装了用户的id，和新的数据对象
	 * @return 受影响的行数
	 */
	Integer update(User user);
	
}
