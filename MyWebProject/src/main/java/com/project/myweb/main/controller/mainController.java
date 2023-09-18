package com.project.myweb.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class mainController {
	
	@GetMapping("")
	public String mainPage(HttpSession session) {
		Integer sessionValue =(Integer) session.getAttribute("loginId");
		if(sessionValue == null || sessionValue == 0 ) {
			return "redirect:/member/member-signin";
		}
		return "/main/main";
	}
}
