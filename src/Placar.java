import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Placar {

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

  public static void adiciona(String arquivo, String nome, int pontos) throws FileNotFoundException {
    try {
      FileWriter fw;
      fw = new FileWriter(arquivo, true);
      fw.write(nome + ";" + pontos + "\n");
      fw.close();
    } catch (Exception e) {
      System.out.println("Erro " + e.getMessage());
    }
  }

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