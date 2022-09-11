package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.event.*;

import control.*; // editar e excluir receita, salvar receita qnd opcao 6 retornar a tela principal, implementar a visualizacao da receita na tela principal na busca, quando visualiza o paciente e o medico. testar para ver se n ta faltando nada

public class TelaDetalhes implements ActionListener{
	private static String[] tipos = {"mg", "ml"};
	private JFrame janela;
	private JLabel titulo, nome;
	private JTextField valorNome, valorIdade, valorGenero, valorCRM, valorDosagem;
	private JComboBox<Object> valorTipo = new JComboBox<Object>(tipos);
	private JButton botaoSalvar = new JButton("Salvar"), botaoExcluir = new JButton("Excluir"), voltar = new JButton("Voltar");
	private JList<String> listaReceitas = new JList<String>();
	private String[] dadosP, dadosM, dadosMed;
	private ControleDados d;
	private int posicao;
	private int opcao;
	
	public TelaDetalhes(ControleDados d, int posicao, int opcao) {
		this.d=d;
		this.posicao = posicao;
		this.opcao=opcao;
		if(opcao == 0 || opcao == 1) { //cadastrar ou editar paciente
			janela = new JFrame("Cadastro Paciente");
			titulo = new JLabel("Paciente");
			nome = new JLabel("Nome:");
			valorNome = new JTextField();
			JLabel idade = new JLabel("Idade:");
			valorIdade = new JTextField();
			JLabel genero = new JLabel("Genero:");
			valorGenero = new JTextField();
			JLabel receitas = new JLabel("Receitas:");
			
			titulo.setFont(new Font("Times New Roman", Font.PLAIN, 33));
			titulo.setBounds(20, 15, 140, 35);
			nome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			nome.setBounds(20, 80, 150, 25);
			valorNome.setBounds(90, 80, 280, 25);
			idade.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			idade.setBounds(20, 130, 150, 25);
			valorIdade.setBounds(90, 130, 70, 25);
			genero.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			genero.setBounds(175, 130, 120, 25);
			valorGenero.setBounds(250, 130, 120, 25);
			receitas.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			receitas.setBounds(20, 180, 120, 25);
			botaoSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			botaoSalvar.setBounds(150, 200, 90, 30);
			voltar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			voltar.setBounds(20, 350, 60, 30);
			voltar.setMargin(new Insets(0,0,0,0));
			

			janela.setLayout(null);
			janela.add(botaoSalvar);
			janela.add(titulo);
			janela.add(nome);
			janela.add(valorNome);
			janela.add(idade);
			janela.add(valorIdade);
			janela.add(genero);
			janela.add(valorGenero);
			janela.setSize(410, 300);
			janela.setVisible(true);
			
			if(opcao == 1) {
				valorNome.setText(d.getPaciente().get(posicao).getNome());
				valorNome.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				valorIdade.setText(Integer.toString(d.getPaciente().get(posicao).getIdade()));
				valorIdade.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				valorGenero.setText(d.getPaciente().get(posicao).getGenero());
				valorGenero.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				listaReceitas = new JList<String>(new ControleReceita(d, posicao, 0).getListaReceitas("", 1));
				listaReceitas.setBounds(110, 180, 260, 130);
				botaoSalvar.setBounds(100, 350, 90, 30);
				botaoExcluir.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				botaoExcluir.setBounds(200, 350, 90, 30);
				
				listaReceitas.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				listaReceitas.setVisibleRowCount(10);
				listaReceitas.setFont(new Font("Calibri Light", Font.PLAIN, 18));
				
				janela.setSize(410, 450);
				janela.add(botaoExcluir);
				janela.add(voltar);
				janela.add(receitas);
				janela.add(listaReceitas);
				
			}
			
		}else if(opcao == 2 || opcao == 3) { // cadastrar ou editar medico
			
			janela = new JFrame("Cadastro Medico");
			titulo = new JLabel("Medico");
			nome = new JLabel("Nome:");
			valorNome = new JTextField();
			JLabel crm = new JLabel("CRM:");
			valorCRM = new JTextField();
			JLabel receitas = new JLabel("Receitas:");
			
			titulo.setFont(new Font("Times New Roman", Font.PLAIN, 33));
			titulo.setBounds(20, 15, 140, 35);
			nome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			nome.setBounds(20, 80, 150, 25);
			valorNome.setBounds(90, 80, 280, 25);
			crm.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			crm.setBounds(20, 130, 150, 25);
			valorCRM.setBounds(90, 130, 150, 25);
			receitas.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			receitas.setBounds(20, 180, 120, 25);
			botaoSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			botaoSalvar.setBounds(150, 200, 90, 30);

			janela.setLayout(null);
			janela.add(botaoSalvar);
			janela.add(titulo);
			janela.add(nome);
			janela.add(valorNome);
			janela.add(crm);
			janela.add(valorCRM);
			janela.setSize(410, 300);
			janela.setVisible(true);
			
			if(opcao == 3) {
				valorNome.setText(d.getMedico().get(posicao).getNome());
				valorNome.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				valorCRM.setText(Integer.toString(d.getMedico().get(posicao).getCrm()));
				valorCRM.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				listaReceitas = new JList<String>(new ControleReceita(d, 0, posicao).getListaReceitas("", 2));
				listaReceitas.setBounds(110, 180, 260, 130);
				botaoSalvar.setBounds(100, 350, 90, 30);
				botaoExcluir.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				botaoExcluir.setBounds(200, 350, 90, 30);
				
				listaReceitas.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				listaReceitas.setVisibleRowCount(10);
				listaReceitas.setFont(new Font("Calibri Light", Font.PLAIN, 18));
				
				janela.setSize(410, 450);
				janela.add(botaoExcluir);
				janela.add(receitas);
				janela.add(listaReceitas);
			}
		}else if(opcao == 4 || opcao == 5) { // cadastrar ou editar medicamento
			janela = new JFrame("Cadastro Medicamento");
			titulo = new JLabel("Medicamento");
			nome = new JLabel("Nome:");
			valorNome = new JTextField();
			JLabel dosagem = new JLabel("Dose:");
			valorDosagem = new JTextField();
			JLabel tipo = new JLabel("Tipo:");
			
			titulo.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			titulo.setBounds(35, 15, 200, 35);
			nome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			nome.setBounds(35, 80, 150, 25);
			valorNome.setBounds(105, 80, 245, 25);
			dosagem.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			dosagem.setBounds(35, 130, 150, 25);
			valorDosagem.setBounds(100, 130, 70, 25);
			tipo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			tipo.setBounds(220, 130, 120, 25);
			valorTipo.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			valorTipo.setBounds(270, 130, 80, 25);
			botaoSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			botaoSalvar.setBounds(150, 200, 90, 30);
			
			if(opcao == 5) {
				valorNome.setText(d.getMedicamento().get(posicao).getNome());
				valorDosagem.setText(Integer.toString(d.getMedicamento().get(posicao).getDosagem()));
				Object tipoRemedio = d.getMedicamento().get(posicao).getTipo();
				valorTipo.setSelectedItem(tipoRemedio);
				botaoExcluir.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				botaoExcluir.setBounds(200, 200, 90, 30);
				botaoSalvar.setBounds(100, 200, 90, 30);
				
				janela.add(botaoExcluir);
			}
			janela.setLayout(null);
			janela.add(botaoSalvar);
			janela.add(titulo);
			janela.add(nome);
			janela.add(valorNome);
			janela.add(dosagem);
			janela.add(valorDosagem);
			janela.add(tipo);
			janela.add(valorTipo);
			janela.setSize(410, 300);
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
		valorTipo.setBackground(Color.white);
		
		botaoSalvar.addActionListener(this);
		botaoExcluir.addActionListener(this);
		voltar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == botaoSalvar) {
			if(opcao == 0 || opcao == 1) { // cadastro ou edicao de paciente
				try {
					boolean res;
					dadosP = new String[4];
					dadosP[0] = Integer.toString(posicao);
					dadosP[1] =  valorNome.getText();
					dadosP[2] =  valorIdade.getText();
					if(valorGenero.getText() != "") {
						dadosP[3] =  valorGenero.getText();
					}
					
					res = d.cadastrarEditarPaciente(dadosP);
	
					if(res) {
						mensagemSucessoCadastro();
					}else {
						mensagemErroCadastro();
					}
						
	
				} catch (NullPointerException exc1) {
					mensagemErroCadastro();
				} catch (NumberFormatException exc2) {
					mensagemErroCadastro();
				}
				if (opcao == 0) {
					new TelaItensCadastrados(d, 1);
				}else {
					new TelaPrincipal(d, ControleHorarios.getDayOfWeek(), posicao);
				}
					
				
			}else if(opcao == 2 || opcao == 3) { // cadastro ou edicao do medico
				try {
					boolean res;
					dadosM = new String[3];
					dadosM[0] =  Integer.toString(posicao);
					dadosM[1] =  valorNome.getText();
					dadosM[2] =  valorCRM.getText();
					
					res = d.cadastrarEditarMedico(dadosM);
	
					if(res) {
						mensagemSucessoCadastro();
					}else {
						mensagemErroCadastro();
					}
						
	
				} catch (NullPointerException exc1) {
					mensagemErroCadastro();
				} catch (NumberFormatException exc2) {
					mensagemErroCadastro();
				}
			}else if(opcao == 4 || opcao == 5) { // cadastro ou edicao de medicamento
				try {
					boolean res;
					dadosMed = new String[4];
					dadosMed[0] = Integer.toString(posicao);
					dadosMed[1] =  valorNome.getText();
					dadosMed[2] =  valorDosagem.getText();
					dadosMed[3] =  valorTipo.getSelectedItem().toString();
					
					res = d.cadastrarEditarMedicamento(dadosMed);
	
					if(res) {
						mensagemSucessoCadastro();
						janela.dispose();
					}else {
						mensagemErroCadastro();
					}
						
	
				} catch (NullPointerException exc1) {
					mensagemErroCadastro();
				} catch (NumberFormatException exc2) {
					mensagemErroCadastro();
				}
				
			}
		}else if(src == botaoExcluir) {
			if(opcao == 1) { // remover paciente
				d.removerPaciente(posicao);
				new TelaItensCadastrados(d, 1);
				janela.dispose();
			}else if(opcao == 3) { // remover medico
				d.removerMedico(posicao);
				janela.dispose();
			}else if(opcao == 5) { // remover medicamento
				boolean res;
				res = d.removerMedicamento(posicao);

				if(res) {
					mensagemSucessoCadastro();
				}else {
					mensagemErroCadastro();
				}

			}
			
		}else if(src == voltar){
			new TelaPrincipal(d, ControleHorarios.getDayOfWeek(), posicao);
			janela.dispose();
			
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
				+ "2. Algum campo foi preenchido incorretamente\n"
				+ "3. O medicamento informado já existe"
				+ "4. O cadastro nao pode ser editado pois está registrado em uma receita", null, 
				JOptionPane.ERROR_MESSAGE);
	}

	

}
