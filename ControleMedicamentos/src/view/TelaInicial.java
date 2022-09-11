package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import control.*;

public class TelaInicial implements ActionListener, ListSelectionListener {
	private static ControleDados d = new ControleDados();
	public static Color c1 = new Color(245,245,220), c2 = new Color(139,0,0); 
	private JFrame janela = new JFrame("Controle de Medicamentos");
	private JLabel titulo = new JLabel("Pacientes");
	private JButton adicionar = new JButton("Paciente +");
	private JList<String> listaPacientes;
	
	public TelaInicial() {listaPacientes = new JList<String>(new ControlePaciente(d).getListaPacientes());
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

		janela.getContentPane().setBackground(c1);
		adicionar.setBackground(c2);
		adicionar.setForeground(Color.white);
		adicionar.setBorderPainted(false);
		
		listaPacientes.addListSelectionListener(this);
		adicionar.addActionListener(this);
	}

	public static void main(String[] args) {
		new TelaInicial();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == adicionar) {
			new TelaDetalhes(d, d.getPaciente().size(), 0);
			janela.dispose();
		}
			
		
	}
	
	public void valueChanged(ListSelectionEvent e) {
		Object src = e.getSource();

		if(e.getValueIsAdjusting() && src == listaPacientes) {
			new TelaPrincipal(d, ControleHorarios.getDayOfWeek(), listaPacientes.getSelectedIndex());
			janela.dispose();
		}

	}
}
