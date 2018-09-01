package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Province;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.IProvinceService;
@Controller
@RequestMapping("/province")
public class ProvinceController extends BaseController {
	@Autowired
	private IProvinceService provinceService;
	@RequestMapping("/list.do")
	@ResponseBody
	public ResponseResult<List<Province>> getList(){
		ResponseResult<List<Province>> rr;
		List<Province> province = provinceService.getProvinceList();
		rr = new ResponseResult<List<Province>>(ResponseResult.STATE_OK,province);
		return rr;		
	}
	
	@RequestMapping("/info.do")
	@ResponseBody
	public ResponseResult<Province> getInfo(String code){
		ResponseResult<Province> rr;
		Province province = provinceService.getProvinceByCode(code);
		rr = new ResponseResult<Province>(ResponseResult.STATE_OK,province);
		return rr;
	}
	
	
	
}
