package model;

import java.util.*;
/**
 * Classe abstrata Pessoa que contém os elementos em comum entre medico e paciente.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public abstract class Pessoa {
	protected String nome;
	protected List<Receita> receitas;
	
	/**
	 * Construtor Pessoa
	 * @param nome
	 * @param receitas
	 */
	public Pessoa(String nome, List<Receita> receitas) {
		this.nome = nome;
		this.receitas = receitas;
	}

	@Override
	public String toString() {
		return nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	
	
}
