import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HyperloopTrainNetwork implements Serializable {
    static final long serialVersionUID = 11L;
    public double averageTrainSpeed;
    public final double averageWalkingSpeed = 1000 / 6.0;;
    public int numTrainLines;
    public Station startPoint;
    public Station destinationPoint;
    public List<TrainLine> lines;

    /**
     * Method with a Regular Expression to extract integer numbers from the fileContent
     * @return the result as int
     */
    public int getIntVar(String varName, String fileContent) {
        Pattern p = Pattern.compile("[\\t ]*" + varName + "[\\t ]*=[\\t ]*([0-9]+)");
        Matcher m = p.matcher(fileContent);
        m.find();
        return Integer.parseInt(m.group(1));
    }

    /**
     * Write the necessary Regular Expression to extract string constants from the fileContent
     * @return the result as String
     */
    public String getStringVar(String varName, String fileContent) {
        // TODO: Your code goes here
        Pattern p = Pattern.compile("[\\t ]*" + varName + "[\\t ]*=[\\t ]*\"(.*)\"");
        Matcher m = p.matcher(fileContent);
        m.find();
        return m.group(1);
    }

    /**
     * Write the necessary Regular Expression to extract floating point numbers from the fileContent
     * Your regular expression should support floating point numbers with an arbitrary number of
     * decimals or without any (e.g. 5, 5.2, 5.02, 5.0002, etc.).
     * @return the result as Double
     */
    public Double getDoubleVar(String varName, String fileContent) {
        // TODO: Your code goes here
        Pattern p = Pattern.compile("[\\t ]*" + varName + "[\\t ]*=[\\t ]*([0-9]+(?:\\.[0-9]+)?)");
        Matcher m = p.matcher(fileContent);
        m.find();
        return Double.parseDouble(m.group(1));
    }

    /**
     * Write the necessary Regular Expression to extract a Point object from the fileContent
     * points are given as an x and y coordinate pair surrounded by parentheses and separated by a comma
     * @return the result as a Point object
     */
    public Point getPointVar(String varName, String fileContent) {
        Point p = new Point(0, 0);
        // TODO: Your code goes here
        Pattern p2 = Pattern.compile("[\\t ]*" + varName + "[\\t ]*=[\\t ]*\\([\\t ]*([0-9]+)[\\t ]*,[\\t ]*([0-9]+)[\\t ]*\\)");
        Matcher m = p2.matcher(fileContent);
        m.find();
        p.x = Integer.parseInt(m.group(1));
        p.y = Integer.parseInt(m.group(2));
        return p;
    } 

    /**
     * Function to extract the train lines from the fileContent by reading train line names and their 
     * respective stations.
     * @return List of TrainLine instances
     */
    public List<TrainLine> getTrainLines(String fileContent) {
        List<TrainLine> trainLines = new ArrayList<>();

        // TODO: Your code goes here
        String[] split = fileContent.split(":");
        
        //For every TrainLine
        for (int i = 0; i < split.length; i+=2){
            List<Station> trainlineStations = new ArrayList<>();
            String str = getStringVar("train_line_name", split[i]);
            int index = split[i+1].indexOf("=");
            String subStr = split[i+1].substring(index+1);
            String[] stations = subStr.split("\\)\\s*\\(");
            int Count = 1;
            
            //For every station in TrainLine
            for (String st : stations) {
                st = st.replaceAll("\\s*\\(|\\)\\s*", "");
                st = "train_line_stations = " + "(" + st + ")";
                Point p = getPointVar("train_line_stations", st);
                String str2 = str + " Line Station " + Count;
                Station station = new Station(p, str2);
                trainlineStations.add(station);
                Count++;
            }
            TrainLine trainline = new TrainLine(str, trainlineStations);
            trainLines.add(trainline);
        }

        return trainLines;
    }

    /**
     * Function to populate the given instance variables of this class by calling the functions above.
     */
    public void readInput(String filename) {

        // TODO: Your code goes here
        try {
            int i = 0;
            int length = Files.readAllLines(Paths.get(filename)).size();
            String[] input = new String[length];
            for (String line : Files.readAllLines(Paths.get(filename)))  {
                input[i++] = line;
            }
            String allString = new String();

            //Reading data from input
            for (int j = 0; j < input.length; j++){
                if (input[j].contains("num_train_lines")){
                    numTrainLines = getIntVar("num_train_lines", input[j]);
                }
                else if (input[j].contains("average_train_speed")){
                    averageTrainSpeed = (getDoubleVar("average_train_speed", input[j])*1000) / 60;
                }
                else if (input[j].contains("starting_point")){
                    Point p = getPointVar("starting_point", input[j]);
                    startPoint = new Station(p, "Starting Point");
                }
                else if (input[j].contains("destination_point")){
                    Point p = getPointVar("destination_point", input[j]);
                    destinationPoint = new Station(p, "Final Destination");
                }
                else if (input[j].contains("train_line_name")){
                    allString += input[j] + ":";
                    allString += input[j+1] + ":";
                }   
            }
            lines = getTrainLines(allString);
    	} catch (IOException e) {
        	e.printStackTrace();
        }
    }
}