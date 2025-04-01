import java.util.*;

// Class representing the Mission Synthesis
public class MissionSynthesis {

    // Private fields
    private final List<MolecularStructure> humanStructures; // Molecular structures for humans
    private final ArrayList<MolecularStructure> diffStructures; // Anomalies in Vitales structures compared to humans
    private List<Molecule> humanSelected = new ArrayList<>(); // Selected molecules from human structures
    private List<Molecule> vitalesSelected = new ArrayList<>(); // Selected molecules from vitales structures

    // Constructor
    public MissionSynthesis(List<MolecularStructure> humanStructures, ArrayList<MolecularStructure> diffStructures) {
        this.humanStructures = humanStructures;
        this.diffStructures = diffStructures;
    }

    // Method to synthesize bonds for the serum
    public List<Bond> synthesizeSerum() {
        List<Bond> serum = new ArrayList<>();
        
        /* YOUR CODE HERE */ 
        //Getting molecules with weakest bond strengths
        int bondCount = 0;
        List<Molecule> potential = new ArrayList<>();
        for (MolecularStructure humanStructure : humanStructures){
            Molecule weakest = humanStructure.getMoleculeWithWeakestBondStrength();
            potential.add(weakest);
            humanSelected.add(weakest);
            bondCount++;
        }
        for (MolecularStructure diffStructure : diffStructures){
            Molecule weakest = diffStructure.getMoleculeWithWeakestBondStrength();
            potential.add(weakest);
            vitalesSelected.add(weakest);
            bondCount++;
        }   
        bondCount--;

        //Creating a list of potential bonds to be created
        List<Bond> potentialBonds = new ArrayList<>();
        for(int i = 0; i < potential.size()-1; i++){
            for(int j = i+1; j < potential.size(); j++){
                Molecule mol1 = potential.get(i);
                int int1 = Integer.parseInt(mol1.getId().substring(1));
                Molecule mol2 = potential.get(j);
                int int2 = Integer.parseInt(mol2.getId().substring(1));
                double average = (mol1.getBondStrength() + mol2.getBondStrength()) / 2.00;

                if(int2 > int1){
                    Bond bond = new Bond(mol1, mol2, average);
                    potentialBonds.add(bond);  
                } else {
                    Bond bond = new Bond(mol2, mol1, average);
                    potentialBonds.add(bond);  
                }            
            }
        } 

        //Sorting the list according to weights
        Collections.sort(potentialBonds, Comparator.comparingDouble(Bond::getWeight));

        //Creating the serum
        List<String> bonded = new ArrayList<>();
        for (int i = 0; i < potentialBonds.size(); i++){
            if (i == bondCount){
                break;
            }
            Bond bond = potentialBonds.get(i);
            if (!bonded.contains(bond.getTo().toString()) || !bonded.contains(bond.getFrom().toString())){
                serum.add(bond);
                bonded.add(bond.getTo().toString());
                bonded.add(bond.getFrom().toString());
            } else {
                bondCount++;
            }
        }

        return serum;
    }

    // Method to print the synthesized bonds
    public void printSynthesis(List<Bond> serum) {

        /* YOUR CODE HERE */ 
        System.out.println("Typical human molecules selected for synthesis: " + Arrays.toString(humanSelected.toArray()));
        System.out.println("Vitales molecules selected for synthesis: " + Arrays.toString(vitalesSelected.toArray()));
        System.out.println("Synthesizing the serum...");

        double total = 0;
        for (Bond bond : serum){
            System.out.println("Forming a bond between " + bond.getTo() + " - " + bond.getFrom() +" with strength " + String.format("%.2f", bond.getWeight()));
            total += bond.getWeight();
        }
        System.out.println("The total serum bond strength is " + String.format("%.2f", total));
    }
}
