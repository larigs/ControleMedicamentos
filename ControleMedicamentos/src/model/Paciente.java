package model;

import java.util.*;
/**
 * Classe Paciente contém os dados do paciente e herda da Classe Pessoa.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class Paciente extends Pessoa {
	private int idade;
	private String genero;
	/**
	 * Construtor Paciente.
	 * @param nome
	 * @param idade
	 * @param genero
	 * @param receitas
	 */
	public Paciente(String nome, int idade, String genero, List<Receita> receitas) {
		super(nome, receitas);
		this.idade = idade;
		this.genero = genero;
	}
	/**
	 * Sobrecarga do construtor Paciente sem o dado de gênero.
	 * @param nome
	 * @param idade
	 * @param receitas
	 */
	public Paciente(String nome, int idade, List<Receita> receitas) {
		super(nome, receitas);
		this.idade = idade;
	}

	@Override
	public String toString() {
		return nome + receitas;
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	

}
