## Feche a Caixa

Java implementation of the game "Feche a Caixa" for the course "Fundamentos da Programação", run by professor Roland Teodorowitsch. In this game, the main goal is to close the 9 boxes on the board obtaining the lower score possible. In order to do this, will be rolled – until the moment boxes 7, 8 and 9 be closed – two dice (both D9), and as long as these boxes are closed, only one will be rolled. Considering the sum of the dice or its value (when there is only one dice), the player needs to choose which box(es) to close, having in mind the numbers obligatorily must coincide. Thus, if the result of the dice rolling is, for example, 5 and 9, he/she is able to close in the board:

1) [5] and [9]
2) [3], [2] and [9]
3) [4], [1] and [9]
4) [5], [8] and [1]
5) [5], [4], [3] and [2]
6) [6], [1], [2], [5], *among other possibilities...*


## Features
The player is able to:
* Insert his/her name and start a match.
* End up a match at any moment.
* Visualize his/her score and the state of the board.
* Roll the dice.
* Select which box(es) will be closed or accumulate the obtained value in the dice rolling in his/her score.

Furthermore, this implementation has a *persistent ranking*, which stores the best 10 scores with the player's name.
