package com.cursomvc.springprojeto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cursomvc.springprojeto.utils.CaminhoRequestUtil;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
        mv.addObject("caminho",CaminhoRequestUtil.caminho()); // Adiciona o caminho da requisição
		return mv;
	}
}
