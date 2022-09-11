package model;

import java.util.*;
/**
 * Classe Receita contém a forma de uso e os horarios do medicamento.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class Receita {
	private Medicamento medicamento;
	private int dosesAoDia;
	private List<HorarioDia> horarioSemanal;
	private int dosagemReceitada;
	/**
	 * Construtor Medicamento.
	 * @param medicamento
	 * @param dosesAoDia
	 * @param horarioSemanal
	 * @param dosagemReceitada
	 */
	public Receita(Medicamento medicamento, int dosesAoDia,
			List<HorarioDia> horarioSemanal, int dosagemReceitada) {
		this.medicamento = medicamento;
		this.dosesAoDia = dosesAoDia;
		this.horarioSemanal = horarioSemanal;
		this.dosagemReceitada = dosagemReceitada;
	}
	
	 public String toString() {
		return medicamento.getNome() + horarioSemanal; 
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public int getDosesAoDia() {
		return dosesAoDia;
	}

	public void setDosesAoDia(int dosesAoDia) {
		this.dosesAoDia = dosesAoDia;
	}

	public List<HorarioDia> getHorarioSemanal() {
		return horarioSemanal;
	}

	public void setHorarioSemanal(List<HorarioDia> horarioSemanal) {
		this.horarioSemanal = horarioSemanal;
	}

	public int getDosagemReceitada() {
		return dosagemReceitada;
	}

	public void setDosagemReceitada(int dosagemReceitada) {
		this.dosagemReceitada = dosagemReceitada;
	}

}
