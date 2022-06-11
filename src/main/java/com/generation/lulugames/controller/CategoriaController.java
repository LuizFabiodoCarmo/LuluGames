package com.generation.lulugames.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

import com.generation.lulugames.model.Categoria;
import com.generation.lulugames.repository.CategoriaRepository;





@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepostory;
	private CrudRepository<Categoria, Long> produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(categoriaRepostory.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return categoriaRepostory.findById(id).map(resposta->ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/sessao/{sessao}")
	public ResponseEntity<List<Categoria>> getBySessao(@PathVariable String sessao){
		return ResponseEntity.ok(categoriaRepostory.
				findAllBySessaoContainingIgnoreCaseOrderBySessao(sessao));
		
	}	
	 @PostMapping
	    public ResponseEntity<Categoria> postPostagem(@Valid @RequestBody Categoria categoria){
	 	  
	    if (produtoRepository.existsById(((Categoria) categoria.getProduto()).getId()))	
	    	return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepostory.save(categoria));
	 	  
	     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
	    
	    @PutMapping
	    public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria){
	 	  
	    	if (categoriaRepostory.existsById(categoria.getId())) {
	    	
	    	if(categoriaRepostory.existsById(((Categoria) categoria.getProduto()).getId()))
	    		
	    	return ResponseEntity.status(HttpStatus.OK).body(categoriaRepostory.save(categoria));
	 	
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
	  
	     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	    }	
	    @DeleteMapping("/{id}")
	    public void deleteCategoria(@PathVariable Long id) {
	        Optional<Categoria>  resposta = categoriaRepostory.findById(id);
	    	if (resposta.isPresent()) {
	    	categoriaRepostory.deleteById(id);
	    	
	    	} else {
	    		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	    			
	    	}    	
	    	
	  }
	
	
}



