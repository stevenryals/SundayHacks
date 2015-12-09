package heatmap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class compute {

	
	public static void main(String[] args){
		
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		//String csvFile = args[0];
		//int numMarkers = Integer.parseInt(args[1]);
		
		String csvFile = "JCProperties.txt";
		int numMarkers = 10;
	
		String[] bldgValue = new String[numMarkers];
		String[] PrevParcelImp = new String[numMarkers];
		
		
		try {
			int count = 1;
			br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
					if (count > numMarkers) {
						return;
						}	
					
			count++;
			// use comma as separator
				String[] lineData = line.split(cvsSplitBy);

				heatmap.marker tempMarker = new marker();
				tempMarker.OBJECTID = lineData[0];
				tempMarker.OWNERNAME =lineData[1];
				tempMarker.ParcelNo =lineData[2];
				tempMarker.PARCELID =lineData[3];
				tempMarker.BLDG_NUM =lineData[4];
				tempMarker.ADDR_PSPR =lineData[5];
				tempMarker.APARTMENT =lineData[6];
				tempMarker.ZIP =lineData[7];
				tempMarker.CITY =lineData[8];
				tempMarker.SUBDIV_NAME =lineData[9];
				tempMarker.FIREDEPTNAME =lineData[10];
				tempMarker.NbhName =lineData[11];
				tempMarker.PROP_MAIL =lineData[12];
				tempMarker.CITYMAIL =lineData[13];
				tempMarker.STATE_Mail =lineData[14];
				tempMarker.ZIP_MAIL =lineData[15];
				tempMarker.BLOCK =lineData[16];
				tempMarker.PARCEL =lineData[17];
				tempMarker.TOTAPVALUE =lineData[18];
				tempMarker.LAND_VALUE =lineData[19];
				tempMarker.BLDG_VALUE =lineData[20];
				tempMarker.OTHERVALUE =lineData[21];
				tempMarker.GIS_ACRES =lineData[22];
				tempMarker.PrevParcelImp =lineData[23];
				tempMarker.PrevParcelTotal =lineData[24];
				tempMarker.DISTRICT =lineData[25];
				tempMarker.Plat_Book =lineData[26];
				tempMarker.Plat_Page =lineData[27];
				tempMarker.ESN_NUM =lineData[28];
				tempMarker.MapNumber =lineData[29];
				tempMarker.FeeDist =lineData[30];
				tempMarker.Cls =lineData[31];
				tempMarker.BLDG_IND =lineData[32];
				tempMarker.TAX_CODE =lineData[33];
				tempMarker.OBY_IND =lineData[34];
				tempMarker.AssdValue =lineData[35];
			}
				}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	}
			
	public double average(double[] array){
	double sum = 0;
	double avg = 0;
				
	for (int i = 0; i < array.length; i++){
		sum = sum + array[i];
	}
	
	avg = sum / array.length;
	return avg;
	
	}
	
	public double stdDev(double[] array, double avg){

	double stdDev = 0;
	double[] xbar = new double[array.length];
	double sigma = 0;
	
	for (int i = 0; i < array.length; i++ ){
		xbar[i] = array[i] - avg;
	}
	
	for (int i = 0; i < xbar.length; i++){ 
		xbar[i] = xbar[i]*xbar[i];
	}
	
	for (int i = 0; i < xbar.length; i++){
		sigma = sigma + xbar[i];
	}
	
	stdDev = Math.sqrt(sigma / (xbar.length - 1));

	return stdDev;
	}
	
	public void outliers(marker list, String FIELD, double mean, double stdDev, int devs){
		// how to specify field for marker type?
		
		double min = mean - (devs * stdDev);
		double max = mean + (devs * stdDev);
		
		for (int i = 0; i < list.size(); i++){
			if ((list.FIELD > max) OR (list.FIELD < min)){
				System.out.println("OUtlier: " + list.OBJECTID + " record at: " + list.FIELD);
			}
			
			
		}
		
		
	}
	
}
