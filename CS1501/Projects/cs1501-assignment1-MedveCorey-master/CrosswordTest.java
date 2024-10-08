import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CrosswordTest {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.nanoTime();
        new CrosswordTest(args);
        long endTime = System.nanoTime();
        System.out.println("Took "+(endTime-startTime)+" ns");
      }
    
    public CrosswordTest(String[] args) throws FileNotFoundException {
        //Read the dictionary
        Scanner fileScan = new Scanner(new FileInputStream(args[0]));
        String st;
        DictInterface D = new MyDictionary();
    
        while (fileScan.hasNext())
        {
            st = fileScan.nextLine();
            D.add(st);
        }
        fileScan.close();
    
        // Parse input file of the Crossword board to create 2-d grid of characters
    
        Scanner fReader = new Scanner(new FileInputStream(args[1]));
        String input = fReader.nextLine();
        int boardSize = Integer.parseInt(input);
    
        char[][] theBoard = new char[boardSize][boardSize];
    
        for (int i = 0; i < boardSize; i++)
        {
            String rowString = fReader.nextLine();
            for (int j = 0; j < boardSize; j++)
            {
                theBoard[i][j] = Character.toLowerCase(rowString.charAt(j));
            }
        }
        fReader.close();

        Crossword cr = new Crossword();
        long startTime = System.nanoTime();
        char[][] solution = cr.fillPuzzle(theBoard, D);
        long endTime = System.nanoTime();
        System.out.println("fillPuzzle: "+ (endTime-startTime));
        if(solution != null) {
            printSolution(solution);
            startTime = System.nanoTime();
            if(cr.checkPuzzle(theBoard, solution, D)){
                endTime = System.nanoTime();
                System.out.println("checkPuzzle: "+ (endTime-startTime));
                System.out.println("Solution is correct");    
            } else {
                endTime = System.nanoTime();
                System.out.println("fillPuzzle: "+ (endTime-startTime));
                System.out.println("Solution is incorrect");    
            }
        } else {
            System.out.println("No solutions found.");
        }
    }


    private void printSolution(char[][] board){
        int boardSize = board[0].length;
        for(int i=0; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                System.out.print(Character.toUpperCase(board[i][j]));
            }
            System.out.println();
        }
    }
}
