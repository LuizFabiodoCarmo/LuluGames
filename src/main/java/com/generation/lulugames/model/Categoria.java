package com.generation.lulugames.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table( name = "tb_categorias")
public class Categoria {
       
	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private Long id;
	
	@NotNull(message = "É obrigatório o preenchimento da sessão do produto!")
	@Size(min = 5, max = 55, message = "O atributo deve conter no mínimo 5 e no máximo 55 caracteres!")
	private String sessao;
	
    @Column(name= "nome" )
	@NotNull(message = "É obrigatório que o produto tenha um nome!")
	@Size(min = 5, max = 100, message = "O atributo deve conter no mínimo 5 e no máximo 100 caracteres!")
	private String nome;
	
	@OneToMany (mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties ("categoria")
	private List <Produto> produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessao() {
		return sessao;
	}

	public void setSessao(String sessao) {
		this.sessao = sessao;
	}

	public String getNome() {
		return nome;
	}

	public void setPromocao(String nome) {
		this.nome = nome;
	}
	
	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	
}