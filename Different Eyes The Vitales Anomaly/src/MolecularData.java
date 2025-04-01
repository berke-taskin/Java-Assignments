import java.util.*;

// Class representing molecular data
public class MolecularData {

    // Private fields
    private final List<Molecule> molecules; // List of molecules

    // Constructor
    public MolecularData(List<Molecule> molecules) {
        this.molecules = molecules;
    }

    // Getter for molecules
    public List<Molecule> getMolecules() {
        return molecules;
    }

    // Method to identify molecular structures
    // Return the list of different molecular structures identified from the input data
    public List<MolecularStructure> identifyMolecularStructures() {
        ArrayList<MolecularStructure> structures = new ArrayList<>();

        /* YOUR CODE HERE */ 
        for (Molecule molecule : molecules){
            if (molecule.isVisited() == false){
                boolean newStructure = true;
                //If molecule needs to be added to an already created structure
                notNewStructure:
                for (MolecularStructure checkStructure : structures){
                    for (String checkBond : molecule.getBonds()){
                        if (checkStructure.hasMolecule(checkBond)){
                            checkStructure.addMolecule(molecule);
                            newStructure = false;
                            break notNewStructure;
                        }
                    }            
                }
                //If new structure needs to be created
                if (newStructure){
                    MolecularStructure structure = new MolecularStructure();
                    DFS(molecule, structure); 
                    structures.add(structure);    
                }             
            }
        }

        return structures;
    }

    // Method to print given molecular structures
    public void printMolecularStructures(List<MolecularStructure> molecularStructures, String species) {
        
        /* YOUR CODE HERE */ 
        System.out.println(molecularStructures.size() + " molecular structures have been discovered in " + species + ".");
        int count = 1;
        for (MolecularStructure structure : molecularStructures){
            System.out.println("Molecules in Molecular Structure " + count + ": " + structure.toString());
            count++;
        }
    }

    // Method to identify anomalies given a source and target molecular structure
    // Returns a list of molecular structures unique to the targetStructure only
    public static ArrayList<MolecularStructure> getVitalesAnomaly(List<MolecularStructure> sourceStructures, List<MolecularStructure> targeStructures) {
        ArrayList<MolecularStructure> anomalyList = new ArrayList<>();
        
        /* YOUR CODE HERE */ 
        for (MolecularStructure target : targeStructures){
            boolean found = false;
            for (MolecularStructure source : sourceStructures){
                if (target.equals(source)){
                    found = true;
                    break;
                }
            }
            if (!found){
                anomalyList.add(target);
            }
        }

        return anomalyList;
    }

    // Method to print Vitales anomalies
    public void printVitalesAnomaly(List<MolecularStructure> molecularStructures) {

        /* YOUR CODE HERE */ 
        System.out.println("Molecular structures unique to Vitales individuals:");
        for (MolecularStructure anomaly : molecularStructures){
            System.out.println(anomaly.toString());
        }
    }

    //Recursive Depth First Search for creating structures
    public void DFS(Molecule molecule, MolecularStructure structure){
        molecule.setVisited(true);
        structure.addMolecule(molecule);
        for (String bondstr : molecule.getBonds()){
            for (Molecule bond : molecules){
                if (bond.getId().equals(bondstr)){
                    if (bond.isVisited() == false){
                    DFS(bond, structure);    
                    }
                    
                }
            }
        }
    }
}
