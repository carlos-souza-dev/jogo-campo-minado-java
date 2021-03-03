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
		campo.alterarMarcado();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void alternarMarcacaoDuasX() {
		campo.alterarMarcado();
		campo.alterarMarcado();
		assertFalse(campo.isMarcado());
	}
	
	@Test
	void abrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void abrirNaoMinadoMarcado() {
		campo.alterarMarcado();
		assertFalse(campo.abrir());
	}
	
	@Test
	void abrirMinadoMarcado() {
		campo.alterarMarcado();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void abrirComVizinhos() {
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 1);
		campo12.minar();
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);

		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());		
	}
	
	@Test
	void abrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}	
	
	@Test
	void getLinhas() {
		Campo campo13 = new Campo(1, 1);
		
		assertTrue(campo13.getLinha() == 1 && campo13.getColuna() == 1);
	}
	
	@Test
	void testeDeReiniciar() {
		Campo campo13 = new Campo(1, 1);
		
        assertTrue(campo13.reiniciar());    
	}
		
	@Test
	void objetivoAlcancado() {
		Campo campo13 = new Campo(1, 1);
		campo13.abrir();
		
		assertTrue(campo13.objetivoAlcancado());
	}
	
	@Test
	void objetivoAlcancado2() {
		Campo campo13 = new Campo(1, 1);
		campo13.minar();
		campo13.alterarMarcado();
		
		assertTrue(campo13.objetivoAlcancado());
	}
	
	@Test
	void saidaToStringMarcado() {

		Campo campo13 = new Campo(1, 1);
		campo13.alterarMarcado();
		
		assertEquals("X", campo13.toString());
	}
	
	@Test
	void saidaToStringAbertoEMinado() {
		Campo campo13 = new Campo(1, 1);
		campo13.abrir();
		campo13.minar();
	
		assertEquals("*", campo13.toString());
	}
	
	@Test
	void saidaToStringAbertoEMinaVizinhos () {
		Campo campo13 = new Campo(1, 1);
		campo13.abrir();
		
		Campo campo14 = new Campo(2, 2);
		campo14.minar();
		campo13.adicionarVizinho(campo14);
		
		assertEquals("1", campo13.toString());
	}
	
	@Test
	void saidaToStringAberto() {
		Campo campo13 = new Campo(1, 1);
		campo13.abrir();
		
		assertEquals(" ", campo13.toString());
	}
	
	@Test
	void saidaToStringNaoMexido() {
		Campo campo13 = new Campo(1, 1);
		
		assertEquals("?", campo13.toString());
	}
}
