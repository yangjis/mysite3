package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("user/joinForm");
		
		return "user/joinForm";
	}
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("user/join");
		
		userService.join(userVo);
		return "user/joinOk";
	}
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("user/loginForm");
		
		return "user/loginForm";
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("user/login");
		
		UserVo authUser = userService.login(userVo);
		if(authUser != null) {
			System.out.println("로그인 성공");
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		}else {
			System.out.println("로그인 실패");
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	@RequestMapping("/logOut")
	public String logOut(HttpSession session) {
		System.out.println("logOut");
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}

	@RequestMapping("/modifyForm")
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("userController.fodifyForm");
		UserVo vo = (UserVo)session.getAttribute("authUser");
		
		UserVo authUser = userService.getUser(vo.getNo());
		model.addAttribute("authUser", authUser);
		
		return "user/modifyForm";
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("userController.modify");
		
		if(!userVo.getPassword().isEmpty()) {
			UserVo vo = (UserVo)session.getAttribute("authUser");
			userVo.setNo(vo.getNo());
			
			userService.updateUser(userVo);
			
			UserVo authUser = userService.login(userVo);
			
			session.removeAttribute("authUser");
			session.setAttribute("authUser", authUser);
			
			return "redirect:/main";
		}else {
			return "redirect:/user/modifyForm?result=fail";
		}
	}
}