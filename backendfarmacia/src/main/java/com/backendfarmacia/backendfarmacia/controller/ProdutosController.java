package com.backendfarmacia.backendfarmacia.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backendfarmacia.backendfarmacia.model.ProdutoModel;
import com.backendfarmacia.backendfarmacia.repository.ProdutoRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	public ProdutoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> getall(){
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{nome}")
	public List<ProdutoModel> getByName(@PathVariable String nome){
		return repository.findAllByNomeContainingIgnoreCase(nome);
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<ProdutoModel>> getById(@PathVariable long id){
		return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));
	}
	@PostMapping
	public ResponseEntity<ProdutoModel> postProduto(@RequestBody ProdutoModel produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	@PutMapping
	public ResponseEntity<ProdutoModel> putProduto(@RequestBody ProdutoModel produto){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	@DeleteMapping
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	@GetMapping("/menorque")
	public ResponseEntity<List<ProdutoModel>> getMenorrQue(@RequestParam BigDecimal preco){
		return new ResponseEntity<List<ProdutoModel>>(repository.findByPrecoLessThanEqual(preco), HttpStatus.OK);
	}
	@GetMapping("/maiorque")
	public ResponseEntity<List<ProdutoModel>> getMaiorQue(@RequestParam BigDecimal preco){
		return new ResponseEntity<List<ProdutoModel>>(repository.findByPrecoGreaterThanEqual(preco), HttpStatus.OK);
	}
}
