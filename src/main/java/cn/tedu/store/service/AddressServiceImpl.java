package cn.tedu.store.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.ex.DataNotExistsException;
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
	
	@Transactional
	public Integer deleteAddressById(Integer id,Integer uid) {
		//确定删除时的数据
		Address data = getAddressById(id);
		//确定删除时的条件
		String where = "id="+id +" and uid="+uid;
		Integer rows = addressMapper.delete(where);
		//判断是否删除成功
		if (rows == 0) {
			throw new DataNotExistsException("删除失败！尝试删除的数据不存在！请联系系统管理员！");
		}
		//判断删除的是否是默认地址
		if(data.getIsDefault()==1) {
			//如果删除的是默认的地址，则需要把剩下的地址id值最大的设置未默认地址
			//根据id的倒序查询找出id最大的数据
			where ="uid="+uid;
			String orderBy = "id desc";
			Integer countPerPage = 1;
			List<Address> list = addressMapper.select(where, orderBy, null, countPerPage);
			//判断是否找到数据,如果集合大小大于0表示找到剩下的数据
			if(list.size()>0) {
				//取到第一个数据
				data = list.get(0);
				//取出这条数据的id，用于设置默认地址
				Integer dataId = data.getId();
				//将这条设置为默认地址
				Address address= new Address();
				address.setId(dataId);
				address.setUid(uid);
				address.setIsDefault(1);
				rows = addressMapper.update(address);
				//再次判断是否修改默认地址成功
				if(rows==0) {
					//失败
					throw new DataNotExistsException("设置默认地址失败，请联系管理员");
				}else {
					//成功，返回
					return 1;
				}
			}
		}

		return rows;
	}
	
	@Transactional//使用事务的注解
	public Integer setDefault(Integer id,Integer uid) {
		Address address = new Address();
		address.setIsDefault(0);
		address.setUid(uid);
		//将该uid的所有收获地址设置为非默认地址
		Integer rows = addressMapper.update(address);
		if(rows>0) {
			//如果执行行数大于0说明设置成功，接下来进行第二步操作
			address.setIsDefault(1);
			address.setId(id);
			rows=addressMapper.update(address);
			//判断是否执行成功
			if(rows>0) {
				return rows;
			}else {
				throw new DataNotExistsException("修改失败[1]，尝试修改的数据不存在！");
			}
		}else {
			//受影响的行数为0，说明不成功，可能是用户非法访问url，或者管理员删除
			throw new DataNotExistsException("修改失败[2]，尝试修改的数据不存在！");
		}

	}

}
