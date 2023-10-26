package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import application.Graph.Edge;
class Dijkstra {
	static Map<String, String> previous = new HashMap<>();
	static Map<String, Boolean> visited = new HashMap<>();
static String start="";
static String end="";
    static class Node implements Comparable<Node> {
        String name;
        double distance;

        Node(String name, double distance) {
            this.name = name;
            this.distance = distance;
        }

        public int compareTo(Node other) {
            return Double.compare(this.distance, other.distance);
        }
    }


    public static double dijkstra(Map<String, List<Edge>> graph, String start, String end) {
        Map<String, Double> distances = new HashMap<>();
        for (String node : graph.keySet()) {
            distances.put(node, Double.MAX_VALUE);
            visited.put(node,false);
        }
        distances.put(start, 0.0);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(start, 0));

     
        while (!priorityQueue.isEmpty()) {
            Node current = priorityQueue.poll();
            visited.put(current.name,true);
            if (current.name.equals(end)) {
                break;
            }
           
            for (Edge neighbor : graph.get(current.name)) {
            	  if(visited.get(neighbor.destination)==false) {
          
               double newDistance = current.distance + neighbor.weight;
                if (newDistance < distances.get(neighbor.destination)) {
                    distances.put(neighbor.destination, newDistance);
                    previous.put(neighbor.destination, current.name);
                    priorityQueue.offer(new Node(neighbor.destination, newDistance));
                }
               }
            }
            }
        
 System.out.print(visited);
        return distances.get(end);
    }

  



    public static List<String> getPath(String start,String end) {
    	
        List<String> path = new ArrayList<>();
        String current = end;
   	 
        if ( previous.containsKey(current) ) {
        	
        while ((current.compareToIgnoreCase(start)!=0)    ) {
        	
            path.add(current);
            current = previous.get(current);
      
        }
        path.add(start);
        Collections.reverse(path);
        
    
        }
        return path;
    }

}

