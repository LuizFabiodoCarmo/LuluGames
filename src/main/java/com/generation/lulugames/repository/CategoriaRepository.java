package com.generation.lulugames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.lulugames.model.Categoria;


@Repository
public interface CategoriaRepository  extends JpaRepository <Categoria, Long>{

	public List <Categoria> findAllBySessaoContainingIgnoreCaseOrderBySessao
	(@Param("sessao") String sessao);
}
