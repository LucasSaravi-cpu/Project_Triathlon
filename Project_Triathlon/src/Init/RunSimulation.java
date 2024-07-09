package Init;

import java.io.File;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controller.Race;
import model.Amateur;
import model.Athlete;
import model.City;
import model.Competitor;
import model.Country;
import model.Modality;
import model.PhysicalConditions;
import model.Stations;



public class RunSimulation {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		//Create weather conditions list
	  
	   List<Race> races =  Race.loadXML();
	   
	   //Create competitions
	   
	   
	   for (Race race: races) {
		   
		    System.out.println(race.toString());
		
	   }
   
   
  
	}
	
}
