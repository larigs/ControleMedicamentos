package control;

import model.*;
import java.util.*;
/**
 * Classe ContorleReceita faz o controle de todos os dados das receitas.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class ControleReceita {
	private List<Receita> receitasPaciente;
	private List<Receita> receitasMedico;
	private List<Integer> posicao = new ArrayList<>();
	/**
	 * Preenche as listas com as receitas de um paciente.
	 * @param d
	 * @param posicaoPaciente
	 * @param posicaoMedico
	 */
	public ControleReceita(ControleDados d, int posicaoPaciente, int posicaoMedico) {
		receitasPaciente = d.getReceitasCadastradasP().get(posicaoPaciente);
		receitasMedico = d.getReceitasCadastradasM().get(posicaoMedico);
	}
	/**
	 * Retorna um array, opcao 0 retorna uma lista com todos os remédios e os horários que precisam ser tomados daquele paciente, 
	 * opcao 1 retorna uma lista com o nome dos medicamentos que o paciente possui receita, se nao for outra opcao retorna uma lista com as receitas que o médico prescreveu.
	 * @return String[]
	 */
	public String[] getListaReceitas(String diaDaSemana, int opcao) {
		String[] listaReceitas;
		if(opcao == 0) { //lista de receitas do paciente separadas pelos dias da semana
			List<String> receitas = new ArrayList<>();
			for(int i = 0; i<receitasPaciente.size(); i++) {
				for(int j = 0; j<receitasPaciente.get(i).getHorarioSemanal().size(); j++) {
					if(receitasPaciente.get(i).getHorarioSemanal().get(j).toString().contains(diaDaSemana) == true) {	
						receitas.add("  "+receitasPaciente.get(i).getHorarioSemanal().get(j).getHoras() + "    " + receitasPaciente.get(i).getMedicamento().getNome());
					}
				}
			}
			
			Collections.sort(receitas);
			listaReceitas = receitas.toArray(String[]::new);	
		} else if (opcao == 1){ // lista de receitas do paciente pelo nome dos medicamentos
			listaReceitas = new String[receitasPaciente.size()];
			for(int i = 0; i<receitasPaciente.size(); i++) {
				listaReceitas[i] = "  "+receitasPaciente.get(i).getMedicamento().getNome();
			}
		}else { // lista de receitas do medico
			listaReceitas = new String[receitasMedico.size()];
			for(int i = 0; i<receitasMedico.size(); i++) {
				listaReceitas[i] = "  " + receitasMedico.get(i).getMedicamento().getNome();
			}
		}
		return listaReceitas;
	}
	/**
	 * Retorna um array com as receitas que possuem o medicamento recebido como parâmetro.
	 * @param medicamento
	 * @return String[]
	 */
	public String[] getListaMedicamentosBusca(String medicamento) {
		String[] listaMedicamentos;
		List<String> nomeReceitas = new ArrayList<>();
		for(int i = 0; i<receitasPaciente.size(); i++) {
			if(receitasPaciente.get(i).getMedicamento().getNome().compareToIgnoreCase(medicamento) == 0) { // se a comparacao der 0, significa que sao iguais
				nomeReceitas.add(" " + medicamento);            // cria lista apenas com o nome do medicamento da receita
				posicao.add(i);
			}	
		}
		listaMedicamentos = nomeReceitas.toArray(String[]::new);	
		return listaMedicamentos;
	}
	
	public List<Receita> getReceitasPaciente() {
		return receitasPaciente;
	}

	public List<Receita> getReceitasMedico() {
		return receitasMedico;
	}

	public Medicamento getMedicamento(int i) {
		return receitasPaciente.get(i).getMedicamento();
	}

	public int getDosesAoDia(int i) {
		return receitasPaciente.get(i).getDosesAoDia();
	}

	public List<HorarioDia> getHorarioSemanal(int i) {
		return receitasPaciente.get(i).getHorarioSemanal();
	}

	public int getDosagemReceitada(int i) {
		return receitasPaciente.get(i).getDosagemReceitada();
	}

	public List<Integer> getPosicao() {
		return posicao;
	}

	public void setPosicao(List<Integer> posicao) {
		this.posicao = posicao;
	}

}
