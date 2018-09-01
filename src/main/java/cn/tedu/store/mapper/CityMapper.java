package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.City;

public interface CityMapper {
	/**
	 * 根据省的代号，获取对应的市的列表
	 * @param provinceCode 省的代号
	 * @return 市的列表
	 */
	List<City> getCityListByProvinceCode(String provinceCode);

	/**
	 * 根据市的代号，获取市的信息
	 * @param provinceCode 市的代号
	 * @return 市的信息
	 */
	City getCityByCode(String cityCode);
	
}
