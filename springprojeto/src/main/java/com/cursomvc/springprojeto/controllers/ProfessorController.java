package com.cursomvc.springprojeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cursomvc.springprojeto.models.Professor;
import com.cursomvc.springprojeto.models.enums.StatusProfessor;
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
	
	@GetMapping("/professores/novo")
	public ModelAndView inserirProfessor() {
		
		ModelAndView mv = new ModelAndView("professor/novo");
		mv.addObject("statusProfessor", StatusProfessor.values());
		return mv;
	}
	
	@PostMapping("/professores")
	public String create(Professor professor) {
		
		System.out.println();
		System.out.println(professor);
		System.out.println();
		
		return "redirect:/professores";
	}
}
