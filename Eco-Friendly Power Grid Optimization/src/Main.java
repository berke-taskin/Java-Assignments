import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Main class
 */
// FREE CODE HERE
public class Main {
    //Reading File
    public static String[] readFile(String path) {
        try {
            int i = 0;
            int length = Files.readAllLines(Paths.get(path)).size();
            String[] lines = new String[length];
                for (String line : Files.readAllLines(Paths.get(path)))  {
                    lines[i++] = line;
                }
            return lines;
    	} catch (IOException e) {
        	e.printStackTrace();
        	return null;
        }
    }

    public static void main(String[] args) throws IOException {

       /** MISSION POWER GRID OPTIMIZATION BELOW **/

        System.out.println("##MISSION POWER GRID OPTIMIZATION##");
        // TODO: Your code goes here
        // You are expected to read the file given as the first command-line argument to read 
        // the energy demands arriving per hour. Then, use this data to instantiate a 
        // PowerGridOptimization object. You need to call GetOptimalPowerGridSolutionDP() method
        // of your PowerGridOptimization object to get the solution, and finally print it to STDOUT.

        //Power Grid Optimization
        String inputFile =  args[0];
        String[] lines = readFile(inputFile);
        String[] line = lines[0].split(" ");
        ArrayList<Integer> demands1 = new ArrayList<Integer>(line.length);
        Integer sum = 0;
        for (int i = 0; i < line.length; i++){
            demands1.add(Integer.parseInt(line[i]));
            sum += Integer.parseInt(line[i]);
        }
        PowerGridOptimization powerGridOptimization = new PowerGridOptimization(demands1);
        OptimalPowerGridSolution optimalPowerGridSolution = powerGridOptimization.getOptimalPowerGridSolutionDP();

        //Printing Optimal Power Grid Solution
        System.out.println("The total number of demanded gigawatts: " + sum);
        System.out.println("Maximum number of satisfied gigawatts: " + optimalPowerGridSolution.getmaxNumberOfSatisfiedDemands());
        System.out.print("Hours at which the battery bank should be discharged: ");
        ArrayList<Integer> hours = optimalPowerGridSolution.getHoursToDischargeBatteriesForMaxEfficiency();
        for (int i = 0; i < hours.size(); i++){
            System.out.print(hours.get(i));
            if (i != hours.size()-1){
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println("The number of unsatisfied gigawatts: " + (sum-optimalPowerGridSolution.getmaxNumberOfSatisfiedDemands()));
        System.out.println("##MISSION POWER GRID OPTIMIZATION COMPLETED##");

        /** MISSION ECO-MAINTENANCE BELOW **/

        System.out.println("##MISSION ECO-MAINTENANCE##");
        // TODO: Your code goes here
        // You are expected to read the file given as the second command-line argument to read
        // the number of available ESVs, the capacity of each available ESV, and the energy requirements 
        // of the maintenance tasks. Then, use this data to instantiate an OptimalESVDeploymentGP object.
        // You need to call getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity) method
        // of your OptimalESVDeploymentGP object to get the solution, and finally print it to STDOUT.

        //Eco-Maintenance
        String inputFile2 =  args[1];
        String[] lines2 = readFile(inputFile2);
        String[] line1 = lines2[0].split(" ");
        String[] line2 = lines2[1].split(" ");
        ArrayList<Integer> demands2 = new ArrayList<Integer>(line2.length);
        for (int i = 0; i < line2.length; i++){
            demands2.add(Integer.parseInt(line2[i]));
        }
        OptimalESVDeploymentGP optimalESVDeploymentGP = new OptimalESVDeploymentGP(demands2);
        Integer minimumEVS = optimalESVDeploymentGP.getMinNumESVsToDeploy(Integer.parseInt(line1[0]), Integer.parseInt(line1[1]));

        //Printing Optimal ESV Deployment
        if (minimumEVS == -1){
            System.out.println("Warning: Mission Eco-Maintenance Failed.");
        } else {
            System.out.println("The minimum number of ESVs to deploy: " + minimumEVS);
            for (int i = 1; i < minimumEVS+1; i++){
                System.out.println("ESV " + i + " tasks: " + optimalESVDeploymentGP.getMaintenanceTasksAssignedToESVs().get(i-1));
            }    
        }
        System.out.println("##MISSION ECO-MAINTENANCE COMPLETED##");
    }
}
