package control;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import model.*;
/**
 * Classe ControleDados faz o controle de todos os dados cadastrados.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class ControleDados {
	private Dados d = new Dados();
	/**
	 * Construtor ControleDados
	 * Cadastra os dados criados na Classe Dados.
	 */
	public ControleDados() {
		d.cadastrosProntos();
	}
	
	public Dados getD() {
		return d;
	}

	public void setD(Dados d) {
		this.d = d;
	}
	/**
	 * Retorna a lista de pacientes cadastrados.
	 * @return List<Paciente>
	 */
	public List<Paciente> getPaciente() {
		return this.d.getPaciente();
	}
	/**
	 * Retorna a lista de médicos cadastrados.
	 * @return List<Medico>
	 */
	public List<Medico> getMedico() {
		return this.d.getMedico();
	}
	/**
	 * Retorna a lista de medicamentos cadastrados.
	 * @return List<Medicamento>
	 */
	public List<Medicamento> getMedicamento() {
		return this.d.getMedicamento();
	}
	/**
	 * Retorna a lista vazia para ser adicionado os horários da receita.
	 * @return List<HorarioDia>
	 */
	public List<HorarioDia> getHorarioDia() {
		return this.d.getHorarioReceita();
	}
	/**
	 * Retorna a lista dos horários das receitas.
	 * Cada posição da lista se refere a uma receita diferente.
	 * @return List<List<HorarioDia>>
	 */
	public List<List<HorarioDia>> getHorariosReceita() {
		return this.d.getHorariosReceitas();
	}
	/**
	 * Retorna uma lista vazia para ser adicionada as receitas do paciente.
	 * @return List<Receita>
	 */
	public List<Receita> getReceitas() {
		return this.d.getReceitas();
	}
	/**
	 * Retorna a lista de receitas cadastradas dos pacientes.
	 * Cada posição da lista se refere a um paciente diferente.
	 * @return List<List<Receita>>
	 */
	public List<List<Receita>> getReceitasCadastradasP() {
		return this.d.getReceitasCadastradasP();
	}
	/**
	 * Retorna a lista de receitas cadastradas dos médicos.
	 * Cada posição da lista se refere a um médico diferente.
	 * @return List<List<Receita>>
	 */
	public List<List<Receita>> getReceitasCadastradasM() {
		return this.d.getReceitasCadastradasM();
	}
	/**
	 * Adiciona uma nova lista vazia a lista horariosReceitas.
	 */
	public void horarioNovaReceita() {
		d.getHorariosReceitas().add(d.getHorarioReceita());
	}
	/**
	 * Retorna TRUE caso um médico receitou a receita recebida como parâmetro e FALSE se não.
	 * @param receita
	 * @return boolean
	 */
	public boolean medicoReceitou(Receita receita) {
		if(d.getReceitasCadastradasM().toString().contains(receita.toString()) == true) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Retorna a posição da lista de horários de uma receita na lista horariosReceitas.
	 * @param posicaoPaciente
	 * @param posicaoReceita
	 * @return int
	 */
	public int posicaoHorarios(int posicaoPaciente, int posicaoReceita) {
		return d.getHorariosReceitas().indexOf(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getHorarioSemanal());
	}
	/**
	 * Retorna TRUE se foi possível cadastrar ou editar o paciente e FALSE se não.
	 * @param dados
	 * @return boolean
	 */
	public boolean cadastrarEditarPaciente(String[] dados){
		if(Integer.parseInt(dados[0]) == d.getPaciente().size()) {
			d.getReceitasCadastradasP().add(d.getReceitas());
		}
		if(!dados[2].matches("[0-9]+")) {
			return false;
		}else if (dados[3] != "") {
			Paciente p = new Paciente(dados[1], Integer.parseInt(dados[2]), dados[3], d.getReceitasCadastradasP().get(Integer.parseInt(dados[0])));
			d.cadastrarPaciente(p, Integer.parseInt(dados[0]), d);
			return true;
		} else {
			Paciente p = new Paciente(dados[1], Integer.parseInt(dados[2]), d.getReceitasCadastradasP().get(Integer.parseInt(dados[0])));
			d.cadastrarPaciente(p, Integer.parseInt(dados[0]), d);
			return true;
		}
	}
	/**
	 * Retorna TRUE se foi possível cadastrar ou editar o médico e FALSE se não.
	 * @param dados
	 * @return boolean
	 */
	public boolean cadastrarEditarMedico(String[] dados){
		if(Integer.parseInt(dados[0]) == d.getMedico().size()) {
			d.getReceitasCadastradasM().add(d.getReceitas());
		}
		
		if(!dados[2].matches("[0-9]+")) {
			return false;
		}else {
			Medico m = new Medico(dados[1], Integer.parseInt(dados[2]), d.getReceitasCadastradasM().get(Integer.parseInt(dados[0])));
			
			d.cadastrarMedico(m, Integer.parseInt(dados[0]), d);
			return true;
		}
	}
	/**
	 * Retorna TRUE se foi possível cadastrar ou editar o medicamento e FALSE se não.
	 * @param dados
	 * @return boolean
	 */
	public boolean cadastrarEditarMedicamento(String[] dados){
		if(!dados[2].matches("[0-9]+") || d.getReceitasCadastradasP().toString().contains(dados[1])) {
			return false;
		}else if((Integer.parseInt(dados[0]) != d.getMedicamento().size()) && d.getReceitasCadastradasP().toString().contains(d.getMedicamento().get(Integer.parseInt(dados[0])).getNome())){
			return false;
		}else {
			Medicamento m = new Medicamento(dados[1], Integer.parseInt(dados[2]), dados[3]);
			d.cadastrarMedicamento(m, Integer.parseInt(dados[0]), d);
			return true;
		}
	}
	/**
	 * Retorna TRUE se foi possível cadastrar ou editar a receita e FALSE se não.
	 * @param dados
	 * @param med
	 * @param horarios
	 * @param posicao
	 * @return boolean
	 */
	public boolean cadastrarEditarReceita(String[] dados, Medicamento med, List<HorarioDia> horarios, int[] posicao){
		if(!dados[2].matches("[0-9]+") || !dados[3].matches("[0-9]+")) {
			return false;
		}else {
			Receita r = new Receita(med, Integer.parseInt(dados[2]), horarios, Integer.parseInt(dados[3]));
			d.cadastrarReceita(r, posicao);
			return true;
		}
	}
	/**
	 * Cadastra ou edita um horário de receita.
	 * @param dados
	 * @param diasEscolhidos
	 */
	public boolean cadastrarEditarHorariosReceita(int[] dados, String[] diasEscolhidos){
		if(dados[2]>24 || dados[3]>59) {
			return false;
		}else {
			SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm");
			Calendar hora = Calendar.getInstance();
			hora.set(Calendar.HOUR_OF_DAY, dados[2]);
			hora.set(Calendar.MINUTE, dados[3]);
			String horas = formatarHora.format(hora.getTime());
			HorarioDia hr = new HorarioDia(dados[2], dados[3], horas, diasEscolhidos);
			d.cadastrarHorarioDia(hr, dados[0], dados[1], dados[4], d);
			return true;
		}
		
	}
	/**
	 * Exclui um paciente.
	 * @param posicaoPaciente
	 */
	public void removerPaciente(int posicaoPaciente) {
		for(int j=0; j<d.getPaciente().get(posicaoPaciente).getReceitas().size(); j++) {
			d.getHorariosReceitas().remove(d.getHorariosReceitas().indexOf(d.getPaciente().get(posicaoPaciente).getReceitas().get(j).getHorarioSemanal()));
		}
		d.getReceitasCadastradasP().remove(d.getReceitasCadastradasP().indexOf(d.getPaciente().get(posicaoPaciente).getReceitas()));
		d.getPaciente().remove(posicaoPaciente);
	}
	/**
	 * Exclui um médico.
	 * @param posicao
	 */
	public void removerMedico(int posicao) {
		d.getReceitasCadastradasM().remove(d.getReceitasCadastradasM().indexOf(d.getMedico().get(posicao).getReceitas()));
		d.getMedico().remove(posicao);
	}
	/**
	 * Exclui um medicamento se não estiver cadastrado em nenhuma receita.
	 * @param posicao
	 * @return boolean
	 */
	public boolean removerMedicamento(int posicao) {
		String receitas = d.getReceitasCadastradasP().toString();
		if(receitas.contains(d.getMedicamento().get(posicao).getNome())) {
			return false;
		}else {
			d.getMedicamento().remove(posicao);
			return true;
		}
	}
	/**
	 * Exclui uma receita.
	 * @param posicaoPaciente
	 * @param posicaoReceita
	 */
	public void removerReceita(int posicaoPaciente, int posicaoReceita) {
		d.getHorariosReceitas().remove(d.getHorariosReceitas().indexOf(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getHorarioSemanal()));
		d.getReceitasCadastradasP().get(posicaoPaciente).remove(d.getReceitasCadastradasP().get(posicaoPaciente).indexOf(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita)));
		if(d.getReceitasCadastradasM().toString().contains(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).toString()) == true) {
			for(int k=0; k<d.getReceitasCadastradasM().size(); k++) {
				if(d.getReceitasCadastradasM().get(k).toString().contains(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).toString()) == true) {
					d.getReceitasCadastradasM().get(k).remove(d.getReceitasCadastradasM().get(k).indexOf(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita)));
					break;
				}
			}
		}
	}
	/**
	 * Exclui um horário da receita.
	 * @param posicaoPaciente
	 * @param posicaoReceita
	 * @param posicaoHorario
	 * @param posicao
	 */
	public void removerHorario(int posicaoPaciente, int posicaoReceita, int posicaoHorario, int posicao) {
		d.getHorariosReceitas().get(posicaoHorario).remove(posicao);
		Receita r =d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita);
		r.setDosesAoDia(r.getDosesAoDia()-1);
	}
}
