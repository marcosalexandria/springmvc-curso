package com.cursomvc.springprojeto.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cursomvc.springprojeto.models.Professor;
import com.cursomvc.springprojeto.models.dto.PostProfessorDTO;
import com.cursomvc.springprojeto.models.enums.StatusProfessor;
import com.cursomvc.springprojeto.repositories.ProfessorRepository;
import com.cursomvc.springprojeto.utils.CaminhoRequestUtil;

import jakarta.validation.Valid;

@Controller
public class ProfessorController {
	
	@Autowired
	ProfessorRepository professorRepository;
	@Autowired
	ModelMapper mapper;
	
	@GetMapping("/professores")
	public ModelAndView index() {

		List<Professor> list = professorRepository.findAll();
		
		ModelAndView mv = new ModelAndView("professor/index");	// a pagina renderizada e manipulada
		mv.addObject("professores", list);
		mv.addObject("caminho", CaminhoRequestUtil.caminho()); // Adiciona o caminho da requisição
		
		return mv;
	}
	
	@GetMapping("/professores/novo")
	public ModelAndView inserirProfessor(PostProfessorDTO postProfessorDTO) {
        
		ModelAndView mv = new ModelAndView("professor/novo");
		mv.addObject("listaStatusProfessor", StatusProfessor.values());
		mv.addObject("caminho", CaminhoRequestUtil.caminho()); // Adiciona o caminho da requisição
		return mv;
	}
	
	@PostMapping("/professores")
	public ModelAndView create(@Valid PostProfessorDTO postProfessorDTO, //Vai ser avaliado
								BindingResult bindingResult) { //objeto que registra se houve algum esso na requisicao
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("caminho", CaminhoRequestUtil.caminho());
        
		if(bindingResult.hasErrors()) {
			
			mv =  new ModelAndView("professor/novo");
			mv.addObject("caminho", CaminhoRequestUtil.caminho());
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			return mv;
		}else {
			Professor professor = new Professor();
			professor = mapper.map(postProfessorDTO, professor.getClass());
			professorRepository.save(professor);
			
			return new ModelAndView("redirect:/professores");
		}
		
	}
}
