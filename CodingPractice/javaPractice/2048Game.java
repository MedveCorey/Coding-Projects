public class 2048Game {
    private static final int SIZE = 4;
    private int[][] board;
    private boolean gameOver;

    public 2048Game(){
        board = new int[SIZE][SIZE];
        gameOver = false;
        addNewTile();
        addNewTile();
    }

    private void addNewTile(){
        //add a random tile between 2 or 4
    }

    private void printBoard(){
        for (int[] row : board){
            for(int cell : row){
                System.out.printf("%4d", cell);
            }
            System.out.println();
        }
        System.out.println();
    }

    private void moveLeft(){

    }

    private void moveRight(){

    }

    private void moveDown(){

    }
    
    private void moveUp(){
        
    }
    public static void main(String[] args){

    }
}
