package view;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Championship;

import javax.swing.JLabel;

public class WindowEnd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2 ;
    private JLabel lblNewLabel_3;
    
	public WindowEnd(Championship controller) {
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
		
	    lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(21, 251, 135, 38);
		contentPane.add(lblNewLabel_1);
		
	    lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(205, 251, 135, 38);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(448, 251, 135, 38);
		contentPane.add(lblNewLabel_3);
		
		
	
	}
}
