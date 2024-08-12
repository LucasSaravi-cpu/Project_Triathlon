package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import dataaccess.DBManager;
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
import model.MeasurementUnit;
import model.Modality;
import model.Pedestrianism;
import model.PhysicalConditions;
import model.Race;
import model.RaceManager;
import model.RaceThread;
import model.Stations;
import model.Swimming;
import model.WeatherConditions;
import view.RacePanel;
import view.WindowDate;
import view.WindowRace;
import listeners.RaceListener;
public class Championship implements RaceListener {
    private WindowRace windowRace;
    private WindowDate windowDate;
	private static List <Race> races;
	private static List<Athlete> athletes;
	private static List<Athlete> SelectionAthletes;
	private static List<Race> SelectionRace;
	private List<RaceThread> raceThreads;
    private static int race;


	public Championship(WindowRace windowRace) {
        this.windowRace = windowRace;
        this.windowDate = new WindowDate(this);
        this.raceThreads = new ArrayList<>();
    }

    public WindowDate getWindowDate() {
        return windowDate;
    }
    public WindowRace getWindowRace() {
        return windowRace;
    }
    public static int getIndexRace(){
        return race;
    }
    public void startChampionship(){
        SelectionRace = getTop4Race(races);
        SelectionAthletes = getTop10Athletes(athletes);
        for (Race race: SelectionRace) {
            for (Athlete athlete: SelectionAthletes)
                race.getAthlete().add(athlete);
        }
        race=0;
    }

    public void startRace() {
        raceThreads.clear();
    	List<JButton> buttons = windowRace.getRacePanel().getButtons();
    	int startX = buttons.get(1).getX() + buttons.get(1).getWidth() + 10;

        windowRace.setRaceTitle(SelectionRace.get(race).getCity() + " " + SelectionRace.get(race).getDate());
    	int i=0;
    	for (Athlete athlete:  SelectionAthletes) {

    	//	race.getAthlete().add(athlete);

    		windowRace.getRacePanel().getLabels().get(i).setText(athlete.getName() + " " + athlete.getSurname());
    		RaceManager raceManager = new RaceManager();

    		RaceThread thread = new RaceThread(startX, windowRace.getRacePanel().getWidth()-80, this, athlete, this,raceManager, SelectionRace.get(race));

            athlete.updateEnergy(athlete.getHeight(),athlete.getWeight(), athlete.getStats().getMentalStrength(), athlete.getStats().getStamina());
            i++;

            // Añade un listener a cada hilo
            thread.addEnergyListener(new EnergyListener() {
                @Override
                public void energyChanged(EnergyEvent event) {
                    double energy = event.getEnergyLevel();
                    int index = raceThreads.indexOf(thread);
                    windowRace.getRacePanel().updateEnergyLabel(index, energy);

                }
            });



            raceThreads.add(thread);

            thread.start();


        }
        race++;



        }
  //  }

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
	public static void loadDatabase() {
	//Loads the climatic changes data base

		try {

		DBManager dbManager = new DBManager("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/Project_triathlon", "postgres", "1234");

		Connection connection = dbManager.getConnection();

		Statement statement = connection.createStatement();

	String query = "SELECT id ,description, measurementunit,lowertier,uppertier,swimmingweathering,cyclingweathering,pedestrianismweathering FROM weatherconditions ";

	 ResultSet TableWeatherConditions= statement.executeQuery(query);

	 while(TableWeatherConditions.next() ) {

		 MeasurementUnit measurementunit = new MeasurementUnit(TableWeatherConditions.getString("measurementunit"));


		 WeatherConditions weatherconditions = new  WeatherConditions(TableWeatherConditions.getInt("id"),TableWeatherConditions.getString("description"),
		 measurementunit,TableWeatherConditions.getDouble("lowertier"),TableWeatherConditions.getDouble("uppertier"),
		 TableWeatherConditions.getDouble("swimmingweathering"),TableWeatherConditions.getDouble("cyclingweathering"),TableWeatherConditions.getDouble("pedestrianismweathering"));

		 System.out.println(weatherconditions.toString());
	 }

		statement.close();
		connection.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}



	  public static List<Race> getTop4Race(List<Race> originalRace) {

	        Map<String, Race> bestRaceForModality = new LinkedHashMap<>();

	        Set<City> usedCities = new HashSet<>();

	        Set<Date> usedDates = new HashSet<>();


	        for (Race race : originalRace) {
	            String modalityName = race.getModality().getName();
	            City city = race.getCity();
	            Date date = race.getDate();


	            if (!bestRaceForModality.containsKey(modalityName) && !usedCities.contains(city) && !usedDates.contains(date)) {
	                //    Add the race to the map and mark the city and date as used
	                bestRaceForModality.put(modalityName, race);
	                usedCities.add(city);
	                usedDates.add(date);
	            }
	        }




	        return new ArrayList<>(bestRaceForModality.values()).subList(0, 4);
	    }

	  private static List<Athlete> getTop10Athletes(List<Athlete> originalAthletes) {
		  List<Athlete>  selectedAthletes = new ArrayList<>();

		 // Shuffle the list to obtain a random order
		  Collections.shuffle(originalAthletes);


	        //Limit the size to 10 or the size of the original list, whichever is smaller
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
        windowDate.setNewRace();

    }
    public static void sortByAlphabeticOrder(){
        SelectionAthletes.sort(Comparator.comparing(Athlete::getSurname));
    }


	public static List<Race> getRaces() {
		return races;
	}

	public static void setRaces(List<Race> races) {
		Championship.races = races;
	}













}

