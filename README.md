## Feche a Caixa

Implementação em Java do jogo Feche A Caixa para o trabalho final da disciplina Fundamentos da Programação, ministrada pelo professor Roland Teodorowitsch. Neste jogo, tem-se como objetivo fechar as 9 caixas do tabuleiro obtendo-se a menor pontuação possível. Para tal, são sorteados, até que sejam fechadas as caixas 7,8 e 9, dois dados de 9 faces cada um, e após serem fechadas tais casas, sorteia-se apenas um dado. A partir da soma dos dados ou do número do dado, o jogador deve determinar que casa(s) fechar, lembrando de coincidir os números, no caso de haver essa possibilidade. Assim, se forem sorteadas, por exemplo, as faces 5 e 9, o jogador poderá fechar no tabuleiro: 
1) [5] e [9]
2) [3], [2] e [9]
3) [4], [1] e [9]
4) [5], [8] e [1]
5) [5], [4], [3] e [2], 
6) [6], [1], [2], [5], *entre outras possibilidades.*

## Funcionalidades
* Fornecer nome e iniciar uma partida.
* Encerrar uma partida a qualquer momento.
* Visualizar os pontos e o estado atual do tabuleiro.
* Lançar dado(s).
* Selecionar quais casas deverão ser fechadas ou acumular o valor sorteado no(s) dado(s) na pontuação.
* Persistência de um placar com espaço para as 10 melhores pontuações, ordenadas de forma crescente.
