package com.cursomvc.springprojeto.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cursomvc.springprojeto.models.Professor;
import com.cursomvc.springprojeto.models.dto.PostProfessorDTO;
import com.cursomvc.springprojeto.models.enums.StatusProfessor;
import com.cursomvc.springprojeto.repositories.ProfessorRepository;
import com.cursomvc.springprojeto.utils.CaminhoRequestUtil;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/professores")
public class ProfessorController {
	
	@Autowired
	ProfessorRepository professorRepository;
	@Autowired
	ModelMapper mapper;
	
	@GetMapping
	public ModelAndView index() {

		List<Professor> list = professorRepository.findAll();
		
		ModelAndView mv = new ModelAndView("professor/index");	// a pagina renderizada e manipulada
		mv.addObject("professores", list);
		mv.addObject("caminho", CaminhoRequestUtil.caminho()); // Adiciona o caminho da requisição
		
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView inserirProfessor(PostProfessorDTO postProfessorDTO) {
        
		ModelAndView mv = new ModelAndView("professor/novo");
		mv.addObject("listaStatusProfessor", StatusProfessor.values());
		mv.addObject("caminho", CaminhoRequestUtil.caminho()); // Adiciona o caminho da requisição
		return mv;
	}
	
	@PostMapping
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
			
			return new ModelAndView("redirect:/professores/" + professor.getId());
		}
		
	}
	
	@GetMapping("/{id}")
	public ModelAndView detalhe(@PathVariable Long id) {
		
		try {
			Professor professor = professorRepository.findById(id).get();
			
			ModelAndView mv = new ModelAndView("/professor/detalhe");
			mv.addObject("caminho", CaminhoRequestUtil.caminho());
			mv.addObject("professor", professor); //enviado o professor
			
			
			
			return mv;
		} catch (Exception e) {
			System.out.println("####################################");
			System.out.println(e.getMessage());
			System.out.println("####################################");
			return new ModelAndView("redirect:/professores");
		}

	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id, PostProfessorDTO postProfessorDTO) {
		
		try {
		Professor professor = professorRepository.findById(id).get();
		
		ModelAndView mv = new ModelAndView("/professor/editar");
		mv.addObject("caminho", CaminhoRequestUtil.caminho());
		mv.addObject("postProfessorDTO", mapper.map(professor, postProfessorDTO.getClass()));
		mv.addObject("listaStatusProfessor", StatusProfessor.values());
		mv.addObject("professorId", professor.getId());
		
		return mv;
		
		} catch (Exception e) {
			System.out.println("####################################");
			System.out.println(e.getMessage());
			System.out.println("####################################");
			return new ModelAndView("redirect:/professores");
		}
		
	}
	
	@PostMapping("/{id}")
	public ModelAndView atualizar(@PathVariable Long id, @Valid PostProfessorDTO postProfessorDTO, BindingResult bindingResult) {
		
		try {
			
			if(bindingResult.hasErrors()) {
				ModelAndView mv = new ModelAndView();
				mv =  new ModelAndView("professor/novo");
				mv.addObject("caminho", CaminhoRequestUtil.caminho());
				mv.addObject("listaStatusProfessor", StatusProfessor.values());
				return mv;
			}
			Professor professor = professorRepository.findById(id).get();
			professor.setNome(postProfessorDTO.getNome());
			professor.setSalario(postProfessorDTO.getSalario());
			professor.setStatusProfessor(postProfessorDTO.getStatusProfessor());
			professorRepository.save(professor);
			return new ModelAndView("redirect:/professores/" + professor.getId());
		} catch (Exception e) {
			System.out.println("####################################");
			System.out.println(e.getMessage());
			System.out.println("####################################");
			ModelAndView mv = new ModelAndView("/professor/editar");
			mv.addObject("listaStatusProfessor", StatusProfessor.values());
			return mv;
		}
	}
}
