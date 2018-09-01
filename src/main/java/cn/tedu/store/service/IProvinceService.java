package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Province;

public interface IProvinceService {
	/**
	 * 获取所有省列表
	 */
	List<Province> getProvinceList();
	/**
	 * 根据省的代号获取省的信息
	 */
	Province getProvinceByCode(String provinceCode);
}
