package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

class Graph {
   HashMap<String, List<Edge>> adjList;
    static HashMap<String, double[]> COUNTRIES = new HashMap<String, double[]>();
    List<String> vertexNames=new ArrayList<>();

    public Graph() {
        adjList = new HashMap<>();
    }
    public void print() {
    	for (Entry<String, List<Edge>> entry : adjList.entrySet()) {
    	      System.out.print("Key: " + entry.getKey() +" is ");
    	      for(int i=0;i< entry.getValue().size();++i) {
    	    	  System.out.print(" " + entry.getValue().get(i).destination);  
    	    	  System.out.print("  ");  
    	      }
    	      System.out.println();
    	      
    	    }
    }
  
    public void addVertex(String vertex) {
    	System.out.print(vertex);
        adjList.putIfAbsent(vertex, new LinkedList<>());
    }
   
        	
    public void addEdge(String source, String destination, double weight) {
    	
        Edge edge = new Edge(destination, weight);
        adjList.get(source).add(edge);
    }

    class Edge {
        String destination;
       double weight;

        public Edge(String destination, double weight) {
            this.destination = destination;
            this.weight = weight;
        }

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}
        
    }
}