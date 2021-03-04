package souza.carlos.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import souza.carlos.cm.ExplosaoException;
import souza.carlos.cm.excecao.SairException;
import souza.carlos.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		executarJogo();	
	}
	
	private void executarJogo() {
		try {
			boolean continuar = true;
			
			while(continuar) {
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n) ");
				String resposta = entrada.nextLine();
				
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
			}
		} catch (SairException e) {
			System.out.println("Tchau!!!");
		} finally {
			entrada.close();
		}
	}
	
	private void cicloDoJogo() {
		try {
			
			while(!tabuleiro.objetivoAlcancado()) {
					
				System.out.println(tabuleiro);			
				
				String digitado = null;
				String acao = "B";
				int x;
				int y;
				boolean isNumero = false;
				char[] numeros;
				boolean inputX = false;
				boolean inputY = false;
				boolean inputAM = false;
//				PROFESSOR
//				Iterator<Integer> xy;
				
				do {
				
					if(inputX && inputY) {
						System.out.println("Valore de 'X' deve ser de 0 até "+(tabuleiro.getLinhas()-1)+" e 'Y' deve ser de 0 até "+(tabuleiro.getColunas()-1));
					} else if(inputY) {
						System.out.println("Valor de 'Y' deve ser de 0 até "+(tabuleiro.getColunas()-1));
					} else if(inputX) {
						System.out.println("Valor de 'X' deve ser de 0 até "+(tabuleiro.getLinhas()-1));
					}
					
					do {
						
						if(!isNumero) {
							System.out.println("Válido somente números");
						}
						
						digitado = capturaValorDigitado("Digite (X, Y): ");
						
//						PROFESSOR
//						xy = Arrays.stream(digitado.split(","))
//							.map(e -> Integer.parseInt(e.trim())).iterator();
					
					String[] input = digitado.split(",");
					numeros = input[0].toCharArray();
					numeros = input[1].toCharArray();
					isNumero = (Character.isDigit(input[0].charAt(0)) && Character.isDigit(input[1].charAt(0)));
					
					} while (!isNumero);
					
					x = numeros[0];
					y = numeros[0];
					
//					PROFESSOR
//					x = xy.next();
//					y = xy.next();
					
					inputX = ((x < 0 || x >= tabuleiro.getLinhas()));
					inputY = ((y < 0 || y >= tabuleiro.getColunas()));
					
				} while (!inputX || !inputY);
				
				do {
					
					acao = capturaValorDigitado("A = Abrir ou M = (Des)Marcar: ");
					
					if("A".equalsIgnoreCase(acao)) {
						tabuleiro.abrir(x, y);
					} else if ("M".equalsIgnoreCase(acao)) {
						tabuleiro.alterarMarcado(x, y);
					}					
					
					inputAM = ("A".equalsIgnoreCase(acao) || "M".equalsIgnoreCase(acao)); // Se um dos dois campos for verdadeiro retora verdadeiro.
					
				} while (!inputAM); // While recebe a negação do inputAM
				
			}
			
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!");			
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}
	
	private String capturaValorDigitado(String texto) {
		System.out.print(texto.trim());
		String digitado = entrada.nextLine();
		
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}	
}
