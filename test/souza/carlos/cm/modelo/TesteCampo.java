package souza.carlos.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import souza.carlos.cm.ExplosaoException;

public class TesteCampo {

	private Campo campo;
	
	@BeforeEach
	void iniciaCampo () {
		campo = new Campo(3, 3);	 
	}
	
	@Test
	void vizinhoEsquerda () {
		Campo vizinho = new Campo(3, 2);		
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void vizinhoDireita () {
		Campo vizinho = new Campo(3, 4);		
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void vizinhoEmcima () {
		Campo vizinho = new Campo(2, 3);		
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void vizinhoEmbaixo () {
		Campo vizinho = new Campo(4, 3);		
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void vizinhoDiagonal () {
		Campo vizinho = new Campo(2, 2);		
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void NaoVizinho () {
		Campo vizinho = new Campo(1, 1);		
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}
	
	@Test
	void valorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void alternarMarcacao() {
		campo.alterarMarcao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void alternarMarcacaoDuasX() {
		campo.alterarMarcao();
		campo.alterarMarcao();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void abrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void abrirNaoMinadoMarcado() {
		campo.alterarMarcao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void abrirMinadoMarcado() {
		campo.alterarMarcao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void abrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	
}
