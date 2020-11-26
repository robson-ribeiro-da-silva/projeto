package com.robson.projetodesafio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.robson.projetodesafio.model.Candidato;
import com.robson.projetodesafio.service.CandidatoService;


@Controller
@RequestMapping("/candidato")
public class CandidatoController {
	

	@Autowired
	private CandidatoService service;
	
	@GetMapping("/add")
	public ModelAndView add(Candidato candidato) {
		
		ModelAndView mv = new ModelAndView("candidato/form");
		mv.addObject("candidato", candidato);
		mv.addObject("candidatos", service.findByPontuacao());
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Candidato candidato, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(candidato).addObject("error", "Erro ao tentar adiconar o candidato");
		}
		
		service.save(candidato);
		
		return findAll().addObject("success", "Candidato adicionado com sucesso!");
	}
	
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll().addObject("success", "Candidato removido com sucesso!");
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		Candidato candidato = new Candidato();
		
		ModelAndView mv = new ModelAndView("candidato/form");
		mv.addObject("candidato", candidato);
		mv.addObject("candidatos", service.findByPontuacao());
		
		return mv;
	}

	

}
