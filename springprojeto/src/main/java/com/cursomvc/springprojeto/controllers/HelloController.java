package com.cursomvc.springprojeto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //classe de controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";	//a pagina que ele vai procurar, um html chamado hello
	}

}
