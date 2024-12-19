package com.crud.batata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Batata")
public class BatataModel {
	
	//define o atributo 'id' como a chave primária da tabela, com geração automatica dos valores
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	
	///define os atributos da entidade
	private String tipo;//tipo da batata.
	private String Origem;//origem da batata.
	private double preco;//preço da batata.

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getOrigem() {
		return Origem;
	}

	public void setOrigem(String origem) {
		Origem = origem;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
		
		
}
