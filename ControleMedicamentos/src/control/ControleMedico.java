package control;

import java.util.List;

import model.*;
/**
 * Classe ContorleMedico faz o controle de todos os dados dos médicos.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class ControleMedico {
	private List<Medico> medicos;
	/**
	 * Construtor ControleMedico
	 * Preenche uma lista com todos os médicos cadastrados.
	 * @param d
	 */
	public ControleMedico(ControleDados d) {
		medicos = d.getMedico();
	}
	/**
	 * Retorna um array com o nome de todos os médicos.
	 * @return String[]
	 */
	public String[] getListaMedicos() {
		String[] listaMedicos = new String[medicos.size()];
		for(int i = 0; i<medicos.size(); i++) {
			listaMedicos[i] = "  " + medicos.get(i).getNome();
		}
		return listaMedicos;
	}

	public String getNome(int i) {
		return medicos.get(i).getNome();
	}

	public List<Receita> getReceitas(int i) {
		return medicos.get(i).getReceitas();
	}

	public int getCrm(int i) {
		return medicos.get(i).getCrm();
	}

}
