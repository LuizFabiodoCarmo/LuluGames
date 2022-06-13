package com.generation.lulugames.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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
import org.springframework.web.bind.annotation.RestController;

import com.generation.lulugames.model.Categoria;
import com.generation.lulugames.repository.CategoriaRepository;





@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	private CrudRepository<Categoria, Long> produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return categoriaRepository.findById(id).map(resposta->ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/sessao/{sessao}")
	public ResponseEntity<List<Categoria>> getBySessao(@PathVariable String sessao){
		return ResponseEntity.ok(categoriaRepository.
				findAllBySessaoContainingIgnoreCaseOrderBySessao(sessao));
		
	}	
	
	@GetMapping("/descricao/{descricao}") //New test
	public ResponseEntity<List<Categoria>> getByNome(@PathVariable Long descricao) {
		List<Categoria> listCat = categoriaRepository.
				findAllByDescricaoContainingIgnoreCaseOrderByDescricao(descricao);
		if(listCat.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(listCat);
	}
	
	 @PostMapping
	    public ResponseEntity<Categoria> postPostagem(@Valid @RequestBody Categoria categoria){
	 	  
	    if (produtoRepository.existsById(((Categoria) categoria.getProduto()).getId()))	
	    	return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	 	  
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
	    
	    @PutMapping
	    public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria){
	 	  
	    	if (categoriaRepository.existsById(categoria.getId())) {
	    	
	    	if(categoriaRepository.existsById(((Categoria) categoria.getProduto()).getId()))
	    		
	    	return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
	 	
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
	  
	     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	    }	
	    @DeleteMapping("/{id}")
		public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
			return categoriaRepository.findById(id).map(resposta -> {
				categoriaRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}).orElse(ResponseEntity.notFound().build());
	  }
	
	
}



