package heatmap;

import java.util.List;

public class main {
	
	public static void main(String[] args) {
	String filename = args[0];
	String numRecs = args[1];
	//String[] arg = {"filename","10"};
	String[] arg = {filename, numRecs};
	readCSV list = new readCSV(arg);
	System.out.println("Lat: " + list.markers.get(0).lat.toString());
	System.out.println("Lon: " + list.markers.get(0).lon.toString());
	System.out.println(list.markers.size());
	
	
			 
			
	}
}
