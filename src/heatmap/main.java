package heatmap;

import java.util.List;

public class main {
	
	public static void main(String[] args) {
	String filename = args[0];
	String numRecs = args[1];
	//String[] arg = {"filename","10"};
	String[] arg = {filename, numRecs};
	readCSV list = new readCSV(arg);
	String test = list.markers.get(0).ADDR_PSPR.toString();
	System.out.println(test);
	int count = list.markers.size();
	System.out.println("Count: " + count);
			
			
	}
}
