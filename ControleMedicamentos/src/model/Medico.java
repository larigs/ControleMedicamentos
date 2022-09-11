package model;

import java.util.*;
/**
 * Classe Medico contém os dados do médico e herda da Classe Pessoa.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class Medico extends Pessoa {
	private int crm;
	/**
	 * Construtor Medico.
	 * @param nome
	 * @param crm
	 * @param receitas
	 */
	public Medico(String nome, int crm, List<Receita> receitas) {
		super(nome, receitas);
		this.crm = crm;
	}

	public String toString() {
		return "Medico - nome:" + nome+ ", CRM: " + crm + ", receitas: " + receitas;
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

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}
	
}
