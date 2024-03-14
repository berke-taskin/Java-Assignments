import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
    //READING INPUT FILE FUNCTION
    public static String[] readFile(String path) {
        try {
        int i=0;
        int length=Files.readAllLines(Paths.get(path)).size();
        String[] results = new String[length];
            for (String line : Files.readAllLines(Paths.get(path)))  {
                results[i++]=line;
            }
        return results;
    	} catch (IOException e) {
        	e.printStackTrace();
        	return null;
        }
    }
    //WRITING OUTPUT FILE FUNCTION
    public static void writeFile(String path, String content, boolean append, boolean newLine) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(path, append));
            ps.print(content + (newLine ? "\n" : ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.flush();
                ps.close();
            }
        }
    }
    //CHECKING IF COLOR IS RED, YELLOW OR BLACK FUNCTION
    public static String whatsColor (String temp) {
        if (temp.equals("R") || temp.equals("Y") || temp.equals("B")){
            return "X";
        }
        else{
            return temp;
        }
    }
    //CALCULATING THE NEW SCORE FUNCTION
    public static int whatsScore (String temp, int Score){
        if (temp.equals("R")){
            return Score+10;
        }
        else if (temp.equals("Y")){
            return Score+5;
        }
        else if (temp.equals("B")){
            return Score-5;
        }
        else{
            return Score;
        }
    }
    public static void main (String[] args) {
        //Obtaining the board lineup from input
    	String[] lines1 = readFile("board.txt");
        String outPath = "output.txt";
        String [] check = lines1[0].split(" ");
        //Number of columns
        int lenX = check.length;
        //Number of rows
        int lenY = lines1.length;
        String[][] board = new String [lenY][lenX];
        //Showing the initial board
        writeFile(outPath,"",false,false);
        writeFile(outPath,"Game board:", true, true);
        int index1 = 0;
        int currentRow = 0;
        int currentCol = 0;
        //Creating a 2D Array for board
    	for (String line : lines1) {
            String[] split = line.split(" ");
            int index2 = 0;
            for (String x : split){
                if (x.equals("*")){
                    currentRow = index1;
                    currentCol = index2;
                }                            
                board [index1][index2] = x;
                index2 += 1;
            }
            index1 += 1;
            writeFile(outPath,line, true, true);
        }
        //Obtaining the move information from input
        writeFile(outPath,"",true,true);
        String[] lines2 = readFile("move.txt");
        //Showing the moves to be played
        writeFile(outPath,"Your movement is:", true, true);
        int Score = 0;
        boolean End = false;
        //Executing the code by the user's next move
    	for (String line : lines2) {
            String[] splitMove = line.split(" ");
            String temp = new String();
            String checked = new String();
            writeFile(outPath,line, true, true);
            for (String move : splitMove){
                //If the next move is Left
                if (move.equals("L")){
                    if (currentCol>0){ //Inside the board
                        temp = board[currentRow][currentCol-1];
                        //If the moved place is not a Wall or Hole
                        if (!temp.equals("W") && !temp.equals("H")){
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[currentRow][currentCol-1] = "*";
                            board[currentRow][currentCol] = checked;
                            currentCol = currentCol-1;
                        } //If the moved place is a Wall
                        else if (temp.equals("W")){
                            if (currentCol!=lenX-1){
                                temp = board[currentRow][currentCol+1];
                                checked = whatsColor(temp);
                                Score = whatsScore(temp,Score);
                                board[currentRow][currentCol+1] = "*";
                                board[currentRow][currentCol] = checked;
                                currentCol = currentCol+1;
                            }else{
                                temp = board[currentRow][0];
                                checked = whatsColor(temp);
                                Score = whatsScore(temp,Score);
                                board[currentRow][0] = "*";
                                board[currentRow][currentCol] = checked;
                                currentCol = 0;  
                            }
                        } //If the moved place is a Hole
                        else if (temp.equals("H")){
                            board[currentRow][currentCol] = " ";
                            End = true;
                            break;
                        }
                    }else{ //Outside the board
                        temp = board[currentRow][lenX-1];
                        //If the moved place is not a Wall or Hole
                        if (!temp.equals("W") && !temp.equals("H")){
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[currentRow][lenX-1] = "*";
                            board[currentRow][currentCol] = checked;    
                            currentCol = lenX-1;
                        } //If the moved place is a Wall
                        else if (temp.equals("W")){
                            temp = board[currentRow][currentCol+1];
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[currentRow][currentCol+1] = "*";
                            board[currentRow][currentCol] = checked;
                            currentCol = currentCol+1;
                        } //If the moved place is a Hole
                        else if (temp.equals("H")){
                            board[currentRow][currentCol] = " ";
                            End = true;
                            break;
                        }
                    }
                }
                //If the next move is Right
                else if (move.equals("R")){
                    if (currentCol<lenX-1){ //Inside the board
                        temp = board[currentRow][currentCol+1];
                        //If the moved place is not a Wall or Hole
                        if (!temp.equals("W") && !temp.equals("H")){
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[currentRow][currentCol+1] = "*";
                            board[currentRow][currentCol] = checked;
                            currentCol = currentCol+1;
                        } //If the moved place is a Wall
                        else if (temp.equals("W")){
                            if (currentCol!=0){
                                temp = board[currentRow][currentCol-1];
                                checked = whatsColor(temp);
                                Score = whatsScore(temp,Score);
                                board[currentRow][currentCol-1] = "*";
                                board[currentRow][currentCol] = checked;
                                currentCol = currentCol-1;
                            }else{
                                temp = board[currentRow][lenX-1];
                                checked = whatsColor(temp);
                                Score = whatsScore(temp,Score);
                                board[currentRow][lenX-1] = "*";
                                board[currentRow][currentCol] = checked;    
                                currentCol = lenX-1;
                            }
                        } //If the moved place is a Hole
                        else if (temp.equals("H")){
                            board[currentRow][currentCol] = " ";
                            End = true;
                            break;
                        } 
                    }else{ //Outside the board
                        temp = board[currentRow][0];
                        //If the moved place is not a Wall or Hole
                        if (!temp.equals("W") && !temp.equals("H")){
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[currentRow][0] = "*";
                            board[currentRow][currentCol] = checked;
                            currentCol = 0;
                        } //If the moved place is a Wall
                        else if (temp.equals("W")){
                            temp = board[currentRow][currentCol-1];
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[currentRow][currentCol-1] = "*";
                            board[currentRow][currentCol] = checked;
                            currentCol = currentCol-1;
                        } //If the moved place is a Hole
                        else if (temp.equals("H")){
                            board[currentRow][currentCol] = " ";
                            End = true;
                            break;
                        }
                    }
                }
                //If the next move is Up
                else if (move.equals("U")){
                    if (currentRow>0){ //Inside the board
                        temp = board[currentRow-1][currentCol];
                        //If the moved place is not a Wall or Hole
                        if (!temp.equals("W") && !temp.equals("H")){
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[currentRow-1][currentCol] = "*";
                            board[currentRow][currentCol] = checked;
                            currentRow = currentRow-1;
                        } //If the moved place is a Wall
                        else if (temp.equals("W")){
                            if (currentRow!=lenY-1){
                                temp = board[currentRow+1][currentCol];
                                checked = whatsColor(temp);
                                Score = whatsScore(temp,Score);
                                board[currentRow+1][currentCol] = "*";
                                board[currentRow][currentCol] = checked;
                                currentRow = currentRow+1;
                            }else{
                                temp = board[0][currentCol];
                                checked = whatsColor(temp);
                                Score = whatsScore(temp,Score);
                                board[0][currentCol] = "*";
                                board[currentRow][currentCol] = checked;
                                currentRow = 0;
                            }
                        } //If the moved place is a Hole
                        else if (temp.equals("H")){
                            board[currentRow][currentCol] = " ";
                            End = true;
                            break;
                        }
                    }else{ //Outside the board
                        temp = board[lenY-1][currentCol];
                        //If the moved place is not a Wall or Hole
                        if (!temp.equals("W") && !temp.equals("H")){
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[lenY-1][currentCol] = "*";
                            board[currentRow][currentCol] = checked;
                            currentRow = lenY-1;
                        } //If the moved place is a Wall
                        else if (temp.equals("W")){
                            temp = board[currentRow+1][currentCol];
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[currentRow+1][currentCol] = "*";
                            board[currentRow][currentCol] = checked;
                            currentRow = currentRow+1;
                        } //If the moved place is a Hole
                        else if (temp.equals("H")){
                            board[currentRow][currentCol] = " ";
                            End = true;
                            break;
                        }
                    }
                }
                //If the next move is Down
                else if (move.equals("D")){
                    if (currentRow<lenY-1){ //Inside the board
                        temp = board[currentRow+1][currentCol];
                        //If the moved place is not a Wall or Hole
                        if (!temp.equals("W") && !temp.equals("H")){
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[currentRow+1][currentCol] = "*";
                            board[currentRow][currentCol] = checked;
                            currentRow = currentRow+1;
                        } //If the moved place is a Wall
                        else if (temp.equals("W")){
                            if (currentRow!=0){
                                temp = board[currentRow-1][currentCol];
                                checked = whatsColor(temp); 
                                Score = whatsScore(temp,Score);
                                board[currentRow-1][currentCol] = "*";
                                board[currentRow][currentCol] = checked;
                                currentRow = currentRow-1;
                            }else{
                                temp = board[lenY-1][currentCol];
                                checked = whatsColor(temp);
                                Score = whatsScore(temp,Score);
                                board[lenY-1][currentCol] = "*";
                                board[currentRow][currentCol] = checked;
                                currentRow = lenY-1;
                            }
                        } //If the moved place is a Hole
                        else if (temp.equals("H")){
                            board[currentRow][currentCol] = " ";
                            End = true;
                            break;
                        }
                    }else{ //Outside the board
                        temp = board[0][currentCol];
                        //If the moved place is not a Wall or Hole
                        if (!temp.equals("W") && !temp.equals("H")){
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[0][currentCol] = "*";
                            board[currentRow][currentCol] = checked;
                            currentRow = 0;
                        } //If the moved place is a Wall
                        else if (temp.equals("W")){
                            temp = board[currentRow-1][currentCol];
                            checked = whatsColor(temp);
                            Score = whatsScore(temp,Score);
                            board[currentRow-1][currentCol] = "*";
                            board[currentRow][currentCol] = checked;
                            currentRow = currentRow-1;
                        } //If the moved place is a Hole
                        else if (temp.equals("H")){
                            board[currentRow][currentCol] = " ";
                            End = true;
                            break;
                        }
                    }
                }                           
            }
        }
        //Showing the final board
        writeFile(outPath,"", true, true);
        writeFile(outPath,"Your output is:", true, true);
        for (int i = 0; i < lenY; i++){
            for (int j = 0; j < lenX; j++){
                writeFile(outPath,board[i][j] + " ", true,false);
            }
            writeFile(outPath,"", true, true);
        }
        writeFile(outPath,"", true, true);
        //If the game ends with falling into a Hole
        if (End){
            writeFile(outPath,"Game Over!", true, true);
        }
        //Total Score
        writeFile(outPath,"Score: "+Score, true, true);
    }
}