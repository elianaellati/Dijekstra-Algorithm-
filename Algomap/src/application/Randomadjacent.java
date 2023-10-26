package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

public class Randomadjacent {

	  List<String> vertexNames=new ArrayList<>();
	  HashMap  <String, ArrayList> adjList;
	    Random random=new Random();
	    
	  public void generateEdges(int numEdges) {
	        for (int i = 0; i < numEdges; i++) {
	        	 System.out.println("hi");
	            String source = vertexNames.get((int) (Math.random() * vertexNames.size()));
	            String destination = vertexNames.get((int) (Math.random() * vertexNames.size()));
	            while (source.equals(destination)) {
	                destination = vertexNames.get((int) (Math.random() * vertexNames.size()));
	            }
	            if (!(adjList.containsKey(source))) {
	            	adjList.put(source, new ArrayList<>());
	            }
	            System.out.print("  "+destination);
	           if(!(adjList.get(source).contains(destination))){
	            adjList.get(source).add(destination);
	           }
	        }
	    }
	  
	  public void print (File file) {
	try {
		FileWriter fstream = new FileWriter(file, true);
		
		 for(Entry<String, ArrayList> entry : adjList.entrySet()){
		        // Use entry.getKey() to fetch Key from entry object 
		        System.out.println("Iterating cities of the country: "+ entry.getKey());
		        // Iterating the ArrayList 
		        // Use entry.getValue() to fetch ArrayList value from entry object 
		        for(Object city : entry.getValue().toArray())
		        {
		            System.out.println(city);
		        }
		    }    
		
		
		
		
		
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  }
	  
	  
	  
	  
	  
	  
}
