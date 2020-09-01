package com.produto.springboot.repository;

import java.util.List;

import com.produto.springboot.model.Pessoa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
    @Query("Select a from Pessoa a where a.nome like %?1%")
    List<Pessoa> findPessoaByNome(String nome);
    
}