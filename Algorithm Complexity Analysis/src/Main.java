import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

class Main {
    public static void main(String args[]) throws IOException {
        //Reading data from csv
        String csv = "TrafficFlowDataset.csv", line;
        Integer[] durations = new Integer[251281];
        int Count = 0;
        boolean once = false;
        try (BufferedReader BufferedReader = new BufferedReader(new FileReader(csv))) {
            while ((line = BufferedReader.readLine()) != null){
                if (once){
                    String[] split = line.split(",");
                    durations[Count++] = Integer.parseInt(split[6]);   
                }
                once = true;
            }
            //Doing the Experiment
            Experiment.SortEXP(durations);
            Experiment.SearchEXP(durations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
