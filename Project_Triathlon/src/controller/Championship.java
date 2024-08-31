package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import Events.DisciplineChangeEvent;
import listeners.DisciplineChangeListener;
import model.athlete.PhysicalConditions;
import model.athlete.type.Amateur;
import model.athlete.Athlete;
import model.athlete.Competition;
import model.athlete.type.Competitor;
import model.race.*;
import model.race.discipline.*;
import model.race.location.City;
import model.race.location.Country;
import model.race.modality.*;
import model.race.thread.RaceThread;
import model.weather.MeasurementUnit;
import model.weather.WeatherConditions;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Events.EnergyEvent;
import Events.WeatherEvent;
import dataaccess.DBManager;
import listeners.EnergyListener;
import listeners.RaceListener;
import listeners.WeatherEventListener;
import view.*;

public class Championship implements RaceListener {
	
	
//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\
	
	 private WindowRace windowRace;
	 private Scoreboard scoreboard;
	 private WindowTrophies windowend;
	 private Weatherboard weatherboard;
     private WeatherSettingsWindow customWeatherPanel;
     private WindowChronometer windowChronometer;
     private static List <Race> races;
	 private static List<Athlete> athletes;
	 private static List<Race> SelectionRace;
	 private List<RaceThread> raceThreads;
	 private static int race;
     private int modalityIndex = 0;
	 private RaceManager raceManager;
	 private List<WeatherEventListener> weatherListeners = new ArrayList<>();;
	 private static WeatherConditions lastCondition = null;
     private static Chronometer chronometer;

  //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	public Championship(WindowRace windowRace) {
        this.windowRace = windowRace;
        this.scoreboard = new Scoreboard(this);
        this.raceThreads = new ArrayList<>();
        this.weatherboard = new  Weatherboard();
        this.customWeatherPanel = new WeatherSettingsWindow();
        this.windowChronometer = new WindowChronometer();
        this.windowend = new WindowTrophies(this);
    }
	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\
    public Scoreboard getScoreboard() {
        return scoreboard;
    }
    public WindowRace getWindowRace() {
        return windowRace;
    }
    public static int getIndexRace(){
        return race;
    }
    public WeatherSettingsWindow getCustomWeatherPanel() {
        return customWeatherPanel;
    }
    public WindowChronometer getWindowChronometer(){
        return windowChronometer;
    }
    public static List<Athlete> getAthletes() {
		return athletes;
	}

	public static void setAthletes(List<Athlete> athletes) {
		athletes = athletes;
	}
    
	public static List<Race> getRaces() {
		return races;
	}

	public static void setRaces(List<Race> races) {
		Championship.races = races;
	}

	
	public Weatherboard getWeatherboard() {
		return weatherboard;
	}
	public void setWeatherboard(Weatherboard weatherboard) {
		this.weatherboard = weatherboard;
	}
	public List<WeatherEventListener> getWeatherListeners() {
		return weatherListeners;
	}
	public void setWeatherListeners(List<WeatherEventListener> weatherListeners) {
		this.weatherListeners = weatherListeners;
	}
	public static Chronometer getChronometer(){
        return chronometer;
    }
	
	


	
	
	//------------------------------------------------>||CLASS METHODS||<--------------------------------------------------------\\
	
	public void Windowend() {
		windowend.setVisible(true);
	}
    public void startChronometer(){
        chronometer.start();
    }
    public void addChronometerListener(WindowChronometer windows){
        chronometer.addListener(windows);
    }

    public void resetTimer() {
        chronometer.reset();
        chronometer.start();
    }
    public void startChampionship(){
        SelectionRace = getTop4Race(races);
        for (Race race: SelectionRace) {
            for (Athlete athlete: athletes)
                race.getAthlete().add(athlete);
        }
        race=0;
    }

    public void startRace() {
        raceThreads.clear();
        RaceManager.clearFinishedAthletes();
    	int startX = windowRace.getRacePanel().getStartX();
        int endX = windowRace.getRacePanel().getEndX();
        chronometer = new Chronometer (SelectionRace.get(race).getModality());
        boolean[] weatherChanged = new boolean[2]; //2 modality changes
        List<Double> changePoints = SelectionRace.get(race).getDisciplineChangePoints();
        windowRace.getRacePanel().setDisciplineChangePoints(changePoints);
        SelectionRace.get(race).setStationPoints(changePoints, startX, endX);
        List<Double> stationPoints = SelectionRace.get(race).getStationPoints();
        windowRace.getRacePanel().setStationPoints(stationPoints);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        windowRace.setRaceTitle(SelectionRace.get(race).getCity() + " " + dateFormat.format(SelectionRace.get(race).getDate()));
    	
        
      
       
      this.addWeatherEventListener(new WeatherEventListener() {
    	    @Override
    	    public void onWeatherUpdate(WeatherEvent event) {
    	    	 WeatherConditions weatherCondition = event.getWeatherConditions();
    		        weatherboard.updateWeatherLabel(weatherCondition);
    		        if (weatherboard != null) {
    		            weatherboard.updateWeatherLabel(weatherCondition);
    		        }
    	    }
    	});


     changeWeatherConditions(0);
      
      
       
     Championship.TotalTimeForRace(SelectionRace.get(race)) ;
     
     SelectionRace.get(race).setCurrentneoprene(SelectionRace.get(race).UseOfNeoprene());
     
     System.out.println("El neoprene en la carrera "+SelectionRace.get(race).isCurrentneoprene());
     
        int i=0;
        raceManager = new RaceManager(race);
    	for (Athlete athlete:  athletes) {
            athlete.setUserSpeedAdjustment(5);
            athlete.setCurrentDiscipline(new Swimming());
    		windowRace.getRacePanel().getAthletePanels().get(i).getAthleteLabel().setText(athlete.getName() + " " + athlete.getSurname());
    		RaceThread thread = new RaceThread(startX, startX, endX, this, athlete, this,raceManager, SelectionRace.get(race), race);
    		athlete.updateEnergy(athlete.getHeight(),athlete.getWeight(), athlete.getStats().getMentalStrength(), athlete.getStats().getStamina());
            i++;
            
            athlete.setNeoprene(SelectionRace.get(race).UseOfNeoprene());
            
            
            
            System.out.println(athlete.isUsingNeoprene());

            //Add a Listener to each Thread
            thread.addEnergyListener(new EnergyListener() {
                @Override
                public void energyChanged(EnergyEvent event) {
                    double energy = event.getEnergyLevel();
                    int index = raceThreads.indexOf(thread);
                    windowRace.getRacePanel().updateEnergyLabel(index, energy);

                }
            });

            thread.addDisciplineChangeListener(new DisciplineChangeListener() {
                @Override
                public void disciplineChanged(DisciplineChangeEvent event) {

                    Discipline newDiscipline = event.getNewDiscipline();
                    if(event.getIsFirst()){
                        changeWeatherConditions(1);
                    }
                    int iconIndex = newDiscipline.getIconIndex();

                    SwingUtilities.invokeLater(() -> {
                       windowRace.getRacePanel().setIcon(raceThreads.indexOf(thread), iconIndex);
                    });



                }

            });
            windowRace.getRacePanel().getAthletePanels().get(athletes.indexOf(athlete)).addSpeedChangeListener(thread);


            raceThreads.add(thread);
            thread.start();
        }
    	
        race++;
        windowChronometer.setVisible(true);
        addChronometerListener(windowChronometer);
        chronometer.start();

        }


    @Override
    public void positionChanged(RaceThread thread, int newPositionX) {
        int index = raceThreads.indexOf(thread);
        windowRace.updateLabelPosition(index, newPositionX);
    }
    
    public void changeWeatherConditions(int index)
    {
        WeatherConditions weatherconditions =  Championship.getRandomWeatherCondition(Championship.loadDatabase());
        notifyWeatherUpdate(weatherconditions);
        if (index==0)
            SelectionRace.get(race).setCurrentWeatherCondition(weatherconditions);
        else
            SelectionRace.get(race-1).setCurrentWeatherCondition(weatherconditions);
    }
   
	public static void loadXML() throws ParserConfigurationException, SAXException, IOException {

		//Load the XML file
        File xmlFile = new File("triatlon.xml");

        //Create a DocumentBuilderFactory and a DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Parse the XML file and get the document
        org.w3c.dom.Document document = builder.parse(xmlFile);

       //Print the root element
        document.getDocumentElement().normalize();
       // System.out.println("Root Element: " + document.getDocumentElement().getNodeName());
       //Get objects <athlete>
        NodeList AthleteList = document.getElementsByTagName("atleta");
       //System.out.println("Number of athletes: " + AthleteList.getLength());
       NodeList CareerList = document.getElementsByTagName("carrera");
       //System.out.println("Number of Races:" + CareerList.getLength());

        //List for elements of type <athlete>
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
                List<Competition> comp = new ArrayList<>();
                for (int j=0; j<4; j++) {
                    comp.add(new Competition(0, "", null, null, null));
                }
                if (category.equalsIgnoreCase("Amateur")) {
	                Athlete athlete = new Amateur(num, name, surname, id, new Country(nationality), birthDate, gender, weight, height, percEndedRaces, economicBudget, ranking, physicalconditions, comp);
	                athletes.add(athlete);
                }
                else {

                	Athlete athlete = new Competitor(num, name, surname, id, new Country(nationality), birthDate, gender, weight, height, percEndedRaces, economicBudget, ranking, physicalconditions,comp);
                	athletes.add(athlete);
                }


            }

        }


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
                switch (cityname) {
                    case "Londres"        : cityname = "London";
                                            break;
                    case "Río de Janeiro" : cityname = "Rio de Janeiro";
                                            break;
                    case "Ciudad del Cabo": cityname = "Cape Town";
                                            break;

                }
                switch (countryname) {
                    case "Reino Unido"   : countryname = "United Kingdom";
                                           break;
                    case "Canadá"        : countryname = "Canada";
                                           break;
                    case "España"        : countryname = "Spain";
                                           break;
                    case "Brasil"        : countryname = "Brazil";
                                           break;
                    case "Sudáfrica"     : countryname = "South Africa";
                                           break;
                    case "Alemania"      : countryname = "Germany";
                                           break;
                    case "Estados Unidos": countryname = "USA";
                                           break;
                    case "Japan"         : countryname = "Japan";
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
                Modality modality;
                switch (modalityname){
                    case "Sprint": modality = new Sprint(disciplinedistances);
                                    break;
                    case "MediumDistance": modality = new MediumDistance(disciplinedistances);
                                            break;
                    case "LongDistance": modality = new LongDistance(disciplinedistances);
                                            break;
                    default: modality = new OlympicDistance(disciplinedistances);
                }



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
                        Stations station;
                        switch (tipo) {
                        	case "ciclismo": station = new Stations(new Cycling(), numero, distancia);
                                             break;
                        	case "pedestrismo": station = new Stations(new Pedestrianism(), numero, distancia);
                                             break;
                        	default: station = new Stations(new Swimming(), numero, distancia);;
                        }
                        stati.add(station);
                    }
                }
                
       
               Race race = new Race(city, country, date, modality, swimming, cyclism, pedestrianism, stati,  Championship.loadDatabase());

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

	
	
//Method for parsing date in "yyyy-MM-dd" to Date
	private static Date parseFecha(String fechaStr) {
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        return  (Date) dateFormat.parse(fechaStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	
	
	public static List<WeatherConditions> loadDatabase() {
		
	//Loads the Weather Conditions Database
		ArrayList<WeatherConditions> weatherConditions = new ArrayList<>();

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

		 weatherConditions.add(weatherconditions);
	 }

		statement.close();
		connection.close();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return weatherConditions;

	}


	  public static List<Race> getTop4Race(List<Race> originalRace) {
            Collections.shuffle(originalRace);
	        Map<String, Race> bestRaceForModality = new LinkedHashMap<>();
	        Set<City> usedCities = new HashSet<>();

	        Set<Date> usedDates = new HashSet<>();


	        for (Race race : originalRace) {
	            String modalityName = race.getModality().getClass().getSimpleName();
	            City city = race.getCity();
	            Date date = race.getDate();


	            if (!bestRaceForModality.containsKey(modalityName) && !usedCities.contains(city) && !usedDates.contains(date)) {
	                //    Add the race to the map and mark the city and date as used
	                bestRaceForModality.put(modalityName, race);
	                usedCities.add(city);
	                usedDates.add(date);
	            }
	        }
            List<Race> resultList = new ArrayList<>(bestRaceForModality.values()).subList(0, 4);
            resultList.sort(Comparator.comparing(Race::getDate));

	        return resultList;
	    }
  

	
    public static String ListAthletes () {

        StringBuilder sb = new StringBuilder();
        for (Athlete athlete: athletes) {
            sb.append(athlete);
        }

        return sb.toString();
    }
    public Object[][] getAthletesData(int index) {
        List<Athlete> sortedList;

        if (index == 2) {
            sortedList = sortByChampionshipPosition();
        } else {
            sortedList = sortByAlphabeticOrder();
        }

        Map<Athlete, Integer> championshipPositions = new HashMap<>();
        List<Athlete> championshipSorted = sortByChampionshipPosition();
        for (int i = 0; i < championshipSorted.size(); i++) {
            championshipPositions.put(championshipSorted.get(i), i + 1);
        }

        Object[][] tableData = new Object[sortedList.size()][15];

        for (int i = 0; i < sortedList.size(); i++) {
            Athlete athlete = sortedList.get(i);
            tableData[i][0] = championshipPositions.get(athlete);
            tableData[i][1] = athlete.getName();
            tableData[i][2] = athlete.getSurname();

            for (int j = 0; j < 4 && j < athlete.getCompetition().size(); j++) {
                Competition comp = athlete.getCompetition().get(j);
                int baseIndex = 3 + (j * 3); 

                for (DisciplineDistance dd : comp.getDistances()) {
                    System.out.println(dd.getDistance() + " " + dd.getTime() + " " + dd.getDiscipline());
                	
               	
                    switch (dd.getDiscipline().getClass().getSimpleName()) {
                        case "Swimming":
                            tableData[i][baseIndex] = dd.getTime();
                            break;
                        case "Cycling":
                            tableData[i][baseIndex + 1] = dd.getTime();
                            break;
                        case "Pedestrianism":
                            tableData[i][baseIndex + 2] = dd.getTime();
                            break;
                    }
                }
            }
        }

        return tableData;
    }


    public void allThreadsCompleted() {
        scoreboard.setNewRace();
        chronometer.stop();

    }
    public static List<Athlete> sortByAlphabeticOrder() {
        List<Athlete> sortedList = new ArrayList<>();
        for (Athlete athlete : athletes)
            sortedList.add(athlete);
        sortedList.sort(Comparator.comparing(Athlete::getSurname));
        return sortedList;
    }
    public static List<Athlete> sortByChampionshipPosition() {
        List<Athlete> sortedList = new ArrayList<>();
        for (Athlete athlete : athletes)
            sortedList.add(athlete);
        sortedList.sort((Comparator.comparingInt(athlete -> ((Athlete) athlete).getPoints()).reversed()));
        return sortedList;
    }
    public String updateRaceResults() {
        return raceManager.getCurrentPositions(raceThreads);
    }

  
    public static WeatherConditions getRandomWeatherCondition(List<WeatherConditions> conditionsList) {
     
        Random random = new Random();
        WeatherConditions newCondition;      
       do {
            int randomIndex = random.nextInt(conditionsList.size());
            newCondition = conditionsList.get(randomIndex);
        } while (newCondition.equals(lastCondition));
     
        lastCondition = newCondition;
        return newCondition;
    }
    
	 public void addWeatherEventListener(WeatherEventListener listener) {
	        weatherListeners.add(listener);
	    }

	    public void notifyWeatherUpdate(WeatherConditions weatherCondition) {
	        WeatherEvent event = new WeatherEvent(this, weatherCondition);     
	        for (WeatherEventListener listener : weatherListeners) {
	            listener.onWeatherUpdate(event);
	        }
	    }
	    
	    public static String GetListWeatherCondition(WeatherConditions weathercondition) {
	        StringBuilder sb = new StringBuilder();
	        Random random = new Random();
	        DecimalFormat format = new DecimalFormat("#.##");
	        double lowerBound = weathercondition.getLowertier();
	        double upperBound = weathercondition.getUppertier();
	        double randomValue = lowerBound + (upperBound - lowerBound) * random.nextDouble();
	        
	        
	        weathercondition.setCurrentTemperature(randomValue);
	        
	        
	        String formattedValue = format.format(randomValue);
	        sb.append(weathercondition.getDescription()).append("\n");
	        sb.append(formattedValue).append(" ").append(weathercondition.getMeasurementUnit());
	        return sb.toString();
	    }

    public void endChampionship() {
        weatherboard.setVisible(false);
        scoreboard.setVisible(false);
        windowRace.setVisible(false);
        windowChronometer.setVisible(false);
        StringBuilder results = new StringBuilder();
        athletes.sort(Comparator.comparingInt(athlete -> ((Athlete) athlete).getPoints()).reversed());
        int position=1;
        for (Athlete athlete : athletes) {
            results.append(athletes.indexOf(athlete) + 1);
            results.append(": ");
            results.append(athlete.getName()).append(" ").append(athlete.getSurname())
                    .append(". Points: ")
                    .append(athlete.getPoints())
                    .append(".\n");
        }

        WindowEndChampionship end = new WindowEndChampionship(results.toString());
        end.showWindow(true);
    }


    
    public static void TotalTimeForRace(Race race) {
    	
    	
    	
    	for (DisciplineDistance disciplinedistance : race.getModality().getDisciplinedistance()) {
    		
    		
    		
    		    Discipline discipline =disciplinedistance.getDiscipline();
    	        String modalityName = race.getModality().getClass().getSimpleName();
    	        int time =Chronometer.TimerMinutes(discipline.time(modalityName));
    	        discipline.setMaxTime(race, time);
    
    		
    	}
    	
    
    		
    }
  
    
  

}

