import java.util.Scanner;

/**
 * A classe Main é responsável pela execução do programa em si.
 * 
 * @author Mateus Freitas Charloto.
 * @version Versão 27 nov. 2022.
 */

public class Main {
  public static void main(String[] args) throws java.io.IOException {
    Scanner in = new Scanner(System.in);

    System.out.println("Nome do jogador: ");
    String nomeJogador = in.next();

    FecheACaixa jogo = new FecheACaixa(nomeJogador);
    jogo.jogar();

  }
}
