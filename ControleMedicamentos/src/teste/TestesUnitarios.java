package teste;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import control.ControleDados;

class TestesUnitarios {
	@Test
	void testCadastrarMedicamento() {
		String[] dados1 = {"3", "Remedio 4", "10", "ml"};
		String[] dados2 = {"0", "Remedio 4", "10", "ml"};
		String[] dados3 = {"0", "Remedio 4", "lO", "ml"};
		String[] dados4 = {"3", "Remedio 1", "10", "ml"};
		ControleDados d = new ControleDados();
		assertFalse(d.cadastrarEditarMedicamento(dados2));
		assertFalse(d.cadastrarEditarMedicamento(dados3));
		assertFalse(d.cadastrarEditarMedicamento(dados4));
		assertTrue(d.cadastrarEditarMedicamento(dados1));
	}
	
	@Test
	void testRemoverMedicamento() {
		String[] dados = {"3", "Remedio 4", "10", "ml"};
		ControleDados d = new ControleDados();
		d.cadastrarEditarMedicamento(dados);
		assertFalse(d.removerMedicamento(1));
		assertTrue(d.removerMedicamento(3));
	}
	
	@Test
	void testCadastrarHorario() {
		int[] dados1 = {0, 0, 25, 0, 0};
		int[] dados2 = {0, 0, 0, 60, 0};
		int[] dados3 = {0, 0, 10, 30, 0};
		int[] dados4 = {0, 0, 10, 30, 1};
		String[] diasSemana = {"Segunda", "Quarta",};
		ControleDados d = new ControleDados();
		assertFalse(d.cadastrarEditarHorariosReceita(dados1, diasSemana));
		assertFalse(d.cadastrarEditarHorariosReceita(dados2, diasSemana));
		assertTrue(d.cadastrarEditarHorariosReceita(dados3, diasSemana));
		assertTrue(d.cadastrarEditarHorariosReceita(dados4, diasSemana));
	}

}
