package souza.carlos.cm;

import souza.carlos.cm.modelo.Tabuleiro;
import souza.carlos.cm.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(10, 10, 6);
		
		new TabuleiroConsole(tabuleiro);
	}
	
}
