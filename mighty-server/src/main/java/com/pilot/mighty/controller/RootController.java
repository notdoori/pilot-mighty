package com.pilot.mighty.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


// SPA (Single Page Application) 에서 URL 접근 시 페이지가 존재 하지 않아 404 에러 발생 하므로 추가 필요 함.
@Controller
public class RootController implements ErrorController {

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
	
	@GetMapping({"/", "/error"})
	public String getRefresh() {
		return "index.html";
	}

}
