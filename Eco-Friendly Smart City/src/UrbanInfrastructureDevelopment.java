import java.io.Serializable;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class UrbanInfrastructureDevelopment implements Serializable {
    static final long serialVersionUID = 88L;

    /**
     * Given a list of Project objects, prints the schedule of each of them.
     * Uses getEarliestSchedule() and printSchedule() methods of the current project to print its schedule.
     * @param projectList a list of Project objects
     */
    public void printSchedule(List<Project> projectList) {
        // TODO: YOUR CODE HERE
        for (Project project : projectList){
            List<Task> tasks = project.getTasks();
            int[] schedule = new int[tasks.size()];
            schedule = project.getEarliestSchedule();
            project.printSchedule(schedule);
            project.getProjectDuration();
        }
    }

    /**
     * TODO: Parse the input XML file and return a list of Project objects
     *
     * @param filename the input XML file
     * @return a list of Project objects
     */
    public List<Project> readXML(String filename) {
        List<Project> projectList = new ArrayList<>();
        // TODO: YOUR CODE HERE
        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList projects = doc.getElementsByTagName("Project");
            for (int i = 0; i < projects.getLength(); i++) {
                Node projectNode = projects.item(i);
                if (projectNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element projectElement = (Element) projectNode;
                    NodeList taskList = projectElement.getElementsByTagName("Task");
                    List<Task> tasks = new ArrayList<>();
                    for (int j = 0; j < taskList.getLength(); j++) {
                        Node taskNode = taskList.item(j);
                        if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element taskElement = (Element) taskNode;
                            int taskid = Integer.parseInt(taskElement.getElementsByTagName("TaskID").item(0).getTextContent());
                            String description = taskElement.getElementsByTagName("Description").item(0).getTextContent();
                            int duration = Integer.parseInt(taskElement.getElementsByTagName("Duration").item(0).getTextContent());
                            NodeList dependencies = taskElement.getElementsByTagName("DependsOnTaskID");
                            List<Integer> dependencyList = new ArrayList<>();
                            for (int k = 0; k < dependencies.getLength(); k++) {
                                dependencyList.add(Integer.parseInt(dependencies.item(k).getTextContent()));
                            }
                            Task task = new Task(taskid, description, duration, dependencyList);
                            tasks.add(task);
                        }
                    }
                    String name = projectElement.getElementsByTagName("Name").item(0).getTextContent();
                    Project project = new Project(name, tasks);
                    projectList.add(project);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projectList;
    }
}
