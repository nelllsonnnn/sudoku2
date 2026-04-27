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

   public boolean isValid() {
      if(!checkRow()) {
         return false;
      }
      for(int spot = 1; spot <= 9; spot++) {
         if(!checkMini(spot)) {
         return false;
         }
      }
      return true;
   }

   private boolean checkRow() {
      for(int r = 0; r < board.length; r++) {
      Set<String> checkNum = new HashSet<>();
      
         for(int c = 0; c < board[r].length; c++) {
            if(!board[r][c].equals(".")) {
               if(checkNum.contains(board[r][c])) {
                  return false;
               }
          }
            checkNum.add(board[r][c]);
         }
      }
      return true;
   }
   
   private boolean checkColumn(){
      for(int c = 0; c < board[0].length; c++){
         Set<String> seen = new HashSet<>();
         
         for(int r = 0; r < board.length; r++){
            String val = board[r][c];
            
            if(!val.equals(".")){
               if (seen.contains(val)){
               return false;
               }
               seen.add(val);
            }
         }
      }
      return true;
   }
   
    
   private boolean checkData(){ //to make sure the cells go from 1 to 9 or "."
      Set<String> allowedValues = new HashSet<String>();
      allowedValues.add(".");
      
      for (int i = 1; i < board.length; i++) {
         allowedValues.add("" + 1);
      }
      
      for(int r = 0; r < board.length; r++){
         for (int c = 0; c < board[r].length; c++){
            return false;
         }
      }
      return false;

   }

   private String[][] miniSquare(int spot) {
      String[][] mini = new String[3][3];

      for(int r = 0; r < 3; r++) {
         for(int c = 0; c < 3; c++) {
         // whoa - wild! This took me a solid hour to figure out (at least)
         // This translates between the "spot" in the 9x9 Sudoku board
         // and a new mini square of 3x3
            mini[r][c] = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
         }
      }
      return mini;
   }

   private boolean checkMini(int spot) {
      Set<String> seen = new HashSet<>();
      String[][] mini = miniSquare(spot);
      
      for(int r = 0; r < mini.length; r++) {
         for(int c = 0; c < mini[r].length; c++) {
            String val = mini[r][c];
            
            if (!val.equals(".")){
               if(seen.contains(mini[r][c])) {
                  return false;
               }
               seen.add(val);
            }       
          }
      }
      return true;
   }
   
   public boolean isSolved() {
      Map<String, Integer> counts = new HashMap<String, Integer>();
      
      for(int r = 0; r < board.length; r++) { //add board.length
         for(int c = 0; c < board[r].length; c++) {
            String num = board[r][c];
            
            if(!counts.containsKey(num)) {
               counts.put(num, 1);
            } else {
               counts.put(num, counts.get(num) + 1);
            }
         }
      }
      for (int digit = 1; digit <= board.length; digit++) {
        String key = "" + digit;
        if (!counts.containsKey(key) || counts.get(key) != board.length) {
            return false;
        }
      }
      return isValid();
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
