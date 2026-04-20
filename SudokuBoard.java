import java.util.*;
import java.io.*;

public class SudokuBoard {
   private String[][] board;
   
   public SudokuBoard(String fileName) {
      board = new String[9][9];
      try {
         Scanner file = new Scanner(new File(fileName));
         
         for(int r = 0; r < board.length; r++) {
            String line = file.nextLine();
            for(int c = 0; c < board[0].length; c++) {
               board[r][c] = "" + line.charAt(c);
            }
         }
      } catch (FileNotFoundException e) {
         System.out.println("File not found.");
      }
   }
   
   public String toString() {
      String output = "";
      for (int r = 0; r < 9; r++) {
        if (r % 3 == 0) {
            output += "  ---------------------\n";
        }
        for (int c = 0; c < 9; c++) {
            if (c % 3 == 0) {
                output += "| ";
            }
            String value = board[r][c];
            if (value.equals(".")) {
                output += "  ";
            } else {
                output += value + " ";
            }
        }
        output += "|\n";
    }
    output += "  ---------------------\n";
    return output;
   }
}