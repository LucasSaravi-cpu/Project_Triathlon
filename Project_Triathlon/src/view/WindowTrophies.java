package view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Championship;

import javax.swing.JLabel;

public class WindowTrophies extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JLabel lblWinner;
	private JLabel lblSecond;
	private JLabel lblThird;

	public JLabel getLblSecond() {
		return lblSecond;
	}

	public void setLblSecond(JLabel lblSecond) {
		this.lblSecond = lblSecond;
	}

	public JLabel getLblThird() {
		return lblThird;
	}

	public void setLblThird(JLabel lblThird) {
		this.lblThird = lblThird;
	}

	public JLabel getLblWinner() {
		return lblWinner;
	}

	public void setLblWinner(JLabel lblWinner) {
		this.lblWinner = lblWinner;
	}

	public WindowTrophies(Championship controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 411);
      
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
		
	    lblWinner = new JLabel("");
		lblWinner.setBounds(264, 253, 135, 38);
		contentPane.add(lblWinner);
		
	    lblSecond = new JLabel("");
		lblSecond.setBounds(110, 295, 135, 38);
		contentPane.add(lblSecond);
		
		lblThird = new JLabel("");
		lblThird.setBounds(428, 313, 135, 38);
		contentPane.add(lblThird);
	}
}
