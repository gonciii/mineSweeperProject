

# Minesweeper Game
This project contains a Minesweeper game developed in Java. The game operates in a text-based format and is designed using two-dimensional arrays. The objective of the game is to clear all the cells without mines to win the game.

## GAME RULES

At the start of the game, the user will be asked to provide the matrix size (number of rows and columns). The minimum size is 2x2. The entered size will be used to create the game board.

Once the matrix is created, a quarter of the matrix will be randomly filled with mines.

The user must select a point on the matrix by providing row and column values.

The selected point will be checked to ensure it is within the matrix boundaries, and new coordinates will be requested until a valid coordinate is entered.

If a coordinate that has already been entered is provided, the user will be warned and asked to enter a new coordinate.

If there is a mine at the selected point, the game will be lost, and a message will be displayed.

If there is no mine at the selected point, the game will check all the neighboring positions of the point. The number of mines in the neighboring cells will be written at the user’s selected point.

If the user opens all the cells without hitting any mines, the game will be won, and an appropriate message will be displayed.

This file contains the steps needed to run the Minesweeper game and instructions on how to play the game. I hope you have a great time playing!

