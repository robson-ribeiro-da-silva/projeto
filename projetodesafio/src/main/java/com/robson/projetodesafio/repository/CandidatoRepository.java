package com.robson.projetodesafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.robson.projetodesafio.model.Candidato;


/**
 * @author robso
 * Classe que permitir a implementação de métodos para realização de consultas no banco de dados (querys)
 */
@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
	
	@Query(value = "select * from candidato order by nota desc", nativeQuery = true)
	public List<Candidato> findByPontuacao();
	
	@Query
	public Candidato findByCpf(String cpf);
	
	@Query
	public Candidato findByNome(String nome);
}
