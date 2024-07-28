package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;

public class WindowsStart extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public WindowsStart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 963, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(35, 314, 201, 35);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(35, 426, 201, 35);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(35, 371, 201, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(WindowsStart.class.getResource("/Image/Start.jpg")));
		lblNewLabel.setBounds(-24, -49, 963, 603);
		contentPane.add(lblNewLabel);
	}
}
