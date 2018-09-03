package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;

public interface IAddressService {
	
	Address addnew (Address address);
	
	
	 /**
	  * 根据用户的uid获取他所有收货地址
	  * @param uid 用户id
	  * @return 地址列表
	  */
	 
	List<Address> getAddressListByUid(Integer uid);
	
	 /**
	  * 根据id获得用户地址
	  * @param id id
	  * @return 详细地址
	  */
	Address getAddressById(Integer id);
	/**
	 * 根据数据id删除收获信息
	 * @param id 数据id
	 * @return 如果成功则返回1，否则返回0
	 */
	Integer deleteAddressById(Integer id,Integer uid);
	/**
	 * 根据id设为默认地址
	 * @param id
	 * @param uid
	 * @return
	 */
	Integer setDefault(Integer id,Integer uid);
}
