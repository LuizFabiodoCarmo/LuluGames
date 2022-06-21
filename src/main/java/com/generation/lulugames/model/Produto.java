package com.generation.lulugames.model;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
	
@Table( name = "tb_produtos")
 
	public class Produto {
		
		@Id
		@GeneratedValue(strategy = GenerationType. IDENTITY)
		private Long id;
		
		@NotNull(message = "É obrigatório que o produto tenha um nome!")
		@Size(min = 5, max = 55, message = "O atributo deve conter no mínimo 5 e no máximo 100 caracteres!")
		private Long nome;
		

		@NotNull(message = "É obrigatório que o produto tenha uma descrição!")
		@Size(min = 5, max = 55, message = "O atributo deve conter no mínimo 5 e no máximo 100 caracteres!")
		private Long descricao;
		
		@NotBlank(message = "É obrigatório que o produto tenha uma foto!")
		private String foto;
		
		@NotBlank(message = "É obrigatório que o produto tenha um preço")
		@Digits(integer = 4, fraction = 2, message = "O preço aceitará no máximo milhares e duas casas depois do ponto!")
		@Positive(message = "O preço deverá ser maior que zero!")
		private BigDecimal preco;
		
		@ManyToOne
		@JsonIgnoreProperties("produto")
		private Categoria categoria;

		/*Como acrescenta quantidade sem que de erro?*/
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getNome() {
			return nome;
		}

		public void setNome(Long nome) {
			this.nome = nome;
		}

		public Long getDescricao() {
			return descricao;
		}

		public void setDescricao(Long descricao) {
			this.descricao = descricao;
		}

		public String getFoto() {
			return foto;
		}

		public void setFoto(String foto) {
			this.foto = foto;
		}

		public BigDecimal getPreco() {
			return preco;
		}

		public void setPreco(BigDecimal preco) {
			this.preco = preco;
		}
		
		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}
		
		
		
		
  }

