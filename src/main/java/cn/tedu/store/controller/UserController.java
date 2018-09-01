package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UsernameConflictException;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;

	@RequestMapping("/reg.do")
	public String showReg() {
		return "register";	
	}

	@RequestMapping("/login.do")
	public String showLogin() {
		return "login";	
	}
	
	@RequestMapping("/logout.do")
	public String handleLogout(HttpSession session) {
		//清除session中的信息
		session.invalidate();
		//重定向到首页
		return "redirect:../main/index.do";	
	}

	@RequestMapping("/change_password.do")
	public String showChangePassword() {
		return "user_password";

	}
	
	@RequestMapping("/change_info.do")
	public String showChangeInfo(ModelMap modelMap,HttpSession session) {
		//从session中获取当前用户的id
		Integer id = getUidFromSession(session);
		//根据id找到当前用户的信息
		User user = userService.findUserById(id);
		//判断是否获取到用户数据，因为登陆后，数据被管理员删除
		if(user != null) {
			//将数据封装到modelMap，转发到前端页面
			modelMap.addAttribute("user", user);
			//执行转发
			return "user_info";	
		}else {
			//找不到用户
			//执行重定向
			return "redirect:../mian/error.do";
		}

	}

	@RequestMapping(value="/handle_reg.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleReg(String username,String password,String phone,
			String email,HttpSession session){
		ResponseResult<Void> rr;
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmail(email);
		try {
			User u = userService.reg(user);
			session.setAttribute("uid",u.getId() );
			session.setAttribute("username", u.getUsername());
			rr=new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (UsernameConflictException e) {
			rr=new ResponseResult<Void>(e);
		}
		return rr;	
	}

	@RequestMapping(value="/handle_login.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> Login(String username,String password,HttpSession session) {
		ResponseResult<Void> rr;
		try {
			User user = userService.login(username, password);
			session.setAttribute("uid",user.getId() );
			session.setAttribute("username", user.getUsername());
			rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (ServiceException e) {
			rr= new ResponseResult<Void>(e);		
		}
		return rr;
	}

	@RequestMapping(value="/handle_change_password.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleChangePassword(String oldPassword,String newPassword,String confirmPassword,HttpSession session){
		ResponseResult<Void> rr;
		if(newPassword != null && 
				newPassword.equals(confirmPassword) &&
				newPassword.length() >= 6 && newPassword.length() <= 16) {
			try {
				//从session中获取当前登陆用户的id
				Integer id = getUidFromSession(session);
				//执行修改密码
				userService.changePassword(id, oldPassword, newPassword);
				rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
			} catch (ServiceException e) {
				rr = new ResponseResult<Void>(e);
			}		
		}else {
			//输入的新密码有误
			rr = new ResponseResult<Void>(ResponseResult.STATE_ERR,"两次输入的新密码不一致！");
		}
		return rr;
	}

	@RequestMapping(value="/handle_change_info.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleChangeInfo(String username, Integer gender,String phone, String email,
			HttpSession session) {
		// 检查数据的有效性
		if ("".equals(username)) {
			username = null;
		}
		// 获取session中的uid
		Integer id = getUidFromSession(session);
		// 声明返回值
		ResponseResult<Void> rr;
		try {
			// 执行修改
			userService.changeInfo(id, username, gender, phone, email);
			rr = new ResponseResult<Void>(ResponseResult.STATE_OK);
		} catch (ServiceException e) {
			rr = new ResponseResult<Void>(e);
		}
		// 返回
		return rr;
	}



}
