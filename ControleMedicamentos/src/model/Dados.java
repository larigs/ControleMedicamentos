package model;

import java.util.*;
/**
 * Classe Dados simula um banco de dados.
 * @author Larissa Gomes
 * @since 2022
 * @version 1.0
 */
public class Dados {
	private List<Paciente> paciente = new ArrayList<Paciente>();
	private List<Medico> medico = new ArrayList<Medico>();
	private List<Medicamento> medicamento = new ArrayList<Medicamento>();
	private List<HorarioDia> horarioReceita;
	private List<List<HorarioDia>> horariosReceitas = new ArrayList<List<HorarioDia>>();
	private List<Receita> receitas = new ArrayList<>();
	private List<List<Receita>> receitasCadastradasP = new ArrayList<List<Receita>>();
	private List<List<Receita>> receitasCadastradasM = new ArrayList<List<Receita>>();
	
	public void cadastrosProntos() {
		//Paciente 1
		medicamento.add(new Medicamento("Remedio 1", 10, "mg"));
		String[] diasDose1 = {"Segunda","Quarta","Sexta"};
		String[] diasDose2 = {"Terca","Quinta"};
		horarioReceita = new ArrayList<>(Arrays.asList(new HorarioDia(10, 30, "10:30", diasDose1), new HorarioDia(20, 30, "20:30", diasDose2)));
		horariosReceitas.add(horarioReceita);
		receitasCadastradasP.add(receitas);
		Receita receita1 = new Receita(medicamento.get(0), 2, horariosReceitas.get(0), 1);
		receitasCadastradasP.get(0).add(receita1);
		receitas = new ArrayList<>();
		receitasCadastradasM.add(receitas);
		receitasCadastradasM.get(0).add(receitasCadastradasP.get(0).get(0));
		paciente.add(new Paciente("Paciente 1", 20, "feminino", receitasCadastradasP.get(0)));
		medico.add(new Medico("Medico 1", 123456789, receitasCadastradasM.get(0)));
		
		//Paciente 2 mesmo medicamento da receita1 e medico diferente
		receitas = new ArrayList<>();
		String[] diasDose3 = {"Domingo","Terca","Sexta"};
		horarioReceita =  new ArrayList<>(Arrays.asList(new HorarioDia(12, 30, "12:30", diasDose3)));
		horariosReceitas.add(horarioReceita);	
		receitasCadastradasP.add(receitas);
		Receita receita2 = new Receita(medicamento.get(0), 1, horariosReceitas.get(1), 3);
		receitasCadastradasP.get(1).add(receita2);
		receitas = new ArrayList<>();
		receitasCadastradasM.add(receitas);
		receitasCadastradasM.get(1).add(receitasCadastradasP.get(1).get(0));
		paciente.add(new Paciente("Paciente 2", 20, "masculino", receitasCadastradasP.get(1)));
		medico.add(new Medico("Medico 2", 123456789, receitasCadastradasM.get(1)));

		//Paciente 1 segunda receita
		medicamento.add(new Medicamento("Remedio 2", 20, "ml"));
		String[] diasDose4 = {"Terca","Quinta"};
		String[] diasDose5 = {"Terca","Quinta"};
		String[] diasDose6 = {"Terca","Quinta"};
		horarioReceita =  new ArrayList<>(Arrays.asList(new HorarioDia(7, 30, "07:30", diasDose4), new HorarioDia(15, 30, "15:30", diasDose5), new HorarioDia(23, 30, "23:30", diasDose6)));
		horariosReceitas.add(horarioReceita);
		Receita receita3 = new Receita(medicamento.get(1), 3, horariosReceitas.get(2), 2);
		receitasCadastradasP.get(0).add(receita3);
		
		//Paciente 3 mesmo medico da receita1
		receitas = new ArrayList<>();
		medicamento.add(new Medicamento("Remedio 3", 10, "mg"));
		String[] diasDose7 = {"Domingo"};
		horarioReceita =  new ArrayList<>(Arrays.asList(new HorarioDia(12, 30, "12:30", diasDose7)));
		horariosReceitas.add(horarioReceita);
		receitasCadastradasP.add(receitas);
		Receita receita4 = new Receita(medicamento.get(2), 1, horariosReceitas.get(3), 2);
		receitasCadastradasP.get(2).add(receita4);
		receitasCadastradasM.get(0).add(receitasCadastradasP.get(2).get(0));
		paciente.add(new Paciente("Paciente 3", 20, "nao binarie", receitasCadastradasP.get(2)));
		
		//Paciente 3 segunda receita mesmo medicamento da primeira receita do paciente 1
		String[] diasDose8 = {"Segunda","Quarta"};
		String[] diasDose9 = {"Segunda","Quarta"};
		horarioReceita = Arrays.asList(new HorarioDia(7, 30, "07:30", diasDose8), new HorarioDia(17, 30, "17:30", diasDose9));
		horariosReceitas.add(horarioReceita);
		Receita receita5 = new Receita(medicamento.get(0), 2, horariosReceitas.get(4), 2);
		receitasCadastradasP.get(2).add(receita5);
	}
	
	public List<Paciente> getPaciente() {
		return paciente;
	}
	
	public void setPaciente(List<Paciente> paciente) {
		this.paciente = paciente;
	}
	
	public List<Medico> getMedico() {
		return medico;
	}
	
	public void setMedico(List<Medico> medico) {
		this.medico = medico;
	}
	
	public List<Medicamento> getMedicamento() {
		return medicamento;
	}
	
	public void setMedicamento(List<Medicamento> medicamento) {
		this.medicamento = medicamento;
	}
	
	public List<HorarioDia> getHorarioReceita() {
		horarioReceita = new ArrayList<>();
		return horarioReceita;
	}
	
	public void setHorarioReceita(List<HorarioDia> horarioReceita) {
		this.horarioReceita = horarioReceita;
	}
	
	public List<List<HorarioDia>> getHorariosReceitas() {
		return horariosReceitas;
	}
	
	public void setHorariosReceitas(List<List<HorarioDia>> horariosReceitas) {
		this.horariosReceitas = horariosReceitas;
	}
	
	public List<Receita> getReceitas() {
		receitas = new ArrayList<>();
		return receitas;
	}
	
	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}
	
	public List<List<Receita>> getReceitasCadastradasP() {
		return receitasCadastradasP;
	}
	
	public void setReceitasCadastradasP(List<List<Receita>> receitasCadastradasP) {
		this.receitasCadastradasP = receitasCadastradasP;
	}
	
	public List<List<Receita>> getReceitasCadastradasM() {
		return receitasCadastradasM;
	}
	
	public void setReceitasCadastradasM(List<List<Receita>> receitasCadastradasM) {
		this.receitasCadastradasM = receitasCadastradasM;
	}
	/**
	 * Armazena o paciente na lista de pacientes.
	 * @param p
	 * @param posicao
	 * @param d
	 */
	public void cadastrarPaciente(Paciente p, int posicao, Dados d) {
		if(posicao == d.getPaciente().size()) {
			this.paciente.add(p);
		}else{
			this.paciente.set(posicao, p);
		}
		
	}
	/**
	 * Armazena o médico na lista de médicos.
	 * @param m
	 * @param posicao
	 * @param d
	 */
	public void cadastrarMedico(Medico m, int posicao, Dados d) {
		if(posicao == d.getMedico().size()) {
			this.medico.add(m);
		}else {
			this.medico.set(posicao, m);
		}
	}
	/**
	 * Armazena o medicamento na lista de medicamentos.
	 * @param m
	 * @param posicao
	 * @param d
	 */
	public void cadastrarMedicamento(Medicamento m, int posicao, Dados d) {
		if(posicao == d.getMedicamento().size()) {
			this.medicamento.add(m);
		}else {
			this.medicamento.set(posicao, m);
		}
	}
	/**
	 * Armazena o horário na posição referente ao paciente.
	 * @param hr
	 * @param posicaoReceita
	 * @param posicao
	 * @param opcao
	 * @param d
	 */
	public void cadastrarHorarioDia(HorarioDia hr, int posicaoReceita,  int posicao, int opcao, Dados d) {
		if(opcao == 0) {
			this.horariosReceitas.get(posicaoReceita).add(hr);
		}else{
			this.horariosReceitas.get(posicaoReceita).set(posicao, hr);
			
		}
	}
	/**
	 * Armazena a receita na posição referente ao paciente e medico.
	 * @param r
	 * @param posicao
	 */
	// 0 - cadastro = 1 e edicao = 0, 1 - medico receitou, 2 - posicao do paciente, 3 - posicao do medico, 4 e 5 - posicao da receita a ser alterada
	public void cadastrarReceita(Receita r, int[] posicao) {
		if(posicao[0] == 1 && posicao[1] == 1) {
			this.receitasCadastradasP.get(posicao[2]).add(r);
			this.receitasCadastradasM.get(posicao[3]).add(r);
			
		}else if(posicao[0] == 1 && posicao[1] == 0) {
			this.receitasCadastradasP.get(posicao[2]).add(r);
			
		}else if(posicao[0] == 0 && posicao[1] == 1) {
			for(int i = 0; i<this.receitasCadastradasM.get(posicao[3]).size(); i++) {
				// a comparação retorna 0 quando são iguais
				Receita receita = this.receitasCadastradasM.get(posicao[3]).get(i);
				if(receita.toString().compareTo(this.receitasCadastradasP.get(posicao[2]).get(posicao[4]).toString()) == 0) {
					this.receitasCadastradasM.get(posicao[3]).set(i, r);
				}
			}
			this.receitasCadastradasP.get(posicao[2]).set(posicao[4], r);
			
		}else {
			this.receitasCadastradasP.get(posicao[2]).set(posicao[4], r);
		}
	}

}
