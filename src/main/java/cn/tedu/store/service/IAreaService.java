package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Area;

public interface IAreaService {
	/**
	 * 根据城市的代号，获取对应的区列表
	 * @param ProvinceCode 区的代号
	 * @return 区列表
	 */
	List<Area> getAreaListByCityCode( String cityCode);
	/**
	 * 根据城市的代号，获取对应的区的信息
	 * @param cityCode 区的代号
	 * @return  区的信息
	 */
	Area getAreaByCode(String areaCode);
}
