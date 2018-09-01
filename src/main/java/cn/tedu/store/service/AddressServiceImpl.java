package cn.tedu.store.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
@Service("addressService")
public class AddressServiceImpl implements IAddressService{
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private IProvinceService provinceService ;
	@Autowired
	private ICityService cityService;
	@Autowired
	private IAreaService areaService;
	public Address addnew(Address address) {
		StringBuffer recvDistrict = new StringBuffer();
		String  provinceName = provinceService.getProvinceByCode(address.getRecvProvince()).getName();
		String cityName = cityService.getCityByCode(address.getRecvCity()).getName();
		String areaName = areaService.getAreaByCode(address.getRecvArea()).getName();
		recvDistrict.append(provinceName);
		recvDistrict.append(cityName);
		recvDistrict.append(areaName);
		address.setRecvDistrict(recvDistrict.toString());
		//封装日志
		Date date = new Date();
		address.setCreatedTime(date);
		address.setCreatedUser("system");
		address.setModifiedTime(date);
		address.setModifiedUser("system");
		//先获得该用户的所有收获地址
		List<Address> list = getAddressListByUid(address.getUid());
		// 【新增】确定是否为默认收货地址
		// 。在执行此次增加之前，该用户还没有任何收货地址信息,则此次增加的收货地址应该是默认的
		if(list.size()==0) {
			address.setIsDefault(1);
		}else {
			//往后如果再增加地址都不是默认地址。
			address.setIsDefault(0);
		}

		//执行增加
		addressMapper.insert(address);
		return address;
	}
	
	public List<Address> getAddressListByUid(Integer uid) {
		String where = "uid="+uid;
		String orderBy = "is_default desc,id desc";
		List<Address> list = addressMapper.select(where, orderBy, null, null);
		return list;
	}
	public Address getAddressById(Integer id) {
		String where = "id="+id;
		List<Address> list = addressMapper.select(where, null, null, null);
		if(list.size()==0) {
			return null;
		}else {
			return list.get(0);
		}
	}
	
	public Integer deleteAddressById(Integer id,Integer uid) {
		String where = "id="+id +" and uid="+uid;
		Integer rows = addressMapper.delete(where);
		//TODO 万一把默认删除了，哪个是默认地址？
		return rows;
	}

}
