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


/**
 * @author robso
 *Classe responsável por realisar a interação e o acesso através de requisições entre a camada model e a view.
 *Métodos implementados que salvam ou consutam registros no banco de dados
 *
 */
@Controller
@RequestMapping("/candidato")
public class CandidatoController {
	

	@Autowired
	private CandidatoService service;
	
	//Método que direciona ao formulário para adicionar um novo elementos 
	@GetMapping("/add")
	public ModelAndView add(Candidato candidato) {
		
		ModelAndView mv = new ModelAndView("candidato/form");
		mv.addObject("candidato", candidato);
		mv.addObject("candidatos", service.findByPontuacao());
		
		return mv;
	}
	
	//Método que recebe a requisição para salvar o elemento no banco
	@PostMapping("/save")
	public ModelAndView save(@Valid Candidato candidato, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(candidato).addObject("error", "Erro ao tentar adiconar o candidato");
		}
		
		service.save(candidato);
		
		return findAll().addObject("success", "Candidato adicionado com sucesso!");
	}
	
	//Método que permite a edição de um elemento
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	//Método que deleta um registro no banco
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll().addObject("success", "Candidato removido com sucesso!");
	}
	
	//Método que realiza uma consulta de todos os elementos no banco e direciona para a view de exibição
	//Obs: O candidatos são listados de acordo com a pontuação
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		Candidato candidato = new Candidato();
		
		ModelAndView mv = new ModelAndView("candidato/form");
		mv.addObject("candidato", candidato);
		mv.addObject("candidatos", service.findByPontuacao());
		
		return mv;
	}

}
