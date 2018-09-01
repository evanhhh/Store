package cn.tedu.store.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.controller.BaseController;
import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Province;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IProvinceService;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{
	@Autowired
	private IProvinceService provinceService;
	@Autowired
	private IAddressService addressService;
	@RequestMapping("/list.do")
	public String showList(ModelMap modelMap ,HttpSession session) {	
		// 获取省的列表
		List<Province> provinces
		= provinceService.getProvinceList();
		// 获取当前登录的用户的uid
		Integer uid = getUidFromSession(session);
		// 获取收货地址列表
		List<Address> addresses = addressService.getAddressListByUid(uid);
		// 封装省的列表，以准备转发
		modelMap.addAttribute("provinces", provinces);
		// 封装收货地址列表，以准备转发
		modelMap.addAttribute("addresses", addresses);
		// 执行转发
		return "address";

	}
	@RequestMapping("/addnew.do")
	public String handleAddnew(Address address,
			HttpSession session) {
		// 此次省略N多数据有效性的判断  封装uid
		Integer uid = getUidFromSession(session);
		address.setUid(uid);
		// 执行增加
		addressService.addnew(address);
		// 完成，重定向
		return "redirect:list.do";
	}
	@RequestMapping("/delete.do")
	public String handlerDelete(Integer id,HttpSession session) {
		Integer uid = getUidFromSession(session);
		addressService.deleteAddressById(id, uid);
		//删除后重定向到列表
		return "redirect:list.do";
	}
}