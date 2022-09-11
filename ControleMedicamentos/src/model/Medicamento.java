package model;
/**
 * Classe Medicamento contém as informações sobre o remedio.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class Medicamento {
	private String nome;
	private int dosagem;
	private String tipo;
	/**
	 * Construtor Medicamento
	 * @param nome
	 * @param dosagem
	 * @param tipo
	 */
	public Medicamento(String nome, int dosagem, String tipo) {
		this.nome = nome;
		this.dosagem = dosagem;
		this.tipo = tipo;
	}

	public String toString() {
		return nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDosagem() {
		return dosagem;
	}

	public void setDosagem(int dosagem) {
		this.dosagem = dosagem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}