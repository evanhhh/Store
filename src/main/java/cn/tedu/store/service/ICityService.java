package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.City;

public interface ICityService {
	/**
	 * 根据省的代号，获取对应的城市列表
	 * @param ProvinceCode 
	 * @return 城市列表
	 */
	List<City> getCityListByProvinceCode( String ProvinceCode);
	/**
	 * 根据省的代号，获取对应的城市的信息
	 * @param cityCode 城市的代号
	 * @return  城市的信息
	 */
	City getCityByCode(String cityCode);
}
