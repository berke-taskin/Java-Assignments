import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission Eco-Maintenance
 */
public class OptimalESVDeploymentGP
{
    private ArrayList<Integer> maintenanceTaskEnergyDemands;

    /*
     * Should include tasks assigned to ESVs.
     * For the sample input:
     * 8 100
     * 20 50 40 70 10 30 80 100 10
     * 
     * The list should look like this:
     * [[100], [80, 20], [70, 30], [50, 40, 10], [10]]
     * 
     * It is expected to be filled after getMinNumESVsToDeploy() is called.
     */
    private ArrayList<ArrayList<Integer>> maintenanceTasksAssignedToESVs = new ArrayList<>();

    ArrayList<ArrayList<Integer>> getMaintenanceTasksAssignedToESVs() {
        return maintenanceTasksAssignedToESVs;
    }

    public OptimalESVDeploymentGP(ArrayList<Integer> maintenanceTaskEnergyDemands) {
        this.maintenanceTaskEnergyDemands = maintenanceTaskEnergyDemands;
    }

    public ArrayList<Integer> getMaintenanceTaskEnergyDemands() {
        return maintenanceTaskEnergyDemands;
    }

    /**
     *
     * @param maxNumberOfAvailableESVs the maximum number of available ESVs to be deployed
     * @param maxESVCapacity the maximum capacity of ESVs
     * @return the minimum number of ESVs required using first fit approach over reversely sorted items.
     * Must return -1 if all tasks can't be satisfied by the available ESVs
     */
    public int getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity)
    {
        // TODO: Your code goes here

        //Sorting demands
        Collections.sort(maintenanceTaskEnergyDemands, Collections.reverseOrder());

        //Capacity List for ESVs
        ArrayList<Integer> capacity = new ArrayList<Integer>();
        for (int i = 0; i < maxNumberOfAvailableESVs; i++){
            capacity.add(maxESVCapacity);
        }

        //ESV List
        ArrayList<ArrayList<Integer>> ESVs = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < maxNumberOfAvailableESVs; i++){
            ESVs.add(new ArrayList<Integer>());
        }

        //Greedy Programming
        for (int i = 0; i < maintenanceTaskEnergyDemands.size(); i++){
            Integer demand = maintenanceTaskEnergyDemands.get(i);
            boolean found = false;
            for (int j = 0; j < capacity.size(); j++){
                if (demand <= capacity.get(j)){
                    ArrayList<Integer> ESV = ESVs.get(j);
                    ESV.add(demand);
                    capacity.set(j, capacity.get(j)-demand);
                    found = true;
                    break;
                }
            }
            if (!found){
                return -1;
            }
        }

        //Removing Unused ESVs
        for (int i = ESVs.size()-1; i > 0; i--){
            if (ESVs.get(i).isEmpty()){
                ESVs.remove(i);
            }
        }

        maintenanceTasksAssignedToESVs = ESVs;
        
        return ESVs.size();
    }
}
