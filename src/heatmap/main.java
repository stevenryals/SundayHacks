package heatmap;

import java.util.List;

public class main {
	
	public static void main(String[] args) {
		
	String[] arg = {"filename","10"};
	readCSV list = new readCSV(arg);
	String test = list.markers.get(0).ADDR_PSPR.toString();
	System.out.println(test);
	int count = list.markers.size();
	System.out.println("Count: " + count);
			
			
	}
}
