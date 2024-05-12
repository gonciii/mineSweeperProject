import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    // Scanner and Random objects
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    // qualifications  --
    // değerlendirme formu 1
    private final int rowNumber;
    private final int colNumber;
    private final String[][] hiddenBoard;   // mines board
    private final String[][] visibleBoard;  // user board
    private final int size;
    private boolean gameOver;

    // created constructor method
    public MineSweeper(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.hiddenBoard = new String[rowNumber][colNumber];
        this.visibleBoard = new String[rowNumber][colNumber];
        this.size = (rowNumber * colNumber);
        this.gameOver = false;
        this.initializeBoards();
        this.prepareMines();
    }


    // for the game screen, I defined the cells that have never been opened with  (-),hiddenBoard with 0
    private void initializeBoards() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {

                hiddenBoard[i][j] = "0";
                visibleBoard[i][j] = "-";

            }
        }
    }


    // random number of mines -> (row*col) / 4 :
    // değerlendirme formu 8
    private void prepareMines() {
        int minesCount = 0;
        while (minesCount != (size / 4)) {
            int randomRow = RANDOM.nextInt(rowNumber);
            int randomCol = RANDOM.nextInt(colNumber);
            if (!hiddenBoard[randomRow][randomCol].equals("*")) {
                hiddenBoard[randomRow][randomCol] = "*";
                minesCount++;
            }
        }
    }

    // hidden board for mines board (not shown to the player)
    private void printHiddenBoard() {
        this.printBoard(this.hiddenBoard);
    }

    // visible board shown to the player
    private void printVisibleBoard() {
        this.printBoard(this.visibleBoard);
    }

    // print for the screen
    private void printBoard(String[][] board) {
        for (String[] string : board) {
            for (String cell : string) {
                System.out.print( cell + "  ");
            }
            System.out.println();
        }
    }

    // startGame() method: starts the game and receives moves from the user.
    // değerlerndirme formu 6-8-10-14-15
    public void startGame() {
        // location of mines board
        System.out.println("Mayınların Konumu ");
        this.printHiddenBoard();

        System.out.println("\n----------------Oyun başladı!--------------\n");


        while (!this.gameOver) {

            this.printVisibleBoard();

            System.out.print("Satır giriniz :");
            int row = SCANNER.nextInt();

            System.out.print("Sütun giriniz : ");
            int col = SCANNER.nextInt();

            // Warning ! for non-coordinate entries
            if (row < 0 || row >= this.rowNumber || col < 0 || col >= this.colNumber) {
                System.out.println("Geçersiz koordinatlar,lütfen tekrar giriniz : ");
                continue;
            }

            if (!this.visibleBoard[row][col].equals("-")) {
                System.out.println("Bu hücre zaten açıldı ! Lütfen başka bir hücre seçimi yapınız !");
                continue;
            }

            // losing situation
            this.uncoverCell(row, col);
            if (this.hiddenBoard[row][col].equals("*")) {
                this.gameOver = true;
                System.out.println("Game over !");

                this.printHiddenBoard();
                break;
            }
            // win situation
            if (this.isCheckWin()) {
                this.gameOver = true;
                System.out.println("Tebrikler! Oyunu kazandınız !");

                this.printHiddenBoard();
                break;
            }

        }

    }

    // calculates the mines around the cell.
    // değerlendirme formu 12
    private int countMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rowNumber && j >= 0 && j < colNumber && hiddenBoard[i][j].equals("*")) {
                    count++;
                }
            }
        }
        return count;
    }


    // with if-else structure and recursive  -> cell and neighboring cells
    // değerlendirme formu 12
    private void uncoverCell(int row, int col) {
        if (hiddenBoard[row][col].equals("*")) {
            visibleBoard[row][col] = "*";

            } else {
            int count = this.countMines(row, col);
            visibleBoard[row][col] = String.valueOf(count);
            if (count == 0) {
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (i >= 0 && i < rowNumber && j >= 0 && j < colNumber && visibleBoard[i][j].equals("-")) {
                            this.uncoverCell(i, j);   //
                            }
                        }
                    }
                }

            }

    }

    // Checks whether the game is won or not.
    // değerlendirme formu 13
    private boolean isCheckWin() {
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < colNumber; j++) {
                if (!hiddenBoard[i][j].equals("*") && visibleBoard[i][j].equals("-")) {
                    return false;
                    }
                }
            }
            return true;
    }



        // Initializes the MineSweeper game by prompting the user to input the size of the game screen.
        // The size must be at least 2x2.
    public static MineSweeper initialize() {

        // the user is given a message at the beginning of the game
        System.out.println("**** Mayın Tarlası Oyununa Hoşgeldiniz! ****\n");

        int row;
        int col;
        while (true) {
            // number of rows from user
            // değerlendirme formu 7
            System.out.print("Oyun ekranı için satır giriniz : ");
            row = SCANNER.nextInt();

            // number of columns from user
            System.out.print("Oyun ekranı için Sütun giriniz : ");
            col = SCANNER.nextInt();

                // If the entered row and column numbers are greater than 2x2, the loop is exited.
            if (row >= 2 && col >= 2) {
                break;
                }
            System.out.println("Matris boyutu 2x2'den küçük olamaz ! Satır ve Sütun sayılarını tekrar giriniz!\n");
            }


        // A new "Minesweeper" object is created with the entered row and column segments and returned.
        return new MineSweeper(row,col);
    }

}


