package model;

import java.util.*;
/**
 * Classe HorarioDia contém o horário e os dias da semana que o remédio deve ser tomado.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class HorarioDia {
	private int hrs;
	private int min;
	private String horas;
	private String[] diasEscolhidos;
	/**
	 * Construtor HorarioDia.
	 * @param hrs
	 * @param min
	 * @param horas
	 * @param diasEscolhidos
	 */
	public HorarioDia(int hrs, int min, String horas, String[] diasEscolhidos) {
		this.hrs = hrs;
		this.min = min;
		this.horas = horas;
		this.diasEscolhidos = diasEscolhidos;
	}

	@Override
	public String toString() {
		return "  " + horas + "   "+ Arrays.toString(diasEscolhidos);
	}

	public int getHrs() {
		return hrs;
	}

	public void setHrs(int hrs) {
		this.hrs = hrs;
	}
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public String[] getDiasEscolhidos() {	 
		return diasEscolhidos;
	}

	public void setDiasEscolhidos(String[] diasEscolhidos) {
		this.diasEscolhidos = diasEscolhidos;
	}

	
}
