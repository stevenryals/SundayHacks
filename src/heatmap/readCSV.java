package heatmap ;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;
import javax.xml.xpath.*;
import javax.xml.parsers.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class readCSV {

	
	List<marker> markers = null;
    
	public readCSV(String[] args) {
	String file = args[0];
	markers = new ArrayList<marker>();
	int numMarkersToReturn = Integer.parseInt(args[1]);
	//String file = "C:\\Users\\Steven\\Desktop\\Documents\\Visual Studio 2013\\Projects\\heatmap\\heatmap\\Files\\JCProperties.txt";
	run(file, numMarkersToReturn);
	
  }

  public void run(String csvFile, int numMarkers) {

	
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

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
			
			String address = tempMarker.ADDR_PSPR + " " + tempMarker.CITY + " AL " + tempMarker.ZIP;
			address = URLEncoder.encode(address, "UTF-8");
			String adrs =  "https://maps.googleapis.com/maps/api/geocode/xml?address="+address+"&sensor=false%22";
			URL url = new URL(adrs);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		    String xmlLatLocation = "//GeocodeResponse/result/geometry/location/lat";
		    String xmlLonLocation = "//GeocodeResponse/result/geometry/location/lon";
		    String docText = "";
		    String s;
		    while ((s = reader.readLine()) != null)
		        docText = docText + s;
		    
		    tempMarker.lat = getLat(docText);
		    tempMarker.lon = getLon(docText);
  
			markers.add(tempMarker);
			
		
		}
	} catch (FileNotFoundException e) {
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

	System.out.println("Done");

  
  } 
  
  public String getLat(String xmlDoc) {
	String results = null;
	   try {
	        DocumentBuilderFactory dbf =
	            DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(xmlDoc));

	        Document doc = db.parse(is);
	        NodeList nodes = doc.getElementsByTagName("lat");

	        // iterate the employees
	        for (int i = 0; i < nodes.getLength(); i++) {
	           Element element = (Element) nodes.item(i);
	           results = getCharacterDataFromElement(element);
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }	  return results;
  }
  
  public String getLon(String xmlDoc) {
		String results = null;
		   try {
		        DocumentBuilderFactory dbf =
		            DocumentBuilderFactory.newInstance();
		        DocumentBuilder db = dbf.newDocumentBuilder();
		        InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(xmlDoc));

		        Document doc = db.parse(is);
		        NodeList nodes = doc.getElementsByTagName("lng");

		        // iterate the employees
		        for (int i = 0; i < nodes.getLength(); i++) {
		           Element element = (Element) nodes.item(i);
		           results = getCharacterDataFromElement(element);
		        }
		    }
		    catch (Exception e) {
		        e.printStackTrace();
		    }	  return results;
	  }

  
  public static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	       CharacterData cd = (CharacterData) child;
	       return cd.getData();
	    }
	    return "?";
	  }
}

