package control;

import java.util.*;
import model.*;
/**
 * Classe ContorleMedicamento faz o controle de todos os dados dos medicamentos.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class ControleMedicamento {
	public List<Medicamento> medicamentos;
	/**
	 * Construtor ControleMedicamento
	 * Preenche uma lista com todos os medicamentos cadastrados.
	 * @param d
	 */
	public ControleMedicamento(ControleDados d) {
		medicamentos = d.getMedicamento();
	}
	/**
	 * Retorna um array com o nome de todos os medicamento.
	 * @return String[]
	 */
	public String[] getListaMedicamentos() {
		String[] listaMedicamentos = new String[medicamentos.size()];
		for(int i = 0; i<medicamentos.size(); i++) {
			listaMedicamentos[i] = medicamentos.get(i).getNome();
		}
		return listaMedicamentos;
	}
	
	public String getNome(int i) {
		return medicamentos.get(i).getNome();
	}

	public int getDosagem(int i) {
		return medicamentos.get(i).getDosagem();
	}

	public String getTipo(int i) {
		return medicamentos.get(i).getTipo();
	}
	
}
