package com.robson.projetodesafio.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robson.projetodesafio.model.Candidato;
import com.robson.projetodesafio.service.CandidatoService;


/**
 * @author robso
 * Classe responsável por permitir acesso ao banco de dados da aplicação 
 * através de requisições http que tem como resposta um json como os dados
 * de acordo com o que cada método implementado permite retornar. 
 *
 */
@RestController
@RequestMapping("/api/candidato")
public class CandidatoResource {

	@Autowired
	private CandidatoService service;
	
	//"Retorna uma lista com todos candidatos cadastrados"
	@GetMapping(value="/findAll", produces="application/json")
	public ResponseEntity<List<Candidato>> findAll(){
		
		List<Candidato> candidatos = service.findAll();
		
		if(candidatos.isEmpty()){
			return ResponseEntity.notFound().build();		
		}
		return ResponseEntity.ok(candidatos);
	}
		
	//"Retorna uma lista com todos candidatos ordenados pela pontuação"
	@GetMapping(value="/findByPontuacao", produces="application/json")
	public ResponseEntity<List<Candidato>> findByPontuacao(){
		
		List<Candidato> candidatos = service.findByPontuacao();
		
		if(candidatos.isEmpty()){
			return ResponseEntity.notFound().build();		
		}
		return ResponseEntity.ok(candidatos);
	}
}
