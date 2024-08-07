package controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;

import javax.swing.JButton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Events.EnergyEvent;
import listeners.EnergyListener;
import listeners.RaceListener;
import model.Amateur;
import model.Athlete;
import model.City;
import model.Competitor;
import model.Country;
import model.Cycling;
import model.Discipline;
import model.DisciplineDistance;
import model.Modality;
import model.Pedestrianism;
import model.PhysicalConditions;
import model.Race;
import model.RaceManager;
import model.RaceThread;
import model.Stations;
import model.Swimming;
import view.RacePanel;
import view.WindowRace;
import listeners.RaceListener;
public class Championship implements RaceListener {
    private WindowRace windowRace;
	private static List <Race> races;
	private static List<Athlete> athletes;
	private static List<Athlete> SelectionAthletes;
	private List<RaceThread> raceThreads;

     
	public Championship(WindowRace windowRace) {
        this.windowRace = windowRace;
        this.raceThreads = new ArrayList<>();
    }

    public WindowRace getWindowRace() {
        return windowRace;
    }

    public Iterator<Race> getRaces(){
    	 return races.iterator();
    }
    public void startRace() {
    	List<JButton> buttons = windowRace.getRacePanel().getButtons();
    	int startX = buttons.get(1).getX() + buttons.get(1).getWidth() + 10;
       
        SelectionAthletes =Championship.getTop10Athletes(athletes);
    		  
    	for (Athlete athlete:  SelectionAthletes) {
    		  
    		RaceManager raceManager = new RaceManager();
  
    		 RaceThread thread = new RaceThread(startX, windowRace.getRacePanel().getWidth()-80, this, athlete, this,raceManager);
    		
            athlete.updateEnergy(athlete.getHeight(),athlete.getWeight(), athlete.getStats().getMentalStrength(), athlete.getStats().getStamina());
       

            // Añade un listener a cada hilo
            thread.addEnergyListener(new EnergyListener() {
                @Override
                public void energyChanged(EnergyEvent event) {
                    double energy = event.getEnergyLevel();
                    int index = raceThreads.indexOf(thread);
                    windowRace.getRacePanel().updateEnergyLabel(index, energy);
                 
                }
            });
            
            
            
            raceThreads.add(  thread);
     
            thread.start();
          
            
        }}
   
    
    @Override
    public void positionChanged(RaceThread thread, int newPositionX) {
        int index = raceThreads.indexOf(thread);
        windowRace.updateLabelPosition(index, newPositionX);
        
     
    }
	public static void loadXML() throws ParserConfigurationException, SAXException, IOException {
		
		// Load the XML file
        File xmlFile = new File("triatlon.xml");

        // Create a DocumentBuilderFactory and a DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the XML file and get document
        org.w3c.dom.Document document = builder.parse(xmlFile);
  
       

        //  Print the root element
        document.getDocumentElement().normalize();
       // System.out.println("Root Element: " + document.getDocumentElement().getNodeName());
        
    
        // Get objects <athlete>
        NodeList AthleteList = document.getElementsByTagName("atleta");
      //  System.out.println("Number of athletes: " + AthleteList.getLength());
        
        NodeList CareerList = document.getElementsByTagName("carrera");
     //   System.out.println("Number of Races:" + CareerList.getLength());
        

        // List for elements <athlete>
        athletes = new ArrayList<>();
      
        
        // Iterate over each athlete
        for (int i = 0; i < AthleteList.getLength(); i++) {
            Node athleteNode = AthleteList.item(i);
            if (athleteNode.getNodeType() == Node.ELEMENT_NODE) {
                Element athleteElement =  (Element) athleteNode;
                
               
          
                

                int num = Integer.parseInt(athleteElement.getAttribute("numero"));
                String surname = getChildElementValue(athleteElement, "apellido");
                String name = getChildElementValue(athleteElement, "nombre");
                String id = getChildElementValue(athleteElement, "dni");
                String nationality = getChildElementValue(athleteElement, "nacionalidad");
                Date  birthDate = parseFecha(getChildElementValue(athleteElement, "fechaNacimiento"));
                String gender = getChildElementValue(athleteElement, "genero");
                String category = getChildElementValue(athleteElement, "categoria");
                double  weight = Double.parseDouble(getChildElementValue(athleteElement, "peso"));
                double  height = Double.parseDouble(getChildElementValue(athleteElement, "altura"));
                double swimmingAptitude = Double.parseDouble(getChildElementValue(athleteElement, "aptitudNatacion"));
                double cyclismAptitude = Double.parseDouble(getChildElementValue(athleteElement, "aptitudCiclismo"));
                double pedestrianismAptitude = Double.parseDouble(getChildElementValue(athleteElement, "aptitudPedestrismo"));
                double stamina = Double.parseDouble(getChildElementValue(athleteElement, "resistencia"));
                double mentalStrength = Double.parseDouble(getChildElementValue(athleteElement, "fortalezaPsicologica"));
                double percEndedRaces = Double.parseDouble(getChildElementValue(athleteElement, "porcentajeCarrerasTerminadas"));
                double economicBudget = Double.parseDouble(getChildElementValue(athleteElement, "presupuestoEconomico"));
                int ranking = Integer.parseInt(getChildElementValue(athleteElement, "ranking"));
                
                
                switch (gender) {
                	case "Masculino": gender = "Male";
                	default: gender = "Female";
                }
                
                PhysicalConditions physicalconditions  = new PhysicalConditions(swimmingAptitude, cyclismAptitude, pedestrianismAptitude, stamina, mentalStrength);
                
                if (category.equalsIgnoreCase("Amateur")) {
	                Athlete athlete = new Amateur(num, surname, name, id, new Country(nationality), birthDate, gender, weight, height, percEndedRaces, economicBudget, ranking, physicalconditions);
	                athletes.add(athlete);
                }
                else {
          
                	Athlete athlete = new Competitor(num, surname, name, id, new Country(nationality), birthDate, gender, weight, height, percEndedRaces, economicBudget, ranking, physicalconditions);
                	athletes.add(athlete);	
                }
                
                
            }
            
        }
        
       

        
        
        
        // List for athletes
        races = new ArrayList<>();
            
            
        for (int j = 0; j <  CareerList.getLength(); j++) {
            Node careerNode = CareerList.item(j);
               
            if (careerNode.getNodeType() == Node.ELEMENT_NODE) {
                Element careerElement =  (Element) careerNode;
            	
            
                    
                
                String cityname = getChildElementValue(careerElement, "ciudad");
                String countryname = getChildElementValue( careerElement,"pais");
                Date date = parseFecha(getChildElementValue( careerElement,"fecha"));
                String modalityname = getChildElementValue(careerElement,"modalidad");
                double swimming = Double.parseDouble(getChildElementValue(careerElement, "natacion"));
                double cyclism = Double.parseDouble(getChildElementValue(careerElement, "ciclismo"));
                double pedestrianism = Double.parseDouble(getChildElementValue(careerElement, "pedestrismo"));
                // Change names to English
                switch (modalityname) {
                	case "Larga distancia": modalityname="LongDistance";
                	                        break;
                	case "Media distancia": modalityname="MediumDistance";
                	                        break;
                	case "Distancia olímpico": modalityname="OlympicDistance";
                	                        break;                        
                }
                
               Discipline swimmingg = new Swimming();
               Discipline cyclimm= new Cycling();
               Discipline pedestriani = new Pedestrianism();
               
               List<DisciplineDistance> disciplinedistances = new ArrayList<>();
               
               DisciplineDistance disciplineDistanceSwimming = new  DisciplineDistance(swimming,swimmingg.time(modalityname),swimmingg);
               DisciplineDistance disciplineDistanceCycling = new  DisciplineDistance(cyclism,cyclimm.time(modalityname),cyclimm);
               DisciplineDistance disciplineDistancePedestrianism = new  DisciplineDistance(pedestrianism,pedestriani.time(modalityname),pedestriani);
               
               disciplinedistances.add(disciplineDistanceSwimming);
               disciplinedistances.add(disciplineDistanceCycling);
               disciplinedistances.add(disciplineDistancePedestrianism);
                    
                Country country = new Country(countryname);
                City city = new City(cityname , country);
                Modality modality = new Modality(modalityname,disciplinedistances);
                    
                    
                    
                List<Stations> stati = new ArrayList<>();
                Element provisioningstationsElement = (Element) careerElement.getElementsByTagName("puestos_aprovisionamiento").item(0);
                NodeList stationsList = provisioningstationsElement.getElementsByTagName("puesto");
                for (int p = 0; p < stationsList.getLength(); p++) {
                    Node puestoNode = stationsList.item(p);
                    if (puestoNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element puestoElement = (Element) puestoNode;
                        String tipo = puestoElement.getAttribute("tipo");
                        int numero = Integer.parseInt(puestoElement.getAttribute("numero"));
                        double distancia = Double.parseDouble(getChildElementValue(puestoElement, "distancia"));
                        
                        switch (tipo) {
                        	case "ciclismo": tipo = "Cyclism";
                        	case "pedestrismo": tipo = "Pedestrianism";
                        	default: tipo = "Swimming";
                        }

                        Stations station = new Stations(tipo, numero, distancia);
                        stati.add(station);
                    }
                }
                    
                Race race = new Race(city, country, date, modality, swimming, cyclism, pedestrianism, stati);
                    
                    
                races.add(race);
            }    
        }
            
            
		
    }
      
	
           
		

    
//Helper method for obtaining the value of a child element given its name
	private static String getChildElementValue(Element parentElement, String childElementName) {
		 NodeList nodeList = parentElement.getElementsByTagName(childElementName);
		 if (nodeList.getLength() > 0) {
		     return nodeList.item(0).getTextContent();
		 } else {
		     return ""; // Handle case where the element is not present
		 }
	}

// Method for parsing date in "yyyy-MM-dd" to Date
	private static Date parseFecha(String fechaStr) {
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        return  (Date) dateFormat.parse(fechaStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	public static void loadDatabase() {}
	//Loads the climatic changes data base    
	
	
	  private static List<Athlete> getTop10Athletes(List<Athlete> originalAthletes) {
		  List<Athlete>  selectedAthletes = new ArrayList<>();
	        
	        // Limitar el tamaño a 10 o el tamaño de la lista original, lo que sea menor
	        int numberOfAthletesToSelect = Math.min(originalAthletes.size(), 10);
	        
	        for (int i = 0; i < numberOfAthletesToSelect; i++) {
	            selectedAthletes.add(originalAthletes.get(i));
	        }
	        
	        return selectedAthletes;
	    }

	public static List<Athlete> getSelectionAthletes() {
		return SelectionAthletes;
	}

	public static void setSelectionAthletes(List<Athlete> selectionAthletes) {
		SelectionAthletes = selectionAthletes;
	}
    public static String ListAthletes () {

        StringBuilder sb = new StringBuilder();

        for (Athlete athlete: SelectionAthletes) {

            sb.append("\n Surname: ").append(athlete.getSurname()).append("\n Name: ").append(athlete.getName())
                    .append("\n Nationality: ").append(athlete.getNationality()) .append("\n StageWinS: ").append(athlete.getStagesWinS())
                    .append("\n StagesWinP: ").append(athlete.getStagesWinP()).append("\n StagesWinC: ").append(athlete.getStagesWinC())
                    .append("\n RacerWin: ").append(athlete.getRacerWin()).append("\n  Racer Desertion: ").append(athlete.getRacerdesertion())
                    .append("\n  Racer Complete: ").append(athlete.getRacercomplete());

            sb.append("\n");
        }

        return sb.toString();
    }
    public void allThreadsCompleted() {
        windowRace.setWindowDate();
    }
    public static void sortByAlphabeticOrder(){
        SelectionAthletes.sort(Comparator.comparing(Athlete::getSurname));
    }











}
