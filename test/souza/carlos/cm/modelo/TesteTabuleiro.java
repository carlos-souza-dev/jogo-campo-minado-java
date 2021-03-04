package souza.carlos.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

class TesteTabuleiro extends TesteCampo {
	
	private Tabuleiro tabuleiro;

	@BeforeEach
	void iniciarTabuleiro() {
		tabuleiro = new Tabuleiro(10, 10, 5);	
	}
	
	@Test 
	void gerarCampo() {
		
		tabuleiro.gerarCampos();
	}
	
	@Test
	void toStringPrint() {
		
		tabuleiro.alterarMarcado(3, 4);
		tabuleiro.alterarMarcado(3, 3);
		tabuleiro.abrir(3, 5);
		
		System.out.println(tabuleiro);
	}
	
	@Test
	void reiniciar() {		
		tabuleiro.reiniciar();
		tabuleiro.objetivoAlcancado();
	}
}
