package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

public interface AddressMapper {
	 Integer insert(Address address);
	 /**
	  * 查询收获地址信息
	  * @param where where的子句
	  * @param orderBy order by子句
	  * @param offset limit子句的第一个参数，表示跳过多少个
	  * @param countPerpage  limit 子句的第二个参数，表示每一页显示个数
	  * @return 返回地址
	  */
	List<Address> select(@Param("where") String where,@Param("orderBy") String orderBy,
			@Param("offset") Integer offset,@Param("countPerpage") Integer countPerpage);
	/**
	 * 删除收获地址
	 * @param where 
	 * @return 
	 */
	Integer delete(@Param("where") String where );
	/**
	 * 修改收获地址
	 * @param address 
	 * @return
	 */
	Integer update(Address address);
}
