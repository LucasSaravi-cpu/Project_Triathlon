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

import model.Athlete;
import model.PhysicalConditions;



public class RunSimulation {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		
   loadXML();

	}
	
	
	
	
	public static void loadXML() throws ParserConfigurationException, SAXException, IOException {
		
		// Cargar el archivo XML
        File xmlFile = new File("triatlon.xml");

        // Crear un DocumentBuilderFactory y un DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
       DocumentBuilder builder = factory.newDocumentBuilder();

        // Parsear el archivo XML y obtener el Document
        org.w3c.dom.Document document = builder.parse(xmlFile);
  
       

        //  imprimir el elemento raíz
        document.getDocumentElement().normalize();
        System.out.println("Root Element: " + document.getDocumentElement().getNodeName());
        
    
        // Obtener todos los elementos <atleta>
        NodeList AthleteList = document.getElementsByTagName("atleta");
        System.out.println("Number of athletes: " + AthleteList.getLength());
        
        NodeList CarrerList = document.getElementsByTagName("carrera");
        System.out.println("Number of Races:" + CarrerList.getLength());
        

        // Lista para almacenar los objetos Atleta
        List<Athlete> athletes = new ArrayList<>();
      
        
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
                String categoria = getChildElementValue(athleteElement, "categoria");
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

               
                System.out.println("Atleta " + (i + 1) + ":");
                System.out.println("   Número: " + num);
                System.out.println("   Apellido: " + surname);
                System.out.println("   Nombre: " + name);
                System.out.println("   DNI: " + id);
                System.out.println("   Nacionalidad: " + nationality);
                System.out.println("   Fecha de Nacimiento: " + birthDate);
                System.out.println("   Género: " + gender );
                System.out.println("   Categoría: " + categoria);
                System.out.println("   Peso: " + weight);
                System.out.println("   Altura: " + height);
                System.out.println("   Aptitud Natación: " + swimmingAptitude);
                System.out.println("   Aptitud Ciclismo: " + cyclismAptitude);
                System.out.println("   Aptitud Pedestrismo: " + pedestrianismAptitude);
                System.out.println("   Resistencia: " + stamina);
                System.out.println("   Fortaleza Psicológica: " + mentalStrength);
                System.out.println("   % Carreras Terminadas: " + percEndedRaces);
                System.out.println("   Presupuesto Económico: " + economicBudget);
                System.out.println("   Ranking: " + ranking);
                
                
                PhysicalConditions physicalconditions  = new PhysicalConditions(swimmingAptitude,cyclismAptitude,pedestrianismAptitude,stamina, mentalStrength);
                
                //Ver si depende de la categoria crear clases de amateur o competicion o hacer un atributo de categoria 
                Athlete athlete = new Athlete(num, surname,name, id, nationality, birthDate, gender,weight,height, percEndedRaces, economicBudget, ranking,physicalconditions);
                
                //Esta lista es para agregar dentro de carrera que tiene una lista de atletas
                athletes.add(athlete);
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

// Método para parsear fecha en formato "yyyy-MM-dd" a Date
private static Date parseFecha(String fechaStr) {
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return  (Date) dateFormat.parse(fechaStr);
    } catch (ParseException e) {
        e.printStackTrace();
        return null;
    }
}


	


	public void loadDatabase() {}

}
