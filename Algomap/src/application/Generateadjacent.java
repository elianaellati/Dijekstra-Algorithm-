package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generateadjacent {
	public static void main(String[] args) throws FileNotFoundException {
  File file =new File("C:\\\\Users\\\\hp\\\\Downloads\\\\full.txt");
		Scanner scan = new Scanner(file);
String countryname="";
String linee = scan.nextLine();
linee=linee.trim();
String[] token;
token=linee.split(" ");
int numofvertix=Integer.parseInt(token[0]);
int numofedges=Integer.parseInt(token[1]);
System.out.println(numofvertix);
 System.out.println(numofedges);
       
		String[] arr = new String[numofvertix];
        List<String> print=new ArrayList<>();
		int i = 0;
		while (scan.hasNextLine()) {
			String s = scan.nextLine();
			System.out.println(s);
			countryname="";
			String[] tokens = s.split(" +");
			if(tokens.length>3) {
				for(int j=0;j<tokens.length-2;++j) {
					countryname=countryname+tokens[j]+" ";
					
				}
				arr[i++] = countryname;
			}else {
				arr[i++] = tokens[0];	
			}
			
	
		}
		
		for (int j = 0; j < numofedges;) {
		int b=	(int) (Math.random() * numofvertix);
                if(arr[(int) (j % (numofvertix))].compareToIgnoreCase(arr[b])!=0) {
                	String d=arr[(int) (j % (numofvertix))]+","+arr[b];	
                	print.add(d);
                	j++;
                }else {
                	j=j;
                }
		
	
		}

	    FileWriter writer;
		try {
			writer = new FileWriter("C:\\\\Users\\\\hp\\\\Downloads\\\\full.txt", true);
			   writer.write(System.lineSeparator());
			   writer.write(System.lineSeparator());
			 for (String line : print) {
			        writer.write(line + System.lineSeparator());
			      }
			 writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
     System.out.print(print.toString());
		scan.close();
	
	}
}
