import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission POWER GRID OPTIMIZATION
 */
public class PowerGridOptimization {
    private ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour;

    public PowerGridOptimization(ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour){
        this.amountOfEnergyDemandsArrivingPerHour = amountOfEnergyDemandsArrivingPerHour;
    }

    public ArrayList<Integer> getAmountOfEnergyDemandsArrivingPerHour() {
        return amountOfEnergyDemandsArrivingPerHour;
    }
    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalPowerGridSolution
     */
    public OptimalPowerGridSolution getOptimalPowerGridSolutionDP(){
        // TODO: YOUR CODE HERE

        //D = Energy Demand List
        ArrayList<Integer> D = amountOfEnergyDemandsArrivingPerHour;
        D.add(0,0);

        //E = Energy Discharge List
        ArrayList<Integer> E = new ArrayList<Integer>(D.size());
        E.add(0);
        for(int i = 1; i < D.size()+1; i++) {   
            E.add(i*i);
        }

        //SOL and HOURS Lists
        ArrayList<Integer> SOL = new ArrayList<Integer>(D.size());
        ArrayList<ArrayList<Integer>> HOURS = new ArrayList<ArrayList<Integer>>(D.size());
        SOL.add(0);
        HOURS.add(new ArrayList<Integer>());

        //Dynamic Programming
        for (int j = 1; j < D.size(); j++){
            //Finding SOL(j)
            ArrayList<Integer> maxList = new ArrayList<Integer>();
            for (int i = 0; i < j; i++){
                Integer n1 = SOL.get(i) + Math.min(D.get(j),E.get(j-i));
                maxList.add(n1);
            }
            Integer n2 = Collections.max(maxList);
            SOL.add(n2);

            //Finding HOURS(j)
            int i = maxList.indexOf(n2);
            ArrayList<Integer> n3 = new ArrayList<Integer>();
            for (int k = 0; k < HOURS.get(i).size(); k++){
                n3.add(HOURS.get(i).get(k));
            }
            n3.add(j);
            HOURS.add(n3);
        }

        OptimalPowerGridSolution optimalPowerGridSolution = new OptimalPowerGridSolution(SOL.get(D.size()-1), HOURS.get(D.size()-1));

        return optimalPowerGridSolution;
    }
}
