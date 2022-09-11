package control;

import java.time.*;
import java.util.*;
import model.*;
/**
 * Classe ContorleHorarios faz o controle de todos os horários das receitas.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class ControleHorarios {
	private List<HorarioDia> horarios;
	/**
	 * Preenche uma lista com os horários de uma receita.
	 * @param receita
	 * @param posicaoReceita
	 */
	public ControleHorarios(ControleReceita receita, int posicaoReceita) {
		horarios = receita.getReceitasPaciente().get(posicaoReceita).getHorarioSemanal();
	}
	/**
	 * Retorna um array com todos os horários da receita.
	 * @return String[]
	 */
	public String[] getListaHorarios() {
		String[] listaHorarios = new String[horarios.size()];
		for(int i = 0; i<horarios.size(); i++) {
			listaHorarios[i] = horarios.get(i).toString();
		}
		return listaHorarios;
	}
	/**
	 * Retorna o dia da semana atual em português.
	 * @return
	 */
	public static String getDayOfWeek() {
		String diaSemana = "";
		String datAtual;
	    LocalDate dataAtual = LocalDate.now();
	    datAtual = dataAtual.getDayOfWeek().toString();
	    switch(datAtual) {
	    case "SUNDAY":
	    	diaSemana = "Domingo";
	    	break;
	    case "MONDAY":
	    	diaSemana = "Segunda";
	    	break;
	    case "TUESDAY":
	    	diaSemana = "Terca";
	    	break;
	    case "WEDNESDAY":
	    	diaSemana = "Quarta";
	    	break;
	    case "THURSDAY":
	    	diaSemana = "Quinta";
	    	break;
	    case "FRIDAY":
	    	diaSemana = "Sexta";
	    	break;
	    case "SATURDAY":
	    	diaSemana = "Sabado";
	    	break;
	    }
	    return diaSemana;
	    
	}
	
	public int getHrs(int i) {
		return horarios.get(i).getHrs();
	}

	public int getMin(int i) {
		return horarios.get(i).getMin();
	}

	public String getHoras(int i) {
		return horarios.get(i).getHoras();
	}

	public String[] getDiasEscolhidos(int i) {	 
		return horarios.get(i).getDiasEscolhidos();
	}
	
}
