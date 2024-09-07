package Init;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.SwingWorker;
import controller.Championship;
import view.LoadingFrame;
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
		LoadingFrame loadingFrame = new LoadingFrame();
		loadingFrame.setVisible(true);

		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {

				Championship.loadXML();
				setProgress(25);
				loadingFrame.updateProgress(25);

				WindowRace windowRace = new WindowRace();
				setProgress(50);
				loadingFrame.updateProgress(50);

				Championship championship = new Championship(windowRace);
				championship.startChampionship();
				setProgress(100);
				loadingFrame.updateProgress(100);

				return null;
			}


			@Override
			protected void done() {
				loadingFrame.close();
				try {
					WindowStart windowStart = new WindowStart(new Championship(new WindowRace()));
					WindowSAGA windowSAGA = new WindowSAGA(windowStart);
					windowSAGA.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		worker.execute();
	}

	public static void restartGame() throws SQLException {
		try {
			startGame();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}