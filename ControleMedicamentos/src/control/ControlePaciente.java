package control;

import java.util.*;
import model.*;
/**
 * Classe ContorlePaciente faz o controle de todos os dados dos pacientes.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class ControlePaciente {
	private List<Paciente> pacientes;
	/**
	 * Construtor ControlePaciente
	 * Preenche uma lista com todos os pacientes cadastrados.
	 * @param d
	 */
	public ControlePaciente(ControleDados d) {
		pacientes = d.getPaciente();
	}
	/**
	 * Retorna um array com o nome de todos os pacientes.
	 * @return String[]
	 */
	public String[] getListaPacientes() {
		String[] listaPacientes = new String[pacientes.size()];
		for(int i = 0; i<pacientes.size(); i++) {
			listaPacientes[i] = ("  "+pacientes.get(i).getNome());
		}
		return listaPacientes;
	}

	public String getNome(int i) {
		return pacientes.get(i).getNome();
	}

	public List<Receita> getReceitas(int i) {
		return pacientes.get(i).getReceitas();
	}

	public int getIdade(int i) {
		return pacientes.get(i).getIdade();
	}

	public String getGenero(int i) {
		return pacientes.get(i).getGenero();
	}
	
}
