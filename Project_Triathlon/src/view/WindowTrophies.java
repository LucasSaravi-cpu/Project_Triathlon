package view;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Championship;
import javafx.geometry.Pos;

import javax.swing.JLabel;

public class WindowTrophies extends JFrame {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PositionLabel lblWinner;
	private PositionLabel lblSecond;
	private PositionLabel lblThird;
	


	  //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	public WindowTrophies() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(60, 150, 629, 411);

		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon icon = new ImageIcon(getClass().getResource("/Image/WindowEnd.png"));
				g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Configure lblWinner
		lblWinner = new PositionLabel();
		lblWinner.setBounds(280, 257, 135, 58);
		contentPane.add(lblWinner);

		// Configure lblSecond
		lblSecond = new PositionLabel();
		lblSecond.setBounds(122, 310, 135, 58);
		contentPane.add(lblSecond);

		// Configure lblThird
		lblThird = new PositionLabel();
		lblThird.setBounds(432, 325, 135, 58);
		contentPane.add(lblThird);
	}

	//------------------------------------------------>||GETTERS & SETTERS||<--------------------------------------------------------\\

	public PositionLabel getLblSecond() {
		return lblSecond;
	}

	public void setLblSecond(PositionLabel lblSecond) {
		this.lblSecond = lblSecond;
	}

	public PositionLabel getLblThird() {
		return lblThird;
	}

	public void setLblThird(PositionLabel lblThird) {
		this.lblThird = lblThird;
	}

	public PositionLabel getLblWinner() {
		return lblWinner;
	}

	public void setLblWinner(PositionLabel lblWinner) {
		this.lblWinner = lblWinner;
	}

	
	public void setWinnerText(String text) {
		lblWinner.setCustomText(text);
		lblWinner.repaint();
	}

	public void setSecondText(String text) {
		lblSecond.setCustomText(text);
		lblSecond.repaint();
	}

	public void setThirdText(String text) {
		lblThird.setCustomText(text);
		lblThird.repaint();
	}
}
