import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Random;

public class FecheACaixa {
    private String nomeJog;
    private int pontuacao;
    private FileWriter fw;
    final String[] tabuleiro = { "[1]", "[2]", "[3]", "[4]", "[5]", "[6]", "[7]", "[8]", "[9]" };

    public FecheACaixa(String nJ) {
        nomeJog = nJ;
    }

    Scanner in = new Scanner(System.in);

    public String obtemJogador() {
        return nomeJog;
    }

    public int obtemPontuacao() {
        return pontuacao;
    }

    public void concluiu() throws java.io.FileNotFoundException {
        try {
            fw = new FileWriter("placar.txt", true);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        LineNumberReader lnr = new LineNumberReader(new FileReader("placar.txt"));
        try {
            lnr.skip(Long.MAX_VALUE);
        } catch (java.io.IOException ioe) {
            ioe.printStackTrace();
        }
        // System.out.println(lnr.getLineNumber());
        if (lnr.getLineNumber() < 10) {
            Placar.adiciona("placar.txt", nomeJog, pontuacao, lnr.getLineNumber());
        } else {
            Placar.atualiza("placar.txt", nomeJog, pontuacao);
        }
        Placar.mostra("placar.txt");
        try {
            fw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void jogar() throws IOException, java.io.FileNotFoundException {
        System.out.println("------ FECHE A CAIXA -----");
        boolean[] casasFechamento = { false, false, false, false, false, false, false, false, false };
        String[] casasInformadas = new String[10];
        int[] casasInformadasIndices = new int[10];
        int contadorCasas = 0;
        pontuacao = 0;
        System.out.print("Nome do jogador: ");
        boolean abandonouPartida = false, lancamentoAtivo = false;
        int dado1 = 0, dado2 = 0, somaDados = 0, somaCaixas = 0, contador = 0;

        while (true) {
            if (buscaBoolean(casasFechamento, casasFechamento.length, false) == 0) {
                System.out.printf("Parabéns, %s, todas as casas foram fechadas!\nA sua pontuação final é: %d pontos",
                        nomeJog, pontuacao);
                break;
            }
            mostrarTabuleiro(tabuleiro);
            System.out.println();
            System.out.println("(L)ançar, (P)assar, (F)echar, (S)air ou informe a(s) casa(s) que deseja fechar ");
            System.out.println();
            String opcao = in.next();
            Scanner scanner = new Scanner(opcao);

            // Para o caso de a entrada não ser um número inteiro
            if (scanner.hasNextInt() == false) {

                if (lancamentoAtivo == false
                        && (opcao.trim().toUpperCase().equals("L") || opcao.trim().toUpperCase().equals("LANÇAR"))) {
                    somaDados = 0;
                    if (casasFechamento[6] == false || casasFechamento[7] == false || casasFechamento[8] == false) {
                        dado1 = gerarNumeroAleatorio(1, 9);
                        dado2 = gerarNumeroAleatorio(1, 9);
                        somaDados = dado1 + dado2;
                        System.out.printf("Resultado do lançamento:\n%d e %d\n", dado1, dado2);
                    } else {
                        dado1 = gerarNumeroAleatorio(1, 9);
                        somaDados = dado1;
                        System.out.printf("Resultado do lançamento:\n%d\n", dado1);

                    }
                    System.out.println();
                    lancamentoAtivo = true;

                } else if (lancamentoAtivo == true
                        && (opcao.trim().toUpperCase().equals("L") || opcao.trim().toUpperCase().equals("LANÇAR"))) {
                    if (casasFechamento[6] == false || casasFechamento[7] == false || casasFechamento[8] == false) {
                        System.out.printf("Atenção!!! Dados já foram lançados!!! %d e %d\n", dado1, dado2);
                    } else {
                        System.out.printf("Atenção!!! O dado já foi lançado!!! %d\n", dado1);
                    }
                    System.out.println();

                } else if (opcao.trim().toUpperCase().equals("P") || opcao.trim().toUpperCase().equals("PASSAR")) {
                    if (lancamentoAtivo == false) {
                        System.out.println("Atenção!!! É necessário lançar os dados antes de passar a jogada!!!\n");
                        System.out.println();
                    } else {
                        pontuacao += somaDados;
                        lancamentoAtivo = false;
                        System.out.println("Sua pontuação no momento é: " + pontuacao + "\n");

                    }
                } else if (opcao.trim().toUpperCase().equals("F") || opcao.trim().toUpperCase().equals("FECHAR")) {
                    if (lancamentoAtivo == false) {
                        System.out.println("Atenção!!! É necessário lançar os dados antes de fechar as casas!!!\n");
                        System.out.println();
                    } else if (contadorCasas == 0) {
                        System.out
                                .println("Atenção!!! É necessário selecionar pelo menos uma casa para fechar!!!\n");
                        System.out.println();

                    } else {
                        if (somaCaixas != somaDados && somaDados != 0 || somaCaixas == 0) {
                            System.out.println(
                                    "Jogada Inválida!!!\nA soma dos dados não condiz com a(s) casa(s) que deseja fechar.");
                            somaCaixas = 0;
                            System.out.println();
                        } else if (somaDados == 0) {
                            System.out.println(
                                    "Jogada Inválida!!!\nÉ necessário lançar os dados antes de fechar as caixas.");
                            System.out.println();
                            // somaCaixas = 0;
                        }

                        else {
                            for (int i = 0; i < contadorCasas; i++) {
                                if (busca(tabuleiro, tabuleiro.length, casasInformadas[i]) == true) {
                                    tabuleiro[casasInformadasIndices[i]] = "[X]";
                                    casasFechamento[casasInformadasIndices[i]] = true;
                                }
                            }
                            System.out.println();
                            somaDados = 0;
                            lancamentoAtivo = false;
                            System.out.println("Sua pontuação no momento é: " + pontuacao + "\n");
                        }
                        somaCaixas = 0;
                        contadorCasas = 0;

                    }
                }

                else if (opcao.trim().toUpperCase().equals("S") || opcao.trim().toUpperCase().equals("SAIR")) {
                    abandonouPartida = true;
                    System.out.println("Partida encerrada!");
                    Placar.mostra("placar.txt");
                    break;
                } else {
                    System.out.println("Comando inexistente. Tente novamente!");
                }

            }

            // Para o caso a entrada seja um inteiro:
            else {
                if (lancamentoAtivo == true && Integer.parseInt(opcao) >= 1 && Integer.parseInt(opcao) <= 9
                        && casasFechamento[Integer.parseInt(opcao) - 1] == false) {

                    if (busca(casasInformadas, contadorCasas, "[" + opcao + "]") == false) {
                        casasInformadas[contadorCasas] = "[" + opcao + "]";
                        casasInformadasIndices[contadorCasas] = Integer.parseInt(opcao) - 1;
                        contadorCasas++;
                        somaCaixas += Integer.parseInt(opcao);
                        System.out.println();
                        System.out.print("Casas selecionadas ");
                        for (int i = 0; i < contadorCasas; i++) {
                            System.out.print(casasInformadas[i] + " ");

                        }
                        System.out.println("\n");
                    } else {
                        System.out.println("Atenção!!! Casa já selecionada!!!\n");
                    }
                } else if (Integer.parseInt(opcao) < 1 || Integer.parseInt(opcao) > 9) {
                    System.out.println("O número da caixa informado não é válido, tente novamente!!!");
                } else if (lancamentoAtivo == false) {
                    System.out.println("Atenção!!! É necessário lançar os dados antes de selecionar as casas!!!\n");
                    System.out.println();
                } else if (casasFechamento[Integer.parseInt(opcao) - 1] == true) {
                    System.out.println("Atenção!!! Esta casa já se encontra fechada, informe outra.");
                    System.out.println();
                    // System.out.print("Casas selecionadas ");
                    for (int i = 0; i < contadorCasas; i++) {
                        System.out.print(casasInformadas[i] + " ");
                    }
                    System.out.println("\n");
                }

                else {
                    System.out.println("Casa inexistente. Tente novamente!");
                }
            }
        }

        if (abandonouPartida == false) {
            concluiu();

        }
    }

    /**
     * Gera um número inteiro aleatório dentro do intervalo específico
     * 
     * @param minimo O menor número inteiro possível a ser sorteado (inclusive)
     * @param maximo O maior número inteiro possível a ser sorteado (inclusive)
     * @return Número inteiro aleatório sorteado dentro do intervalo especificado
     *         (<code>int</code>).
     */
    public static int gerarNumeroAleatorio(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }

    /**
     * Imprime o tabuleiro, que é um array do tipo vetor.
     * 
     * @param m Tabuleiro (vetor) a ser impresso.
     */
    public static void mostrarTabuleiro(String[] m) {
        for (int i = 0; i < m.length; i++) {
            System.out.print(m[i]);
        }
    }

    /**
     * Soma todos os elementos de um vetor (completamente preenchido) de inteiros.
     * Este m&eacute;todo utiliza o m&eacute;todo <code>soma</code> para um vetor
     * parcial.
     * 
     * @param v Vetor cujos elementos devem ser somados.
     * @return Somatório dos elementos do vetor (<code>int</code>).
     */
    public static int somaVetor(int[] v) {
        return somaVetor(v, v.length);
    }

    /**
     * Soma os elementos de um vetor parcialmente preenchido de inteiros.
     * 
     * @param v Vetor cujos elementos devem ser somados.
     * @param t Tamanho do vetor parcial (ou seja, número de elementos que
     *          ser&atilde;o somados).
     * @return Somatório dos elementos do vetor parcial (<code>int</code>).
     */
    public static int somaVetor(int[] v, int t) {
        int soma = 0;
        for (int i = 0; i < t; i++) {
            soma = soma + v[i];
        }
        return soma;
    }

    public static boolean busca(String[] v, int t, String n) {
        for (int i = 0; i < t; i++) {
            if (v[i].equals(n)) {
                return true;
            }
        }
        return false;

    }

    public static int buscaBoolean(boolean[] v, int t, boolean n) {
        int contador = 0;
        for (int i = 0; i < t; i++) {
            if (v[i] == n) {
                contador++;
            }
        }
        return contador;
    }
}