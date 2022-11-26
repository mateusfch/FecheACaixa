import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * A classe Placar é responsável por adicionar jogadores no placar e também
 * atualizar esse placar conforme a entrada de novas pontuações, mas somente
 * caso seja necessário.
 * 
 * @author Mateus Freitas Charloto
 * @version 26 nov. 2022
 */

public class Placar {

  /**
   * Ordena um vetor de inteiros de maneira crescente e, com base nisso, ordena um
   * vetor de strings associado a esse vetor de inteiros. Não há return nesse
   * método.
   * 
   * @param v  Vetor de inteiros a ser ordenado.
   * @param v2 Vetor de strings que será ordenado com base na ordenação do vetor
   *           de inteiros.
   */
  public static void bubbleSort(int[] v, String[] v2) {
    for (int i = 0; i < v.length; i++) {
      for (int m = 0; m < v.length; m++) {
        int aux = v[i];
        String aux2 = v2[i];
        if (v[i] < v[m]) {
          v[i] = v[m];
          v[m] = aux;
          v2[i] = v2[m];
          v2[m] = aux2;
        }
      }
    }
  }

  /**
   * Exibe um placar contendo o nome do jogador e a sua pontuação obtida no jogo.
   * 
   * @param arquivo String com o placar a ser exibido.
   * 
   */
  public static void mostra(String arquivo) throws FileNotFoundException {
    Scanner inFile = new Scanner(new File(arquivo));
    System.out.println("\n--Placar ------");
    int i = 1;
    while (inFile.hasNextLine()) {
      String linha = inFile.nextLine();
      String[] campos = linha.split(";");
      if (campos.length != 2) {
        System.out.println("> Placar: arquivo" + arquivo + " invalido...");
        inFile.close();
        return;

      }
      String nome = campos[0];
      int pontos = Integer.parseInt(campos[1]);
      System.out.printf("%2d. %-20s > %3d\n", i++, nome, pontos);
    }
    System.out.print("----------\n");
    inFile.close();
  }

  /**
   * Adiciona no placar o nome do jogador e a pontuação que ele obteve no jogo.
   * 
   * @param arquivo   Arquivo em que serão registrados os dados.
   * @param nome      Nome do jogador.
   * @param pontos    Pontos obtidos no jogo durante a partida.
   * @param numLinhas Número de linhas do placar até então.
   */
  public static void adiciona(String arquivo, String nome, int pontos, int numLinhas) throws FileNotFoundException {
    try {
      FileWriter fw;
      fw = new FileWriter(arquivo, true);
      fw.write(nome + ";" + pontos + "\n");
      fw.close();
    } catch (Exception e) {
      System.out.println("Erro " + e.getMessage());
    }
    Scanner inFile = new Scanner(new File(arquivo));
    int contador = 0;
    int[] scores = new int[numLinhas + 1];
    String[] names = new String[numLinhas + 1];

    while (inFile.hasNextLine()) {
      String linha = inFile.nextLine();
      String[] campos = linha.split(";");
      names[contador] = campos[0];
      scores[contador] = Integer.parseInt(campos[1]);
      contador++;
    }
    bubbleSort(scores, names);

    try {
      new FileWriter(arquivo, false).close();
    } catch (java.io.IOException ioe) {
      ioe.printStackTrace();
    }

    try {
      FileWriter fw;
      fw = new FileWriter(arquivo, true);
      for (int i = 0; i < numLinhas + 1; i++) {
        fw.write(names[i] + ";" + scores[i] + "\n");
      }
      fw.close();
    } catch (Exception e) {
      System.out.println("Erro " + e.getMessage());
    }
  }

  /**
   * Verifica se o enésimo jogador (n>=11) está apto ou
   * não a figurar no placar. Caso esteja, é registrado no placar; do contrário,
   * fica de reserva na décima primeira posição do vetor de names e do vetor de
   * scores, não sendo registrado e podendo ser substituído.
   * 
   * @param nomeArquivo Arquivo que será atualizado.
   * @param nome        Nome do jogador.
   * @param pontos      Pontos do jogador.
   */
  public static void atualiza(String nomeArquivo, String nome, int pontos) throws FileNotFoundException {
    int contador = 0;
    int[] scores = new int[11];
    String[] names = new String[11];
    Scanner inFile = new Scanner(new File(nomeArquivo));

    while (inFile.hasNextLine()) {
      String linha = inFile.nextLine();
      String[] campos = linha.split(";");
      names[contador] = campos[0];
      scores[contador] = Integer.parseInt(campos[1]);
      contador++;
    }
    names[10] = nome;
    scores[10] = pontos;

    bubbleSort(scores, names);

    try {
      new FileWriter(nomeArquivo, false).close();
    } catch (java.io.IOException ioe) {
      ioe.printStackTrace();
    }

    try {
      FileWriter fw;
      fw = new FileWriter(nomeArquivo, true);
      for (int i = 0; i < 10; i++) {
        fw.write(names[i] + ";" + scores[i] + "\n");
      }
      fw.close();
    } catch (Exception e) {
      System.out.println("Erro " + e.getMessage());
    }
  }
}