//cjm235
//Corey Medve
package lab02_sudokugameapp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
        
public class Lab02_SudokuGameApp {



    int NUMBER_OF_LEVELS = 3;
    int NUMBER_OF_GAMES_PER_LEVEL = 12;
    int NUMBER_OF_ROWS = 9;
    int NUMBER_OF_COLS = 9;

    public static void main(String[] args) throws FileNotFoundException {
        new Lab02_SudokuGameApp();
    }

    public Lab02_SudokuGameApp() throws FileNotFoundException {
        Scanner sudokuDataBaseScanner = getSudokuDataBaseScanner();
        int[][][][] sudokuGames = getAllSudokuGames(sudokuDataBaseScanner);
        sudokuDataBaseScanner.close();
        goToMainMenu(sudokuGames);
    }

    private Scanner getSudokuDataBaseScanner() throws FileNotFoundException {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        Scanner scanner = new Scanner(file);
        return scanner;
    }

    private int[][][][] getAllSudokuGames(Scanner sudokuDataBaseScanner) {
        int[][][][] allSudokuGames = new int[NUMBER_OF_LEVELS][NUMBER_OF_GAMES_PER_LEVEL][NUMBER_OF_ROWS][NUMBER_OF_COLS];

        allSudokuGames[0] = getGamesForLevel(0, sudokuDataBaseScanner);
        allSudokuGames[1] = getGamesForLevel(1, sudokuDataBaseScanner);
        allSudokuGames[2] = getGamesForLevel(2, sudokuDataBaseScanner);

        return allSudokuGames;
    }

    private int[][][] getGamesForLevel(int level,  Scanner sudokuDataBaseScanner) {
        int[][][] gamesForLevelN = new int[NUMBER_OF_GAMES_PER_LEVEL][NUMBER_OF_ROWS][NUMBER_OF_COLS];
        int gameNumber = 0;
        for (int i = 0; i < NUMBER_OF_GAMES_PER_LEVEL; i++) {
            int[][] currentGame = new int[NUMBER_OF_ROWS][NUMBER_OF_COLS];
            String line = sudokuDataBaseScanner.nextLine();
            for (int j = 0; j < NUMBER_OF_ROWS; j++) {
                line = sudokuDataBaseScanner.nextLine();
                String[] items = line.split("(?!^)");
                for (int k=0; k<NUMBER_OF_COLS; k++) {
                    currentGame[j][k] = Integer.parseInt(items[k]);
                }
            }
            gamesForLevelN[gameNumber] = currentGame;
            gameNumber++;
        }
        return gamesForLevelN;
    }
    //main method (needs while loop)
    private void goToMainMenu(int[][][][] sudokuGames){

        int level = -1;
        while(level != 4){
            System.out.println("Select level of difficulty:\n1) Beginner\n2) Intermediate\n3) Advanced\n4) Main Menu");
            Scanner input = new Scanner(System.in);
            level = input.nextInt();
            if(level !=4) {
                int[][][] allGamesForLevel = getAllGamesForLevel(level-1,sudokuGames);
                printAllGamesForLevel(allGamesForLevel);
                System.out.println("What game number do you want to play? ");
                int game_number = input.nextInt();
                int[][] current_game = getChosenGame(game_number,allGamesForLevel);
                printChosenGameForChosenLevel(current_game);
            }
        }


    }

    private void printChosenGameForChosenLevel(int[][] current_game) {
        for(int row = 0; row <NUMBER_OF_ROWS; row++){
            for(int col = 0; col < NUMBER_OF_COLS; col++){
                System.out.print(current_game[row][col]+" ");
                if( col == NUMBER_OF_COLS){
                    System.out.println(" ");
                    }
                }
            if (row == NUMBER_OF_ROWS){
                System.out.println(" ");
            }
            System.out.println(" ");
        }
    }



    //return the boards for a particular level
    private int[][][]getAllGamesForLevel(int levelNumber, int[][][][] sudokuGames){
        return sudokuGames[levelNumber];

    }
    //print all the boards for a particular level
    private void printAllGamesForLevel(int[][][] allGamesForLevel){
        for (int i = 0; i < NUMBER_OF_GAMES_PER_LEVEL;i++){
            for(int row = 0; row <NUMBER_OF_ROWS; row++){
                for(int col = 0; col < NUMBER_OF_COLS; col++){
                    System.out.print(allGamesForLevel[i][row][col]);
                    if( col == NUMBER_OF_COLS){
                        System.out.println(" ");

                    }
                }
                if (row == NUMBER_OF_ROWS){
                    System.out.println(" ");
            }
            System.out.println(" ");
        }
        }

    }
    //return the 2D array of the board the user chose
    private int[][] getChosenGame(int gameNumber, int[][][] allGamesForLevel){
        return allGamesForLevel[gameNumber];
    }
}
