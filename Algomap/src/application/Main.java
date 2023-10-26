package application;
	
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	 Circle circle=new Circle();
	 ComboBox <String>comboBox = new ComboBox();
	  ComboBox<String> comboBox1 = new ComboBox();
	    Graph graph = new Graph();
	 String[] token;
	 String countryname="";
		Group pane=new Group();
     int k=0;

	@Override
	public void start(Stage primaryStage) {
		try {

			File output = new File("C:\\Users\\hp\\Downloads\\full.txt");
			if (!output.exists()) { 
				System.out.print("file does not exist");
				System.exit(2);
			}
			coordinates v=new coordinates();
			int numofvertix=0;
			int numofedges=0;
			Image image = new Image("world-map.gif");
		    ImageView imageView = new ImageView();
		    imageView.setImage(image);
		    imageView.setPreserveRatio(true);
		    imageView.setFitWidth(1000);
		    imageView.setFitHeight(595.83);
		    ObservableList<String> options = FXCollections.observableArrayList();

    Pane root = new Pane();
    root.getChildren().add(imageView);
    root.setMinSize(1000, 595.83);
    root.setMaxSize(1000, 595.83);
    root.setPrefSize(1000, 595.83);
   
     double imageWidth = imageView.getImage().getWidth() , 
     		imageHeight =imageView.getImage().getHeight();
    
     
		    AnchorPane root1 = new AnchorPane();
		 
		 
		    root1.setStyle("-fx-background-color: BEIGE;");
		  
		
		  
		   
			Scanner sc = new Scanner(output);
		   
	    
			if ("full.txt".length() != 0) {
				String line = sc.nextLine();
				line=line.trim();
				token=line.split(" ");
				numofvertix=Integer.parseInt(token[0]);
				numofedges=Integer.parseInt(token[1]);
			   
			    line = sc.nextLine();
				while (sc.hasNextLine() && !(line.isEmpty())) { 
					countryname="";
					double lon;
					double lat;
				token=line.split(" +");
				if(token.length>3) {
					for(int i=0;i<token.length-2;++i) {
						countryname=countryname+token[i]+" " ;
						
					}
				
				 lon=Double.parseDouble(token[token.length-2]);
				 lat=Double.parseDouble(token[token.length-1]);
				}else {
					 countryname=token[0];
					 lon=Double.parseDouble(token[1]);
					 lat=Double.parseDouble(token[2]);
				}
				 
				 System.out.println(countryname+" " +lon +" "+lat);
			     options.add(countryname);
				 double[] coordinates1 = {lon,lat};
				 graph.COUNTRIES.put(countryname,coordinates1);
				 graph.addVertex(countryname);
				 double x = (((lon + 180) / 360) * imageView.getFitWidth())-50;
				double latrad=lat*Math.PI/180;
				double mercN=Math.log(Math.tan((Math.PI/4)+(latrad/2)));
				double y =(imageView.getFitHeight()/2)-(imageView.getFitWidth()*mercN/(2*Math.PI))+90;
			
					 
				     Circle marker = new Circle(x, y, 2,Color.BLACK);
				   
			            marker.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			            
			                public void handle(MouseEvent event) {
			                    double x =marker.getCenterX();
			                    double y = marker.getCenterY();
			                	System.out.println(x);
			                	System.out.println(y);
			                	
			                	
			                	String value=String.valueOf(x)+","+String.valueOf(y);
			                	String city=getKey(v.distances,value);
			                    marker.setFill(Color.RED);
			                    if(k==0) {
			                  
			                    comboBox.setValue(city);
			                    ++k;
			                    }else if(k==1) {
			                    	
			                    comboBox1.setValue(city);
			                    }
			                    System.out.print(city);
			                   
			                }
			            });
			            
			          
			         root.getChildren().add(marker);
					String coordinates=x+","+y;
					v.distances.put(countryname,coordinates);
					 line = sc.nextLine();	 
				}
			
				while(sc.hasNextLine()) {
					line = sc.nextLine();
					token=line.split(",");

				    if(graph.COUNTRIES.containsKey(token[0]) && graph.COUNTRIES.containsKey(token[1])) {
				    	
					double first[] =graph.COUNTRIES.get(token[0]);
					double second[] =graph.COUNTRIES.get(token[1]);
					double lon1=first[0];
					double lat1=first[1];
					double lon2=second[0];
					double lat2=second[1];
					
					
				        double dLat = Math.toRadians(lat2 - lat1);
				        double dLon = Math.toRadians(lon2 - lon1);
				        lat1 = Math.toRadians(lat1);
				        lat2 = Math.toRadians(lat2);

				        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
				        double c = 2 * Math.asin(Math.sqrt(a));
				       double weight= 6371 * c;

			
					System.out.println("Distance  Between "+token[0]+" and "+token[1]+"    "+weight);
				
					graph.addEdge(token[0],token[1],weight);
					

					}
				 
					 
				}
				
				
				  graph.print();
				
				
				
			}
			Label source = new Label("Source:");
			source.setFont(Font.font(25));
			source.setTextFill(Color.BLACK);
			source.setFont(Font.font(null, FontWeight.BOLD, source.getFont().getSize()));
			Label dest= new Label("Destination:");
			dest.setFont(Font.font(25));
			dest.setTextFill(Color.BLACK);
			dest.setFont(Font.font(null, FontWeight.BOLD,dest.getFont().getSize()));
			comboBox.setItems(options);
			comboBox1.setItems(options);
			VBox  h=new VBox();
			h.getChildren().addAll(source,comboBox);
			h.getChildren().addAll(dest,comboBox1);
			Button Run=new Button ("Run");
			Run.setFont(Font.font(25));
			Run.setTextFill(Color.BLACK);
			Run.setFont(Font.font(null, FontWeight.BOLD, Run.getFont().getSize()));
			h.getChildren().add(Run);
			Label path= new Label("Path:");
			path.setFont(Font.font(25));
			path.setTextFill(Color.BLACK);
			path.setFont(Font.font(null, FontWeight.BOLD,path.getFont().getSize()));
			h.getChildren().add(path);
			TextArea p=new TextArea();
			p.setPrefSize(120, 150);
			p.setMinWidth(100);
			p.setMinHeight(100);
			h.getChildren().add(p);
			Label d= new Label("Distance:");
			d.setFont(Font.font(25));
			d.setTextFill(Color.BLACK);
			d.setFont(Font.font(null, FontWeight.BOLD,d.getFont().getSize()));
			h.getChildren().add(d);
			TextField dd=new TextField();
			dd.setMinWidth(100);
			dd.setMinHeight(100);
			h.getChildren().add(dd);
			Button Back=new Button ("Clear");
			Back.setFont(Font.font(25));
			Back.setTextFill(Color.BLACK);
			Back.setFont(Font.font(null, FontWeight.BOLD, Back.getFont().getSize()));
			h.getChildren().add(Back);
			h.setSpacing(15);
			root1.getChildren().addAll(root,h);	
			
			root1.setRightAnchor(root, 430.0);
			root1.setLeftAnchor(h,1240.0); // 10 pixels from left
		
		
		Run.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent arg0) {
				Dijkstra d=new Dijkstra();
				 
				double r=d.dijkstra(graph.adjList,comboBox.getValue(),comboBox1.getValue());
				dd.setFont(Font.font(15));
				dd.setFont(Font.font(null, FontWeight.BOLD, dd.getFont().getSize()));
				dd.setText(String.valueOf(r));
				 System.out.println(comboBox1.getValue());
				List<String> path=d.getPath(comboBox.getValue(),comboBox1.getValue());
				p.setFont(Font.font(15));
				p.setFont(Font.font(null, FontWeight.BOLD, p.getFont().getSize()));
				int j=0;
			  
				double x1=0;
				double y1=0;
				if(path.size()>1) {
				for (int i = 0; i <path.size();++i) {
					 j=i+1; 
					 
		            p.appendText(path.get(i)+"\n");
					 System.out.println(path.get(i));
					
				    if(j<path.size()) {
				    
				    	 String cor= v.distances.get(path.get(i));
				    	
				    	 String corr= v.distances.get(path.get(j));
				    	Line line=new Line();
				    		token=cor.split(",");
				    	    x1=Double.parseDouble(token[0]);
				    		y1=Double.parseDouble(token[1]);
				    		 
				    		token=corr.split(",");
				    		 double x2=Double.parseDouble(token[0]);
				    	     double y2=Double.parseDouble(token[1]);
				    		
				    		   line.setStartX(x1);
				    	       line.setStartY(y1);
				    	       line.setEndX(x2);
				    	        line.setEndY(y2);

							    line.setStroke(Color.RED);
							    line.setStrokeWidth(2);
							    pane.getChildren().add(line);
							
							 
				    			
				    }
				    
				}
				root.getChildren().add(pane);
				
		
				
				}
				else {
					 p.appendText("There is no path"+"\n");
					
				}
				
				
			}
			
			
		});
			Back.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			k=0;
			p.clear();
			dd.clear();
			
			pane.getChildren().clear();
			root.getChildren().remove(pane);
			  comboBox.setValue(" ");
			  comboBox1.setValue(" ");
		}
		
	});

		
			
			Scene scene = new Scene(root1,1550,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public  String getKey (Map<String, String> distances,String value)
	{
	    for (Map.Entry<String ,String> entry: distances.entrySet())
	    {
	        if (value.equals(entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
}




