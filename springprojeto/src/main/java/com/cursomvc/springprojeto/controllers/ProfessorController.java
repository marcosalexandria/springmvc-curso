package com.cursomvc.springprojeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cursomvc.springprojeto.models.Professor;
import com.cursomvc.springprojeto.repositories.ProfessorRepository;

@Controller
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@GetMapping("/professores")
	public ModelAndView index() {
		
		List<Professor> list = professorRepository.findAll();
		
		ModelAndView mv = new ModelAndView("professor/index");	// a pagina renderizada e manipulada
		mv.addObject("professores", list);
		
		return mv;
	}
}
