
//fazer um if pra conferir se a soma das caixas informadas não ultrapassa o limite
import java.util.Scanner;
import java.util.Random;

public class FecheACaixa {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("----- FECHE A CAIXA ------");
        String[] tabuleiro = { "[1]", "[2]", "[3]", "[4]", "[5]", "[6]", "[7]", "[8]", "[9]" };
        String[] tabuleiroDemonstrativo = { "[1]", "[2]", "[3]", "[4]", "[5]", "[6]", "[7]", "[8]", "[9]" };
        String[] casasInformadas = new String[10];
        int[] casasInformadasPonteiro = new int[10];
        int[] casasInformadasIndices = new int[10];
        int contadorCasas = 0, pontuacao = 0;
        System.out.println("Nome do jogador: ");
        String nomeJogador = in.nextLine();
        boolean condicao = true, lancamentoAtivo = false;
        int dado1 = 0, dado2 = 0, somaDados = 0, somaCaixas = 0;
        // mostrarTabuleiro(tabuleiro);

        while (condicao == true) {
            mostrarTabuleiro(tabuleiro);
            System.out.println();
            System.out.println("(L)ançar, (P)assar, (F)echar, (S)air ou informe a(s) casa(s) que deseja fechar ");
            System.out.println();
            String opcao = in.next();
            Scanner scanner = new Scanner(opcao);
            if (scanner.hasNextInt() == false) {

                if (lancamentoAtivo == false
                        && (opcao.trim().toUpperCase().equals("L") || opcao.trim().toUpperCase().equals("LANÇAR"))) {
                    somaDados = 0;
                    dado1 = gerarNumeroAleatorio(1, 9);
                    dado2 = gerarNumeroAleatorio(1, 9);
                    somaDados = dado1 + dado2;
                    System.out.printf("Resultado do lançamento:\n%d e %d\n", dado1, dado2);
                    System.out.println();
                    lancamentoAtivo = true;

                } else if (lancamentoAtivo == true
                        && (opcao.trim().toUpperCase().equals("L") || opcao.trim().toUpperCase().equals("LANÇAR"))) {
                    System.out.printf("Atenção!!! Dados já foram lançados!!! %d e %d\n", dado1, dado2);
                    System.out.println();

                } else if (opcao.trim().toUpperCase().equals("P") || opcao.trim().toUpperCase().equals("PASSAR")) {
                    if (lancamentoAtivo == false) {
                        System.out.println("Atenção!!! É necessário lançar os dados antes de passar a jogada!!!\n");
                        System.out.println();
                    } else {
                        pontuacao += somaDados;
                        lancamentoAtivo = false;
                    }
                } else if (opcao.trim().toUpperCase().equals("F") || opcao.trim().toUpperCase().equals("FECHAR")) {
                    if (lancamentoAtivo == false) {
                        System.out.println("Atenção!!! É necessário lançar os dados antes de fechar as casas!!!\n");
                        System.out.println();
                    } else {
                        if (somaCaixas > somaDados && somaDados != 0 || somaCaixas == 0) {
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
                                }
                            }
                            // mostrarTabuleiro(tabuleiro);
                            System.out.println();
                            pontuacao = somaDados - somaCaixas;
                            somaDados = 0;
                            lancamentoAtivo = false;
                        }
                        somaCaixas = 0;
                        contadorCasas = 0;

                    }
                }

                else if (opcao.trim().toUpperCase().equals("S") || opcao.trim().toUpperCase().equals("SAIR")) {
                    System.out.println("Partida encerrada!");
                    break;
                    // System.exit(0);
                } else {
                    System.out.println("Comando inexistente. Tente novamente!");
                }

            }
            // caso a entrada seja um inteiro
            else {
                if (lancamentoAtivo == true && Integer.parseInt(opcao) >= 1 && Integer.parseInt(opcao) <= 9) {
                    casasInformadasPonteiro[contadorCasas] = Integer.parseInt(opcao);
                    casasInformadas[contadorCasas] = "[" + opcao + "]";
                    casasInformadasIndices[contadorCasas] = Integer.parseInt(opcao) - 1;
                    contadorCasas++;
                    somaCaixas += Integer.parseInt(opcao);
                    // mostrarTabuleiro(tabuleiro);
                    System.out.println();
                    System.out.print("Casas selecionadas ");
                    for (int i = 0; i < contadorCasas; i++) {
                        System.out.print(casasInformadas[i] + " "); // preciso acessar todos os
                        // "opcao"
                        // informados
                    }
                    System.out.println("\n");
                    // tabuleiroDemonstrativo[Integer.parseInt(opcao) - 1] = " < " +
                    // tabuleiro[Integer.parseInt(opcao) - 1]
                    // + " > ";
                    // mostrarTabuleiro(tabuleiroDemonstrativo);
                } else if (lancamentoAtivo == false) {
                    System.out.println("Atenção!!! É necessário lançar os dados antes de selecionar as casas!!!\n");
                    System.out.println();
                }

                else {
                    System.out.println("Casa inexistente. Tente novamente!");
                }
            }
        }
    }

    public static int gerarNumeroAleatorio(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
    }

    public static void mostrarTabuleiro(String[] m) {
        for (int i = 0; i < m.length; i++) {
            System.out.print(m[i]);
        }
    }

    public static int somaVetor(int[] v) {
        return somaVetor(v, v.length);
    }

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
}
// if (opcao.trim().toUpperCase().equals("F") ||
// opcao.trim().toUpperCase().equals("FECHAR")) {
// if (somaVetor(casasInformadas) != somaDados) {
// System.out.println(
// "Jogada Inválida: A soma dos dados não condiz com a(s) casa(s) que deseja
// fechar");
// } else {
// System.out.println("arrozdoce");
// for (int i = 0; i < contadorCasas; i++) {
// if (busca(tabuleiro, 9, Integer.toString(casasInformadas[i])) == true) {
// System.out.println("bom dia");
// tabuleiro[Integer.toString(casasInformadas[i])
// .indexOf(Integer.toString(casasInformadas[i]))] = "[X]";
// }
// mostrarTabuleiro(tabuleiro);
// }
// }

// }