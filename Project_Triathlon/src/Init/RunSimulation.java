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

import controller.Championship;
import model.Amateur;
import model.Athlete;
import model.City;
import model.Competitor;
import model.Country;
import model.Modality;
import model.PhysicalConditions;
import model.Race;
import model.RaceThread;
import model.Stations;
import view.WindowRace;
import view.WindowStart;
import view.WindowSAGA;

public class RunSimulation {

		public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
	        // Load races from XML
	        WindowSAGA windowSAGA = new WindowSAGA();
			Championship.loadXML();
			WindowRace windowRace = new WindowRace();
			Championship championship = new Championship(windowRace);
            WindowStart windowStart = new WindowStart(championship);
			windowStart.setVisible(true);
	        
	      
	   }
}
	

