package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.util.List;

import control.*;

public class TelaDetalhesReceita implements ActionListener, ListSelectionListener , ItemListener {
	private static String[] qtd = {"", "1", "2", "3", "4"};
	private ControleDados d;
	private JFrame janela;
	private JLabel titulo, nome, tipo;
	private JTextField valorDosagem, valorHr, valorMin;
	private JComboBox<Object> valorNome, valorMedico, valorDosesDia = new JComboBox<Object>(qtd);
	private JCheckBox dom, seg, ter, qua, qui, sex, sab;
	private JButton botaoSalvar = new JButton("Salvar"), botaoExcluir = new JButton("Excluir"), voltar = new JButton("Voltar");
	private JRadioButton sim = new JRadioButton("Sim", false), nao = new JRadioButton("Não", false);
	private JList<String> listaHorarios = new JList<String>();
	private String[] dadosR = new String[7];
	private int[] dadosH = new int[5];
	private List<JCheckBox> buttons = new ArrayList<>();
	private int posicao;
	private int opcao;
	private int posicaoPaciente;
	private int posicaoReceita;
	
	public TelaDetalhesReceita(ControleDados d, int posicao, int opcao, int posicaoPaciente, int posicaoReceita, String[] dados) {
		this.d=d;
		this.posicaoPaciente = posicaoPaciente;
		this.posicaoReceita = posicaoReceita;
		this.posicao = posicao;
		this.opcao=opcao;
		if(opcao == 1 || opcao == 2 || opcao == 3) { // cadastro receita
			janela = new JFrame("Cadastro Receita");
			titulo = new JLabel("Receita");
			nome = new JLabel("Medicamento:");
			JLabel dosagem = new JLabel("Dosagem receitada:");
			valorDosagem = new JTextField();
			JLabel dosesDia = new JLabel("Tomar            vezes ao dia");
			JLabel medico = new JLabel("Médico receitou: ");
			valorMedico = new JComboBox<Object>(new ControleMedico(d).getListaMedicos());
			valorNome = new JComboBox<Object>(new ControleMedicamento(d).getListaMedicamentos());
			valorNome.addActionListener(this);
			tipo = new JLabel("Comprimido(s)/ml");
			titulo.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			titulo.setBounds(20, 15, 200, 35);
			nome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			nome.setBounds(20, 80, 150, 25);
			dosagem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			dosagem.setBounds(20, 115, 200, 25);
			valorDosagem.setBounds(185, 115, 50, 25);
			
			medico.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			medico.setBounds(20, 150, 300, 25);
			sim.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			sim.setBounds(160, 150, 57, 25);
			nao.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			nao.setBounds(215, 150, 57, 25);
			valorMedico.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			valorMedico.setBounds(275, 150, 84, 25);
			dosesDia.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			dosesDia.setBounds(20, 185, 300, 25);
			valorDosesDia.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			valorDosesDia.setBounds(78, 185, 50, 25);
			
			valorNome.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			valorNome.setBounds(145, 80, 215, 25);
			voltar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			voltar.setBounds(20, 220, 90, 30);
			voltar.setMargin(new Insets(0,0,0,0));
			
			valorMedico.setVisible(false);
			janela.setSize(410, 300);
			janela.add(medico);
			janela.add(valorMedico);
			janela.add(voltar);
			janela.add(sim);
			janela.add(nao);
			janela.add(tipo);
			
			if(opcao == 2 || opcao == 3) { // apresentacao da receita e edicao da receita
				Object nomeMedicamento = d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getMedicamento().getNome();
				valorNome.setSelectedItem(nomeMedicamento);
				valorDosagem.setText(Integer.toString(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getDosagemReceitada()));
				valorDosesDia.setSelectedIndex(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getDosesAoDia());
				JLabel horarios = new JLabel("Horários: ");
				
				if(d.medicoReceitou(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita)) == true) {
					dadosR[5] = "1";
					sim.setSelected(true);
					nao.setVisible(false);
				}else {
					dadosR[5] = "0";
					nao.setSelected(true);
					sim.setVisible(false);
				}
				
				if(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getMedicamento().getTipo() == "mg") {
					tipo.setVisible(false);
					tipo = new JLabel("Comprimido(s)");
				}else {
					tipo.setVisible(false);
					tipo = new JLabel("ml");
				}
				
				botaoSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				botaoSalvar.setBounds(150, 360, 90, 30);
				voltar.setVisible(false);
				
				if(opcao == 2) {
					this.dadosR=dados;
					botaoSalvar.setText("Voltar");
					ControleReceita r = new ControleReceita(d, Integer.parseInt(dadosR[4]), Integer.parseInt(dadosR[5]));
					listaHorarios = new JList<String>(new ControleHorarios(r, r.getReceitasPaciente().size()-1).getListaHorarios());
					janela.add(listaHorarios);
					
				}else if(opcao == 3) {
					nomeMedicamento = d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getMedicamento().getNome();
					valorNome.setSelectedItem(nomeMedicamento);
					valorDosagem.setText(Integer.toString(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getDosagemReceitada()));
					valorDosesDia.setSelectedIndex(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getDosesAoDia());
					dadosR[0] = Integer.toString(valorNome.getSelectedIndex());
					dadosR[1] = valorNome.getSelectedItem().toString();
					dadosR[2] = valorDosesDia.getSelectedItem().toString();
					dadosR[3] = valorDosagem.getText();
					dadosR[4] = Integer.toString(posicaoPaciente);
					dadosR[6] = Integer.toString(valorMedico.getSelectedIndex());
					
					ControleReceita r = new ControleReceita(d, posicaoPaciente, Integer.parseInt(dadosR[5]));
					listaHorarios = new JList<String>(new ControleHorarios(r, posicaoReceita).getListaHorarios());
					botaoExcluir.setFont(new Font("Times New Roman", Font.PLAIN, 18));
					botaoExcluir.setBounds(200, 360, 90, 30);
					botaoSalvar.setBounds(100, 360, 90, 30);
					voltar.setBounds(20, 360, 60, 30);
					
					voltar.setVisible(true);
					janela.add(listaHorarios);
					janela.add(botaoExcluir);
				}
				
				horarios.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				horarios.setBounds(20, 220, 300, 25);
				listaHorarios.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				listaHorarios.setBounds(100, 220, 260, 115);
				
				janela.setSize(410, 450);
				janela.add(horarios);
				janela.add(tipo);
				janela.add(botaoSalvar);
			}
			valorNome.setBackground(Color.white);
			valorMedico.setBackground(Color.white);
			valorDosesDia.setBackground(Color.white);
			
			janela.setLayout(null);
			janela.add(titulo);
			janela.add(nome);
			janela.add(valorNome);
			janela.add(dosagem);
			janela.add(valorDosagem);
			janela.add(dosesDia);
			janela.add(valorDosesDia);
			janela.setVisible(true);
			
		}else if (opcao == 4 || opcao == 5 || opcao == 6) { // cadastro dos horarios
			this.dadosR=dados;
			janela = new JFrame("Cadastro Horario");
			titulo = new JLabel("Horario");
			titulo.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			titulo.setBounds(20, 15, 200, 35);
			int i = d.getHorariosReceita().get(d.getHorariosReceita().size()-1).size();
			valorHr = new JTextField();
			valorMin = new JTextField();
			JLabel dose, hora, dias;

			dose = new JLabel("Dose " + (i+1));
			hora = new JLabel("Hora:             :");
			dias = new JLabel("Nos dias: ");
			dose.setFont(new Font("Times New Roman", Font.PLAIN, 23));
			dose.setBounds(180, 23, 100, 25);
			hora.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			hora.setBounds(20, 70, 150, 25);
			valorHr.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			valorHr.setBounds(70, 70, 40, 25);
			valorMin.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			valorMin.setBounds(118, 70, 40, 25);
			dom = new JCheckBox("Domingo");
			seg = new JCheckBox("Segunda");
			ter = new JCheckBox("Terca");
			qua = new JCheckBox("Quarta");
			qui = new JCheckBox("Quinta");
			sex = new JCheckBox("Sexta");
			sab = new JCheckBox("Sabado");
			dias.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			dias.setBounds(180, 70, 100, 25);
			dom.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			dom.setBounds(255, 70, 100, 25);
			seg.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			seg.setBounds(255, 90, 100, 25);
			ter.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			ter.setBounds(255, 110, 100, 25);
			qua.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			qua.setBounds(255, 130, 100, 25);
			qui.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			qui.setBounds(255, 150, 100, 25);
			sex.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			sex.setBounds(255, 170, 100, 25);
			sab.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			sab.setBounds(255, 190, 100, 25);
			botaoSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			botaoSalvar.setBounds(150, 200, 90, 30);
			buttons.add(dom);
			buttons.add(seg);
			buttons.add(ter);
			buttons.add(qua);
			buttons.add(qui);
			buttons.add(sex);
			buttons.add(sab);
			
			if(opcao == 6) {
				dose = new JLabel("Dose " + i);
				dose.setFont(new Font("Times New Roman", Font.PLAIN, 23));
				dose.setBounds(180, 23, 100, 25);
			}
			if(opcao == 5) { // apresentacao e edicao dos horarios
				dose.setText("Dose " + (posicao+1));
				valorHr.setText(Integer.toString(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getHorarioSemanal().get(posicao).getHrs()));
				valorMin.setText(Integer.toString(d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getHorarioSemanal().get(posicao).getMin()));
				String[] diasSemana = d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getHorarioSemanal().get(posicao).getDiasEscolhidos();
				botaoExcluir.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				botaoSalvar.setBounds(50, 200, 90, 30);
				botaoExcluir.setBounds(150, 200, 90, 30);
				
				for (String diaSemana : diasSemana) {
					switch(diaSemana) {
					case "Domingo":
						dom.setSelected(true);
						break;
					case "Segunda":
						seg.setSelected(true);
						break;
					case "Terca":
						ter.setSelected(true);
						break;
					case "Quarta":
						qua.setSelected(true);
						break;
					case "Quinta":
						qui.setSelected(true);
						break;
					case "Sexta":
						sex.setSelected(true);
						break;
					case "Sabado":
						sab.setSelected(true);
						break;
					}
				}
				janela.add(botaoExcluir);
			}
			dom.setBackground(TelaInicial.c1);
			seg.setBackground(TelaInicial.c1);
			ter.setBackground(TelaInicial.c1);
			qua.setBackground(TelaInicial.c1);
			qui.setBackground(TelaInicial.c1);
			sex.setBackground(TelaInicial.c1);
			sab.setBackground(TelaInicial.c1);
			
			janela.setSize(410, 300);
			janela.setLayout(null);
			janela.add(titulo);
			janela.add(dose);
			janela.add(hora);
			janela.add(dias);
			janela.add(valorHr);
			janela.add(valorMin);
			janela.add(dom);
			janela.add(seg);
			janela.add(ter);
			janela.add(qua);
			janela.add(qui);
			janela.add(sex);
			janela.add(sab);
			janela.add(botaoSalvar);
			janela.setVisible(true);
		}
		janela.getContentPane().setBackground(TelaInicial.c1);
		botaoSalvar.setBackground(TelaInicial.c2);
		botaoSalvar.setForeground(Color.white);
		botaoSalvar.setBorderPainted(false);
		botaoExcluir.setBackground(TelaInicial.c2);
		botaoExcluir.setForeground(Color.white);
		botaoExcluir.setBorderPainted(false);
		voltar.setBackground(TelaInicial.c2);
		voltar.setForeground(Color.white);
		voltar.setBorderPainted(false);
		sim.setBackground(TelaInicial.c1);
		nao.setBackground(TelaInicial.c1);
		
		listaHorarios.addListSelectionListener(this);
		valorDosesDia.addActionListener(this);
		botaoSalvar.addActionListener(this);
		botaoExcluir.addActionListener(this);
		voltar.addActionListener(this);
		sim.addItemListener(this);
		nao.addItemListener(this);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object src = e.getSource();
		if(opcao == 2 || opcao == 3) {
			if(e.getValueIsAdjusting() && src == listaHorarios) {
				new TelaDetalhesReceita(d, listaHorarios.getSelectedIndex(), 5, posicaoPaciente, posicaoReceita, dadosR);
				janela.dispose();
			}	
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object src = e.getSource();
		if (sim.isSelected() && src == sim) {
			dadosR[5] = "1";
		    nao.setSelected(false);   
		    valorMedico.setVisible(true);
		}
		if (nao.isSelected() && src == nao) {
			dadosR[5] = "0";
		    sim.setSelected(false);
		    valorMedico.setVisible(false);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == botaoSalvar) {
			if(opcao == 2) {
				new TelaPrincipal(d, ControleHorarios.getDayOfWeek(), posicaoPaciente);
				janela.dispose();
			}else if(opcao == 3) {
				boolean res;
				int[] posicoes = new int[5];
				dadosR[0] = Integer.toString(valorNome.getSelectedIndex());
				dadosR[1] = valorNome.getSelectedItem().toString();
				dadosR[2] = valorDosesDia.getSelectedItem().toString();
				dadosR[3] = valorDosagem.getText();
				dadosR[4] = Integer.toString(posicaoPaciente);
				dadosR[6] = Integer.toString(valorMedico.getSelectedIndex());
				
				posicoes[0] = 0;
				posicoes[1] = Integer.parseInt(dadosR[5]);
				posicoes[2] = Integer.parseInt(dadosR[4]);
				posicoes[3] = Integer.parseInt(dadosR[6]);	
				posicoes[4] = posicaoReceita;
				
				res = d.cadastrarEditarReceita(dadosR, d.getMedicamento().get(valorNome.getSelectedIndex()), d.getHorariosReceita().get(d.posicaoHorarios(posicaoPaciente, posicaoReceita)), posicoes);
				
				if(res) {
					mensagemSucessoCadastro();
				}else {
					mensagemErroCadastro();
				}
				
				new TelaPrincipal(d, ControleHorarios.getDayOfWeek(), posicaoPaciente);
				janela.dispose();
			}else if(opcao == 4 || opcao == 5 || opcao == 6) { // cadastro de horario 
				if(!valorHr.getText().matches("[0-9]+") || !valorMin.getText().matches("[0-9]+")) {
					mensagemErroCadastro();
				}else {
					String[] diasSemana;
					List<String> dias = new ArrayList<>();
					dadosH[0] = posicaoReceita;
					dadosH[1] =  0;
					dadosH[2] = Integer.parseInt(valorHr.getText());
					dadosH[3] = Integer.parseInt(valorMin.getText());
					dadosH[4] = 0;
	
					for ( JCheckBox checkbox : buttons ) {
					    if( checkbox.isSelected() ){
					        dias.add(checkbox.getText());
					    }
					}
					
					diasSemana = dias.toArray(String[]::new);
					
					if(opcao == 4 && diasSemana.length != 0) {
						boolean res;
						res = d.cadastrarEditarHorariosReceita(dadosH, diasSemana);
						if(res == false) {
							mensagemErroCadastro();
						}else {
							if(d.getHorariosReceita().get(d.getHorariosReceita().size()-1).size() < Integer.parseInt(dadosR[2])) {
								new TelaDetalhesReceita(d, posicao, 4, posicaoPaciente, posicaoReceita, dadosR);
							}else {
								int[] posicoes = new int[4];
								posicoes[0] = 1;
								posicoes[1] = Integer.parseInt(dadosR[5]);
								posicoes[2] = Integer.parseInt(dadosR[4]);
								posicoes[3] = Integer.parseInt(dadosR[6]);	
								
								res = d.cadastrarEditarReceita(dadosR, d.getMedicamento().get(Integer.parseInt(dadosR[0])), d.getHorariosReceita().get(d.getHorariosReceita().size()-1), posicoes);
			
								if(res == false) {
									mensagemErroCadastro();
								}else {
									mensagemSucessoCadastro();
								}
								new TelaDetalhesReceita(d, posicao, 2, posicaoPaciente, (d.getPaciente().get(posicaoPaciente).getReceitas().size()-1), dadosR);
								janela.dispose();
							}
						}
					}else if(opcao == 6 && diasSemana.length != 0) {
						dadosH[0] = d.posicaoHorarios(posicaoPaciente, posicaoReceita);
						dadosH[1] = posicaoPaciente;
						dadosH[2] = Integer.parseInt(valorHr.getText());
						dadosH[3] = Integer.parseInt(valorMin.getText());
						dadosH[4] = 0;
						
						d.cadastrarEditarHorariosReceita(dadosH, diasSemana);
						
						boolean res;
						int[] posicoes = new int[5];
						posicoes[0] = 0;
						posicoes[1] = Integer.parseInt(dadosR[5]);
						posicoes[2] = Integer.parseInt(dadosR[4]);
						posicoes[3] = Integer.parseInt(dadosR[6]);	
						posicoes[4] = posicaoReceita;
						
						res = d.cadastrarEditarReceita(dadosR, d.getMedicamento().get(Integer.parseInt(dadosR[0])), d.getHorariosReceita().get(dadosH[0]), posicoes);
	
						if(res == false) {
							mensagemErroCadastro();
						}else {
							mensagemSucessoCadastro();
							
						}
						
						new TelaDetalhesReceita(d, posicao, 3, posicaoPaciente, posicaoReceita, dadosR);
							
						janela.dispose();
					
				
					}else if(opcao == 5) {
						boolean res;
						dadosH[0] = d.posicaoHorarios(posicaoPaciente, posicaoReceita);
						dadosH[1] = posicao;
						dadosH[2] = Integer.parseInt(valorHr.getText());
						dadosH[3] = Integer.parseInt(valorMin.getText());
						dadosH[4] = 1;
						
						d.cadastrarEditarHorariosReceita(dadosH, diasSemana);

						int[] posicoes = new int[5];
						
						posicoes[0] = 0;
						posicoes[1] = Integer.parseInt(dadosR[5]);
						posicoes[2] = Integer.parseInt(dadosR[4]);
						posicoes[3] = Integer.parseInt(dadosR[6]);
						posicoes[4] = posicaoReceita;
						
						res = d.cadastrarEditarReceita(dadosR, d.getMedicamento().get(Integer.parseInt(dadosR[0])), d.getHorariosReceita().get(dadosH[0]), posicoes);
	
						if(res == false) {
							mensagemErroCadastro();
						}
						new TelaDetalhesReceita(d, posicao, 3, posicaoPaciente, posicaoReceita, dadosR);
						janela.dispose();
					}else {
						mensagemErroCadastro();
					}
				}
			}
		}else if(src == botaoExcluir) {
			if(opcao == 5) {
				d.removerHorario(posicaoPaciente, posicaoReceita, d.posicaoHorarios(posicaoPaciente, posicaoReceita), posicao);
				new TelaDetalhesReceita(d, posicao, 3, posicaoPaciente, posicaoReceita, dadosR);
			}else if(opcao == 3) {
				d.removerReceita(posicaoPaciente, posicaoReceita);
				new TelaPrincipal(d, ControleHorarios.getDayOfWeek(), posicaoPaciente);
				mensagemSucessoCadastro();
			}
			janela.dispose();
			
		}else if(src == voltar){
			new TelaPrincipal(d, ControleHorarios.getDayOfWeek(), posicaoPaciente);
			janela.dispose();
			
		}else if (src == valorDosesDia) { // salva os dados da receita e cria a tela de cadastro dos horarios
			if(opcao == 1) {
				if(valorDosesDia.getSelectedItem().toString() == "" || !valorDosagem.getText().matches("[0-9]+")) {
					mensagemErroCadastro();
					
				}else {
					dadosR[0] = Integer.toString(valorNome.getSelectedIndex());
					dadosR[1] = valorNome.getSelectedItem().toString();
					dadosR[2] = valorDosesDia.getSelectedItem().toString();
					dadosR[3] = valorDosagem.getText();
					dadosR[4] = Integer.toString(posicaoPaciente);
					dadosR[6] = Integer.toString(valorMedico.getSelectedIndex());
					
					d.horarioNovaReceita();
	
					posicaoReceita = d.getHorariosReceita().size()-1;
					
					if(dadosR[5] == null) {
						mensagemErroCadastro();
					}else {
						new TelaDetalhesReceita(d, valorNome.getSelectedIndex(), 4, posicaoPaciente, posicaoReceita, dadosR);
						janela.dispose();
					}
				}	
			}else if(opcao == 3) {
				if(valorDosesDia.getSelectedItem().toString() == "" || !valorDosagem.getText().matches("[0-9]+")) {
					mensagemErroCadastro();
					
				}else {
					dadosR[0] = Integer.toString(valorNome.getSelectedIndex());
					dadosR[1] = valorNome.getSelectedItem().toString();
					dadosR[2] = valorDosesDia.getSelectedItem().toString();
					dadosR[3] = valorDosagem.getText();
					dadosR[4] = Integer.toString(posicaoPaciente);
					dadosR[6] = Integer.toString(valorMedico.getSelectedIndex());
					
					int dose = Integer.parseInt(valorDosesDia.getSelectedItem().toString());
					
					if(dadosR[5] == null || dose <= d.getPaciente().get(posicaoPaciente).getReceitas().get(posicaoReceita).getDosesAoDia()) {
						mensagemErroCadastro();
					}else {
						new TelaDetalhesReceita(d, valorNome.getSelectedIndex(), 6, posicaoPaciente, posicaoReceita, dadosR);
						janela.dispose();
					}
				}	
			}
			

		}else if(src == valorNome) {
			janela.remove(tipo);
			if(d.getMedicamento().get(valorNome.getSelectedIndex()).getTipo() == "mg") {
				tipo = new JLabel("Comprimido(s)");
			}else {
				tipo = new JLabel("ml");
			}
			tipo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			tipo.setBounds(250, 115, 120, 25);
			janela.add(tipo);
			
		}
		
	}
	
	public void mensagemSucessoCadastro() {
		JOptionPane.showMessageDialog(null, "Os dados foram salvos com sucesso!", null, 
				JOptionPane.INFORMATION_MESSAGE);
		janela.dispose();
	}

	public void mensagemErroCadastro() {
		JOptionPane.showMessageDialog(null,"ERRO AO SALVAR OS DADOS!\n "
				+ "Pode ter ocorrido um dos dois erros a seguir:  \n"
				+ "1. Nem todos os campos foram preenchidos \n"
				+ "2. Algum campo foi preenchido incorretamente", null, 
				JOptionPane.ERROR_MESSAGE);
	}

}
