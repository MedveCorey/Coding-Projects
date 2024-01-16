package SudokuGameApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class SudokuGameApp {

    int NUMBER_OF_LEVELS = 3;
    int NUMBER_OF_GAMES_PER_LEVEL = 12;
    int NUMBER_OF_ROWS = 9;
    int NUMBER_OF_COLS = 9;
    
    public static void main(String[] args) throws FileNotFoundException {
        new SudokuGameApp();
    }
    
    public SudokuGameApp() throws FileNotFoundException {
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
    private void goToMainMenu(int[][][][] sudokuGames){
        System.out.println("Select level of difficulty");
        System.out.println("1) Beginner");
        System.out.println("2) Intermediate");
        System.out.println("3) Advanced");
        System.out.println("4) Main Menu");
        
    }
    private int[][][]getAllGamesForLevel(int levelNumber, int[][][][] sudokuGames){
      
        return null;
      
    }
    private void printAllGamesForLevel(int[][][] allGamesForLevel){
        
    }
    private int[][] getChosenGame(int gameNumber, int[][][] allGamesForLevel){
        
        return null;
    }
}
    