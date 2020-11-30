package com.robson.projetodesafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robson.projetodesafio.model.Candidato;
import com.robson.projetodesafio.repository.CandidatoRepository;


/**
 * @author robso
 * Classe da camada de serviços, implementa os métodos da camada repository
 * 
 */
@Service
public class CandidatoService {
	
	@Autowired
	private CandidatoRepository repository;
	
	public List<Candidato> findAll() {
		return repository.findAll();
	}
	
	public Candidato findOne(Long id) {
		return repository.getOne(id);
	}
	
	public Candidato save(Candidato candidato) {
		return repository.saveAndFlush(candidato);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Candidato> findByPontuacao() {
		return repository.findByPontuacao();
	}
	
	public Candidato findByCpf(String cpf){
		return repository.findByCpf(cpf);
	}
	
	public Candidato findByNome(String nome){
		return repository.findByNome(nome);
	}

}
