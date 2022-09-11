package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import control.*;

public class TelaPrincipal implements ActionListener, ListSelectionListener {
	private static String[] diasDaSemana= {"","Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado"};
	private JFrame janela;
	private JLabel titulo;
	private JComboBox<Object> mudarDia = new JComboBox<Object>(diasDaSemana);
	private JButton adicionarReceita = new JButton("Receita +"), remedio = new JButton("Medicamento"), medico = new JButton("Medicos");
	private JButton dadosPaciente = new JButton("Perfil"), pacientes = new JButton("Pacientes"), buscar = new JButton(">");
	private JTextField pesquisa = new JTextField("Buscar Receita"); 
	private JList<String> receitasBusca = new JList<String>();
	private JList<String> receitasDoDia;
	private int posicao;
	private ControleDados d;
	private ControleReceita r;
	
	public TelaPrincipal(ControleDados d, String diaDaSemana, int posicao) {
		this.d=d;
		this.posicao = posicao;

		janela = new JFrame("Receitas de " + diaDaSemana);
		titulo = new JLabel(diaDaSemana);
		receitasDoDia = new JList<String>(new ControleReceita(d, posicao, 0).getListaReceitas(diaDaSemana, 0));
		
		dadosPaciente.setMargin(new Insets(0,0,0,0));
		medico.setMargin(new Insets(0,0,0,0));
		pacientes.setMargin(new Insets(0,0,0,0));
		buscar.setMargin(new Insets(0,0,0,0));
		remedio.setMargin(new Insets(0,0,0,0));
		adicionarReceita.setMargin(new Insets(0,0,0,0));
		
		titulo.setFont(new Font("Times New Roman", Font.PLAIN, 33));
		titulo.setBounds(20, 15, 140, 35);
		mudarDia.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		mudarDia.setBounds(160, 25, 100, 18);
		dadosPaciente.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		dadosPaciente.setBounds(280, 70, 105, 30);
		medico.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		medico.setBounds(280, 110, 105, 30);
		pacientes.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		pacientes.setBounds(280, 150, 105, 30);
		adicionarReceita.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		adicionarReceita.setBounds(280, 230, 105, 30);
		remedio.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		remedio.setBounds(280, 190, 105, 30);
		pesquisa.setBounds(280, 270, 88, 30);
		buscar.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		buscar.setBounds(370, 270, 15, 30);
		
		
		receitasDoDia.setBounds(20, 65, 250, 300);
		receitasDoDia.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		receitasDoDia.setVisibleRowCount(10);
		receitasDoDia.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		
		receitasBusca.setBounds(280, 310, 105, 55);
		receitasBusca.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		receitasBusca.setVisibleRowCount(2);
		receitasBusca.setFont(new Font("Calibri Light", Font.PLAIN, 16));

		
		janela.setLayout(null);
		janela.add(titulo);
		janela.add(mudarDia);
		janela.add(dadosPaciente);
		janela.add(medico);
		janela.add(pacientes);
		janela.add(pesquisa);
		janela.add(buscar);
		janela.add(remedio);
		janela.add(adicionarReceita);
		janela.add(receitasDoDia);
		janela.add(receitasBusca);
		
		janela.setSize(410, 450);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
		receitasBusca.setVisible(false);
		
		janela.getContentPane().setBackground(TelaInicial.c1);
		adicionarReceita.setBackground(TelaInicial.c2);
		adicionarReceita.setForeground(Color.white);
		adicionarReceita.setBorderPainted(false);
		remedio.setBackground(TelaInicial.c2);
		remedio.setForeground(Color.white);
		remedio.setBorderPainted(false);
		medico.setBackground(TelaInicial.c2); 
		medico.setForeground(Color.white);
		medico.setBorderPainted(false);
		dadosPaciente.setBackground(TelaInicial.c2); 
		dadosPaciente.setForeground(Color.white);
		dadosPaciente.setBorderPainted(false);
		pacientes.setBackground(TelaInicial.c2); 
		pacientes.setForeground(Color.white);
		pacientes.setBorderPainted(false);
		buscar.setBackground(TelaInicial.c2);
		buscar.setForeground(Color.white);
		buscar.setBorderPainted(false);
		mudarDia.setBackground(Color.white);
		receitasBusca.setBackground(Color.white);
		
		receitasBusca.addListSelectionListener(this);
		mudarDia.addActionListener(this);
		dadosPaciente.addActionListener(this);
		pacientes.addActionListener(this);
		medico.addActionListener(this);
		remedio.addActionListener(this);
		adicionarReceita.addActionListener(this);
		buscar.addActionListener(this);
		
	}

	public void valueChanged(ListSelectionEvent e) {
		Object src = e.getSource();
		if(src == receitasBusca) {
			String[] vazia = {};
			new TelaDetalhesReceita(d, receitasBusca.getSelectedIndex(), 3, posicao, r.getPosicao().get(receitasBusca.getSelectedIndex()), vazia);
			janela.dispose();
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == mudarDia) {// mudar as receitas para outros dias
			switch(mudarDia.getSelectedItem().toString()) {
			case "Domingo":
				new TelaPrincipal(d, "Domingo", posicao);
				janela.dispose();
				break;
			case "Segunda":
				new TelaPrincipal(d, "Segunda", posicao);
				janela.dispose();
				break;
			case "Terça":
				new TelaPrincipal(d, "Terca", posicao);
				janela.dispose();
				break;
			case "Quarta":
				new TelaPrincipal(d, "Quarta", posicao);
				janela.dispose();
				break;
			case "Quinta":
				new TelaPrincipal(d, "Quinta", posicao);
				janela.dispose();
				break;
			case "Sexta":
				new TelaPrincipal(d, "Sexta", posicao);
				janela.dispose();
				break;
			case "Sabado":
				new TelaPrincipal(d, "Sabado", posicao);
				janela.dispose();
				break;
			}
		}else if (src == dadosPaciente) {
			new TelaDetalhes(d, posicao, 1);
			janela.dispose();
		}else if (src == medico) {
			new TelaItensCadastrados(d, 0);
		}else if (src == pacientes) {	
			new TelaItensCadastrados(d, 1);
			janela.dispose();
		}else if (src == remedio) {
			new TelaItensCadastrados(d,2);
		}else if (src == adicionarReceita) {
			String[] vazia = {};
			new TelaDetalhesReceita(d, 0, 1, posicao, 0 ,vazia);
			janela.dispose();
		}else if (src == buscar) {
			r = new ControleReceita(d, posicao, 0);
			receitasBusca.setListData(r.getListaMedicamentosBusca(pesquisa.getText()));
			receitasBusca.updateUI();
			receitasBusca.setVisible(true);
		}
		
	}

}
