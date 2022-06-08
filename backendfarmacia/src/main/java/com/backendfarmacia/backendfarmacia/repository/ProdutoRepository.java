package com.backendfarmacia.backendfarmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendfarmacia.backendfarmacia.model.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long>{
	List<ProdutoModel> findAllByNomeContainingIgnoreCase(String nome);
	List<ProdutoModel> findByPrecoLessThanEqual(BigDecimal preco);
	List<ProdutoModel> findByPrecoGreaterThanEqual(BigDecimal preco);
}
