package Init;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



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
        System.out.println("Elemento raíz: " + document.getDocumentElement().getNodeName());
        
    
        // Obtener todos los elementos <atleta>
        NodeList AthleteList = document.getElementsByTagName("atleta");
        System.out.println("Número de atletas: " + AthleteList.getLength());
        
        NodeList CarrerList = document.getElementsByTagName("carrera");
        System.out.println("Número de carreras: " + CarrerList.getLength());
        
        
      
        
        
      
	}
           
		

    
	
	

	


	public void loadDatabase() {}

}
