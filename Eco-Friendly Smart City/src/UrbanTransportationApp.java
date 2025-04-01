import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class UrbanTransportationApp implements Serializable {
    static final long serialVersionUID = 99L;
    
    public HyperloopTrainNetwork readHyperloopTrainNetwork(String filename) {
        HyperloopTrainNetwork hyperloopTrainNetwork = new HyperloopTrainNetwork();
        hyperloopTrainNetwork.readInput(filename);
        return hyperloopTrainNetwork;
    }

    /**
     * Function calculate the fastest route from the user's desired starting point to 
     * the desired destination point, taking into consideration the hyperloop train
     * network. 
     * @return List of RouteDirection instances
     */
    public List<RouteDirection> getFastestRouteDirections(HyperloopTrainNetwork network) {
        List<RouteDirection> routeDirections = new ArrayList<>();
        
        // TODO: Your code goes here
        List<TrainLine> lines = network.lines;
        int totalPoints = 2;
        List<Station> allStations = new ArrayList<>();
        allStations.add(network.startPoint);
        for (int i = 0; i < network.numTrainLines; i++){
            for (int j = 0; j < lines.get(i).trainLineStations.size(); j++){
                allStations.add(lines.get(i).trainLineStations.get(j));
                totalPoints++;
            }   
        }
        allStations.add(network.destinationPoint);

        //Creating Adjacency Matrix
        double[][] graph = new double[totalPoints][totalPoints];
        boolean[][] trainRide = new boolean[totalPoints][totalPoints];
        for (int i = 0; i < totalPoints; i++){
            Station point1 = allStations.get(i);
            String[] split1 = point1.description.split(" ");
            for (int j = i; j < totalPoints; j++){
                Station point2 = allStations.get(j);
                String[] split2 = point2.description.split(" ");
                double sum = Math.abs(point1.coordinates.x - point2.coordinates.x)*Math.abs(point1.coordinates.x - point2.coordinates.x) + 
                Math.abs(point1.coordinates.y - point2.coordinates.y) * Math.abs(point1.coordinates.y - point2.coordinates.y);
                double distance = Math.sqrt(sum);
                if (split1.length == 4 && split2.length == 4 && split1[0].equals(split2[0]) && Integer.parseInt(split2[3])-Integer.parseInt(split1[3])==1){
                    graph[i][j] = distance / network.averageTrainSpeed;
                    graph[j][i] = distance / network.averageTrainSpeed;
                    trainRide[i][j] = true;
                    trainRide[j][i] = true;
                } else {
                    graph[i][j] = distance / network.averageWalkingSpeed;
                    graph[j][i] = distance / network.averageWalkingSpeed;
                }
            }
        }

        //Dijkstra's Algorithm to find Shortest Path
        double[] durations = new double[totalPoints];
        boolean[] visited = new boolean[totalPoints];
        int[] parents = new int[totalPoints];
        parents[0] = -1;
        int source = 0;
        durations[source] = 0;
        for (int i = 1; i < totalPoints; i++) {
            if (i != source){
                durations[i] = Integer.MAX_VALUE;    
            }		
		}

        for (int i = 1; i < totalPoints; i++){
            int nearestPoint = -1;
            double shortestDuration = Integer.MAX_VALUE;
            for (int pointIndex = 0; pointIndex < totalPoints; pointIndex++){
                if (!visited[pointIndex] && durations[pointIndex] < shortestDuration){
                    nearestPoint = pointIndex;
                    shortestDuration = durations[pointIndex];
                }    
            }
            visited[nearestPoint] = true;

            for (int pointIndex = 0; pointIndex < totalPoints; pointIndex++){
                double edgeDuration = graph[nearestPoint][pointIndex];
                if (edgeDuration > 0 && ((shortestDuration + edgeDuration) < durations[pointIndex])){
                    parents[pointIndex] = nearestPoint;
                    durations[pointIndex] = shortestDuration + edgeDuration;
                }
            }
        }

        //Getting the actual path from start to destination
        pathOf(totalPoints-1, parents, durations, routeDirections, allStations, trainRide);

        return routeDirections;
    }

    /**
     * Function to print the route directions to STDOUT
     */
    public void printRouteDirections(List<RouteDirection> directions) {
        
        // TODO: Your code goes here
        double totalDuration = 0;
        for (RouteDirection direction : directions){
            totalDuration += direction.duration;
        }
        System.out.println("The fastest route takes " + Math.round(totalDuration) + " minute(s).");
        System.out.println("Directions");
        System.out.println("----------");
        for (int i = 0; i < directions.size(); i++){
            int n = i+1;
            RouteDirection direction = directions.get(i);
            if (direction.trainRide){
                System.out.println(n + ". Get on the train from \"" + direction.startStationName + "\" to \"" + direction.endStationName + "\" for "
                + String.format("%.2f", direction.duration) + " minutes.");    
            } else {
                System.out.println(n + ". Walk from \"" + direction.startStationName + "\" to \"" + direction.endStationName + "\" for "
                + String.format("%.2f", direction.duration)  + " minutes.");   
            }        
        }
    }

    public void pathOf (int index, int[] parents, double[] durations, List<RouteDirection> directions, List<Station> allStations, boolean[][] trainRide){
        if (parents[index] == -1){ 
            return;
        }
        pathOf(parents[index], parents, durations, directions, allStations, trainRide);
        String station1 = allStations.get(index).description;
        String station2 = allStations.get(parents[index]).description;
        RouteDirection routeDirection = new RouteDirection(station2, station1, durations[index]-durations[parents[index]], trainRide[index][parents[index]]);
        directions.add(routeDirection);
    }
}