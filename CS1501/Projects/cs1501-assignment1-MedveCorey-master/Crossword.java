public final class Crossword implements WordPuzzleInterface {
  private StringBuilder[] rowStr;
  private StringBuilder[] colStr;
  private int boardSize;
  private int bs1;
  private int[] rowLastMinusPos;
  private int[] colLastMinusPos;
  private int row, col;
  private DictInterface D;
  private static char[][] empty;
  private char[][] filledBoard;
  private int DofRow;
  private int DofCol;

  /*
   * fills out a word puzzle defined by an empty board.
   * The characters in the empty board can be:
   * '+': any letter can go here
   * '-': no letter is allowed to go here
   * a letter: this letter has to remain in the filled puzzle
   * 
   * @param board is a 2-d array representing the empty board to be filled
   * 
   * @param dictionary is the dictinary to be used for filling out the puzzle
   * 
   * @return a 2-d array representing the filled out puzzle
   */
  public char[][] fillPuzzle(char[][] board, DictInterface dict) {
    boardSize = board.length;
    empty = board;
    filledBoard = new char[boardSize][boardSize];
    bs1 = boardSize - 1;
    D = dict;
    row = 0;
    col = 0;
    rowStr = new StringBuilder[boardSize];
    colStr = new StringBuilder[boardSize];
    rowLastMinusPos = new int[boardSize];
    colLastMinusPos = new int[boardSize];
    for (int i = 0; i < boardSize; i++) {
      rowStr[i] = new StringBuilder();
      colStr[i] = new StringBuilder();
      rowLastMinusPos[i] = -1;
      colLastMinusPos[i] = -1;
    }
    
    if (solve(row, col)) {
      // filledBoard[0][0] = 'n';
      // filledBoard[0][1]='s';
      // filledBoard[0][2]='f';
      // filledBoard[1][0]= 'a';
      // filledBoard[1][1] = '-';
      // filledBoard[1][2]='e';
      // filledBoard[2][0]='b';
      // filledBoard[2][1]='m';
      // filledBoard[2][2]='w';
      return filledBoard;
    }
    
    return null;
  }

  private boolean solve(int row, int col) {
    // check to see if current row is equal to the board size
    if (row == boardSize) {
      return true;
    }

    switch (empty[row][col]) {
      case '+':
        for (char c = 'a'; c <= 'z'; c++) {
          rowStr[row].append(c);
          colStr[col].append(c);
          DofRow = D.searchPrefix(rowStr[row], rowLastMinusPos[row] + 1, rowStr[row].length() - 1);
          DofCol = D.searchPrefix(colStr[col], colLastMinusPos[col] + 1, colStr[col].length() - 1);
          // for debugging
          // System.out.println("(" + row + ", " + col + "): rowStr: " + rowStr[row] + " colStr: " + colStr[col]
          // + "\nD of row: " + D.searchPrefix(rowStr[row], rowLastMinusPos[row] + 1,
          // rowStr[row].length() - 1)
          // + " Dof col: " + D.searchPrefix(colStr[col], colLastMinusPos[col] + 1,
          // colStr[col].length() - 1));

          // check to see if the current prefix is in the dictionary
          if (DofRow != 0 && DofCol != 0) {
            // check to see if you are at the end of the board
            if (col == bs1 && row == bs1) {
              if (DofRow >= 2 && DofCol >= 2) {
                filledBoard[row][col] = c;
                return true;
              }
            } else if (col == bs1) {
              // check to see if the current prefix is a word in the dictionary for both the
              // row and the column.
              if (empty[row + 1][col] == '-') {
                if (DofRow >= 2 && DofCol >= 2) {
                  filledBoard[row][col] = c;
                  if (solve(row + 1, 0)) {
                    return true;
                  }
                }
              } else {
                if (DofRow >= 2 && DofCol % 2 == 1) {
                  filledBoard[row][col] = c;
                  if (solve(row + 1, 0)) {
                    return true;
                  }
                }
              }

            } else if (row == bs1) {
              if (empty[row][col + 1] == '-') {
                if (DofRow >= 2 && DofCol >= 2) {
                  filledBoard[row][col] = c;
                  if (solve(row, col + 1)) {
                    return true;
                  }
                }
              } else {
                if (DofRow % 2 == 1 && DofCol >= 2) {
                  filledBoard[row][col] = c;
                  if (solve(row, col + 1)) {
                    return true;
                  }
                }
              }

            } else {
              if (empty[row][col + 1] == '-') {
                if (empty[row + 1][col] == '-') {
                  if (DofRow >= 2  && DofRow >= 2) {
                    filledBoard[row][col] = c;
                    if (solve(row, col + 1)) {
                      return true;
                    }
                  }
                } else {
                  if (DofRow >= 2 && DofCol % 2 == 1) {
                    filledBoard[row][col] = c;
                    if (solve(row, col + 1)) {
                      return true;
                    }
                  }
                }

              } else if (empty[row + 1][col] == '-') {
                if (DofRow % 2 == 1  && DofCol >= 2) {
                  filledBoard[row][col] = c;
                  if (solve(row, col + 1)) {
                    return true;
                  }
                }
              } else {
                if (DofRow % 2 == 1  && DofCol % 2 == 1) {
                  filledBoard[row][col] = c;
                  if (solve(row, col + 1)) {
                    return true;
                  }
                }
              }
            }

          }
          filledBoard[row][col] = '+';
          rowStr[row].deleteCharAt(rowStr[row].length() - 1);
          colStr[col].deleteCharAt(colStr[col].length() - 1);
        }
        return false;
      case '-':

        int rowLastMinus = rowLastMinusPos[col];
        int colLastMinus = colLastMinusPos[row];
        rowLastMinusPos[row] = col;
        colLastMinusPos[col] = row;
        filledBoard[row][col] ='-';

        rowStr[row].append('-');
        colStr[col].append('-');
        if (col == bs1) {
          if (solve(row + 1, 0)) {
            return true;
          }
        } else {
          if (solve(row, col + 1)) {
            return true;
          }
        }
        rowLastMinusPos[row] = rowLastMinus;
        colLastMinusPos[col] = colLastMinus;
        rowStr[row].deleteCharAt(rowStr[row].length() - 1);
        colStr[col].deleteCharAt(colStr[col].length() - 1);
        return false;
      default:

      rowStr[row].append(empty[row][col]);
      colStr[col].append(empty[row][col]);
      DofRow = D.searchPrefix(rowStr[row], rowLastMinusPos[row] + 1, rowStr[row].length() - 1);
      DofCol = D.searchPrefix(colStr[col], colLastMinusPos[col] + 1, colStr[col].length() - 1);
      // for debugging
      // System.out.println("(" + row + ", " + col + "): rowStr: " + rowStr[row] + " colStr: " + colStr[col]
      // + "\nD of row: " + D.searchPrefix(rowStr[row], rowLastMinusPos[row] + 1,
      // rowStr[row].length() - 1)
      // + " Dof col: " + D.searchPrefix(colStr[col], colLastMinusPos[col] + 1,
      // colStr[col].length() - 1));

      // check to see if the current prefix is in the dictionary
      if (DofRow != 0 && DofCol != 0) {
        // check to see if you are at the end of the board
        if (col == bs1 && row == bs1) {
          if (DofRow >= 2 && DofCol >= 2) {
            filledBoard[row][col] = empty[row][col];
            return true;
          }
        } else if (col == bs1) {
          // check to see if the current prefix is a word in the dictionary for both the
          // row and the column.
          if (empty[row + 1][col] == '-') {
            if (DofRow >= 2 && DofCol >= 2) {
              filledBoard[row][col] = empty[row][col];
              if (solve(row + 1, 0)) {
                return true;
              }
            }
          } else {
            if (DofRow >= 2 && DofCol % 2 == 1) {
              filledBoard[row][col] = empty[row][col];
              if (solve(row + 1, 0)) {
                return true;
              }
            }
          }

        } else if (row == bs1) {
          if (empty[row][col + 1] == '-') {
            if (DofRow >= 2 && DofCol >= 2) {
              filledBoard[row][col] = empty[row][col];
              if (solve(row, col + 1)) {
                return true;
              }
            }
          } else {
            if (DofRow % 2 == 1 && DofCol >= 2) {
              filledBoard[row][col] = empty[row][col];
              if (solve(row, col + 1)) {
                return true;
              }
            }
          }

        } else {
          if (empty[row][col + 1] == '-') {
            if (empty[row + 1][col] == '-') {
              if (DofRow >= 2  && DofRow >= 2) {
                filledBoard[row][col] = empty[row][col];
                if (solve(row, col + 1)) {
                  return true;
                }
              }
            } else {
              if (DofRow >= 2 && DofCol % 2 == 1) {
                filledBoard[row][col] = empty[row][col];
                if (solve(row, col + 1)) {
                  return true;
                }
              }
            }

          } else if (empty[row + 1][col] == '-') {
            if (DofRow % 2 == 1  && DofCol >= 2) {
              filledBoard[row][col] = empty[row][col];
              if (solve(row, col + 1)) {
                return true;
              }
            }
          } else {
            if (DofRow % 2 == 1  && DofCol % 2 == 1) {
              filledBoard[row][col] = empty[row][col];
              if (solve(row, col + 1)) {
                return true;
              }
            }
          }
        }

      }
      filledBoard[row][col] = '+';
      rowStr[row].deleteCharAt(rowStr[row].length() - 1);
      colStr[col].deleteCharAt(colStr[col].length() - 1);
      return false;
    }

  }

  /*
   * checks if filledBoard is a correct fill for emptyBoard
   * 
   * @param emptyBoard is a 2-d array representing an empty board
   * 
   * @param filledBoard is a 2-d array representing a filled out board
   * 
   * @param dictionary is the dictinary to be used for checking the puzzle
   * 
   * @return true if rules defined in fillPuzzle has been followed and
   * that every row and column is a valid word in the dictionary. If a row
   * a column has one or more '-' in it, then each segment separated by
   * the '-' should be a valid word in the dictionary
   */
  public boolean checkPuzzle(char[][] emptyBoard, char[][] filledBoard, DictInterface dictionary) {
    for(int i = 0; i<boardSize; i++){
      for(int j = 0; j < boardSize; j++){
        if(emptyBoard[i][j] == '+' && !Character.isLetter(filledBoard[i][j])){
          System.out.println("incorrect Symbol");
          return false;
        }
        if(emptyBoard[i][j]== '-' && filledBoard[i][j] != '-'){
          System.out.println("minus not where minus should be");
          return false;
        }
        if(Character.isLetter(emptyBoard[i][j]) && emptyBoard[i][j] != filledBoard[i][j]){
          System.out.println("letter not where letter should be");
          return false;
        }
      }
    }
    for (int i = 0; i < boardSize; i++) {
      StringBuilder row = new StringBuilder();
      for(int j = 0; j < boardSize; j++){
        row.append(filledBoard[i][j]);
      }
      for(String s:row.toString().split("-")){
        int res = dictionary.searchPrefix(new StringBuilder(s));
        if(res != 2 && res != 3){
          System.out.println(i+": s: "+ s + "not a word in dictionary");
          return false;
        }
      }
  }
  return true;
  }
}
