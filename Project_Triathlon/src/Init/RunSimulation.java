package Init;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import controller.Championship;
import view.WindowRace;
import view.WindowStart;
import view.WindowSAGA;

public class RunSimulation {

	public static void main(String[] args) throws SQLException {
		try {
			startGame();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void startGame() throws ParserConfigurationException, SAXException, IOException, SQLException {

		Championship.loadXML();
		WindowRace windowRace = new WindowRace();
		Championship championship = new Championship(windowRace);
		championship.startChampionship();
		WindowStart windowStart = new WindowStart(championship);
		WindowSAGA windowSAGA = new WindowSAGA(windowStart);
	}

	public static void restartGame() throws SQLException {
		try {
			startGame();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
