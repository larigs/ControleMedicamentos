package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import control.*;

public class TelaItensCadastrados implements ActionListener, ListSelectionListener {
	private ControleDados d;
	private JFrame janela;
	private JLabel titulo;
	private JButton adicionar;
	private JList<String> listaPacientes;
	private JList<String> listaMedicos;
	private JList<String> listaMedicamentos;
	private int opcao;
	
	public TelaItensCadastrados(ControleDados d, int opcao) {
		this.d=d;
		this.opcao=opcao;
		
		switch(opcao) { // lista com todos os medicos cadastrados
		case 0:
			janela = new JFrame("Medicos Cadastrados");
			titulo = new JLabel("Medicos");
			adicionar = new JButton("Medico +");
			String[] medicos = new ControleMedico(d).getListaMedicos();
			listaMedicos = new JList<String>(medicos);
			
			adicionar.setMargin(new Insets(0,0,0,0));
			
			titulo.setFont(new Font("Times New Roman", Font.PLAIN, 33));
			titulo.setBounds(130, 20, 150, 30);
			adicionar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			adicionar.setBounds(150, 340, 90, 30);
			listaMedicos.setBounds(70, 65, 250, 250);
			listaMedicos.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaMedicos.setVisibleRowCount(10);
			listaMedicos.setFont(new Font("Calibri Light", Font.PLAIN, 20));
			
			janela.setLayout(null);
			janela.add(titulo);
			janela.add(adicionar);
			janela.add(listaMedicos);
			
			janela.setSize(410, 450);
			janela.setVisible(true);

			listaMedicos.addListSelectionListener(this);
			break;
			
		case 1:
			janela = new JFrame("Pacientes Cadastrados");
			titulo = new JLabel("Pacientes");
			adicionar = new JButton("Paciente +");
			listaPacientes = new JList<String>(new ControlePaciente(d).getListaPacientes());
			adicionar.setMargin(new Insets(0,0,0,0));
			
			titulo.setFont(new Font("Times New Roman", Font.PLAIN, 33));
			titulo.setBounds(130, 20, 150, 30);
			adicionar.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			adicionar.setBounds(150, 340, 90, 30);
			listaPacientes.setBounds(70, 65, 250, 250);
			listaPacientes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaPacientes.setVisibleRowCount(10);
			listaPacientes.setFont(new Font("Calibri Light", Font.PLAIN, 20));
			
			janela.setLayout(null);
			janela.add(titulo);
			janela.add(adicionar);
			janela.add(listaPacientes);
			janela.setSize(410, 450);
			janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			janela.setVisible(true);
			
			listaPacientes.addListSelectionListener(this);
			break;
			
		case 2:
			janela = new JFrame("Medicamentos Cadastrados");
			titulo = new JLabel("Medicamentos");
			adicionar = new JButton("Medicamento +");
			listaMedicamentos = new JList<String>(new ControleMedicamento(d).getListaMedicamentos());
			adicionar.setMargin(new Insets(0,-1,0,0));
			
			titulo.setFont(new Font("Times New Roman", Font.PLAIN, 28));
			titulo.setBounds(120, 20, 200, 30);
			adicionar.setFont(new Font("Times New Roman", Font.PLAIN, 16));
			adicionar.setBounds(140, 340, 110, 30);
			listaMedicamentos.setBounds(70, 65, 250, 250);
			listaMedicamentos.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listaMedicamentos.setVisibleRowCount(10);
			listaMedicamentos.setFont(new Font("Calibri Light", Font.PLAIN, 20));
			
			janela.setLayout(null);
			janela.add(titulo);
			janela.add(adicionar);
			janela.add(listaMedicamentos);
			janela.setSize(410, 450);
			janela.setVisible(true);
			
			listaMedicamentos.addListSelectionListener(this);
			break;
		}
		janela.getContentPane().setBackground(TelaInicial.c1);
		adicionar.setBackground(TelaInicial.c2);
		adicionar.setForeground(Color.white);
		adicionar.setBorderPainted(false);
		
		adicionar.addActionListener(this);

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object src = e.getSource();
		if(e.getValueIsAdjusting() && src == listaPacientes) { // visualizacao dos dados paciente
			janela.dispose();
			new TelaPrincipal(d, ControleHorarios.getDayOfWeek(), listaPacientes.getSelectedIndex());
		}else if(e.getValueIsAdjusting() && src == listaMedicos) { // visualizacao dos dados medico
			janela.dispose();
			new TelaDetalhes(d, listaMedicos.getSelectedIndex(), 3);
		}else if(e.getValueIsAdjusting() && src == listaMedicamentos) {// tela de detalhes do cadastro de uma receita
			janela.dispose();
			new TelaDetalhes(d, listaMedicamentos.getSelectedIndex(), 5);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == adicionar) {
			if(opcao == 0) { // tela de cadastro medico
				janela.dispose();
				new TelaDetalhes(d, d.getMedico().size(), 2);
			}else if(opcao == 1) { // tela de cadastro paciente
				janela.dispose();
				new TelaDetalhes(d, d.getPaciente().size(), 0);
			}else if(opcao == 2) { // tela de cadastro medicamento
				janela.dispose();
				new TelaDetalhes(d, d.getMedicamento().size(), 4);
			}
			
		}
		
	}

}
