package com.cursomvc.springprojeto.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cursomvc.springprojeto.models.Professor;
import com.cursomvc.springprojeto.models.enums.StatusProfessor;

@Controller
public class ProfessorController {
	
	@GetMapping("/professores")
	public ModelAndView index() {
		Professor prof1 = new Professor("Batnam", new BigDecimal(5000.0), StatusProfessor.ATIVO);
		prof1.setId(1L);
		Professor prof2 = new Professor("Coringa", new BigDecimal(10000.0), StatusProfessor.INATIVO);
		prof2.setId(2L);
		Professor prof3 = new Professor("Mulher Maravilha", new BigDecimal(15000.0), StatusProfessor.APOSENTADO);
		prof3.setId(3L);
		
		List<Professor> list = Arrays.asList(prof1, prof2, prof3);
		
		ModelAndView mv = new ModelAndView("professor/index");	// a pagina renderizada e manipulada
		mv.addObject("professores", list);
		
		return mv;
	}
}
