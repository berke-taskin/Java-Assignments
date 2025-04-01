import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

// Class representing the mission of Genesis
public class MissionGenesis {

    // Private fields
    private MolecularData molecularDataHuman; // Molecular data for humans
    private MolecularData molecularDataVitales; // Molecular data for Vitales

    // Getter for human molecular data
    public MolecularData getMolecularDataHuman() {
        return molecularDataHuman;
    }

    // Getter for Vitales molecular data
    public MolecularData getMolecularDataVitales() {
        return molecularDataVitales;
    }

    // Method to read XML data from the specified filename
    // This method should populate molecularDataHuman and molecularDataVitales fields once called
    public void readXML(String filename) {

        /* YOUR CODE HERE */ 
        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("HumanMolecularData").item(0).getChildNodes();;
            List<Molecule> humanMolecules = new ArrayList<Molecule>();
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeName() == "Molecule"){
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element moleculeElement = (Element) node;
                        String id = moleculeElement.getElementsByTagName("ID").item(0).getTextContent();
                        String bondStrength = moleculeElement.getElementsByTagName("BondStrength").item(0).getTextContent();
                        
                        NodeList bondsList = moleculeElement.getElementsByTagName("Bonds");
                        Element bondsElement = (Element) bondsList.item(0);
                        NodeList moleculeIDsList = bondsElement.getElementsByTagName("MoleculeID");

                        List<String> bonds = new ArrayList<String>();

                        for (int j = 0; j < moleculeIDsList.getLength(); j++) {
                            bonds.add(moleculeIDsList.item(j).getTextContent());
                        }
                        
                        int bondstr = Integer.parseInt(bondStrength);
                        Molecule molecule = new Molecule(id, bondstr, bonds);
                        humanMolecules.add(molecule); 
                    }                   
                }
                
            }
            MolecularData molecularData1 = new MolecularData(humanMolecules);
            molecularDataHuman = molecularData1;

            NodeList nodeList2 = doc.getElementsByTagName("VitalesMolecularData").item(0).getChildNodes();;
            List<Molecule> vitalesMolecules = new ArrayList<Molecule>();
            for (int temp = 0; temp < nodeList2.getLength(); temp++) {
                Node node = nodeList2.item(temp);
                if (node.getNodeName() == "Molecule"){
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element moleculeElement = (Element) node;
                        String id = moleculeElement.getElementsByTagName("ID").item(0).getTextContent();
                        String bondStrength = moleculeElement.getElementsByTagName("BondStrength").item(0).getTextContent();
                        
                        NodeList bondsList = moleculeElement.getElementsByTagName("Bonds");
                        Element bondsElement = (Element) bondsList.item(0);
                        NodeList moleculeIDsList = bondsElement.getElementsByTagName("MoleculeID");

                        List<String> bonds = new ArrayList<String>();

                        for (int j = 0; j < moleculeIDsList.getLength(); j++) {
                            bonds.add(moleculeIDsList.item(j).getTextContent());
                        }

                        int bondstr = Integer.parseInt(bondStrength);
                        Molecule molecule = new Molecule(id, bondstr, bonds);
                        vitalesMolecules.add(molecule); 
                    }    
                }
            }
            MolecularData molecularData2 = new MolecularData(vitalesMolecules);
            molecularDataVitales = molecularData2;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
