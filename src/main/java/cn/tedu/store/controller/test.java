package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Province;
import cn.tedu.store.service.IProvinceService;

@Controller
@RequestMapping("/test")
public class test {
	@Autowired
	private IProvinceService provinceService;
	@RequestMapping("/address.do")
	public String testaa(ModelMap moddelMap) {
		
		List<Province> provinces = provinceService.getProvinceList();
		moddelMap.addAttribute("provinces", provinces);
		return "testjsp";
		
	}
}
