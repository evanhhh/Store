package cn.tedu.store.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tedu.store.controller.BaseController;
import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Province;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IProvinceService;
import cn.tedu.store.service.ex.ServiceException;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{
	@Autowired
	private IProvinceService provinceService;
	@Autowired
	private IAddressService addressService;
	@RequestMapping("/list.do")
	public String showList(Integer id,String action,ModelMap modelMap ,HttpSession session) {	
		String actionUrl;
		if (!"edit".equals(action)) {
			action = "addnew";
			actionUrl = "addnew.do";
		} else {
			action = "edit";
			actionUrl = "edit.do";
		}
		// 如果此次是修改操作，需要读取对应的数据
		Address address = addressService.getAddressById(id);
		// 获取省的列表
		List<Province> provinces= provinceService.getProvinceList();
			

		// 获取当前登录的用户的uid
		Integer uid = getUidFromSession(session);
		// 获取收货地址列表
		List<Address> addresses = addressService.getAddressListByUid(uid);
		// 封装所修改的收货地址数据
		modelMap.addAttribute("address", address);
		// 封装当前页面表单的操作类型和提交位置
		modelMap.addAttribute("action", action);
		modelMap.addAttribute("actionUrl", actionUrl);
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
	@RequestMapping("/set_default.do")
	public String handleSetDefault(@RequestParam("id") Integer id,HttpSession session ,ModelMap modelMap) {
		Integer uid = getUidFromSession(session);
		try {
			addressService.setDefault(id, uid);
			return "redirect:list.do";
	
		} catch (ServiceException e) {
			//封装错误信息
			modelMap.addAttribute("err-msg",e.getMessage());
			//转发到专门的错误页面
			return "error";
		}
		
	}





}