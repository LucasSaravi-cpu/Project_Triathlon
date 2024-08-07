package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Championship;
import model.Athlete;
import model.RaceManager;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.TextArea;
import javax.swing.ButtonGroup;
public class WindowDate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_3;
    private JRadioButton rdbtnalphabetic;
    private JRadioButton rdbtnposition;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;



	public WindowDate() {
		
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 699);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		

		TextArea textArea = new TextArea();
		textArea.setBounds(21, 43, 517, 216);
		contentPane.add(textArea);
		
		
		btnNewButton = new JButton("List Athletes' stats");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnNewButton_3 = new JButton("Clean ");
		btnNewButton_3.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		textArea.setText("");
		 	}
		 });
		btnNewButton_3.setBounds(24, 522, 218, 38);
		contentPane.add(btnNewButton_3);
		
		rdbtnalphabetic = new JRadioButton("By alphabetic order");
		rdbtnalphabetic.setBounds(247, 347, 219, 43);
		contentPane.add(rdbtnalphabetic);
		rdbtnposition = new JRadioButton("By championship position");
		rdbtnposition.setBounds(248, 293, 218, 43);
		contentPane.add(rdbtnposition);
		btnNewButton.setBounds(24, 293, 218, 43);
		contentPane.add(btnNewButton);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnalphabetic);
		group.add(rdbtnposition);
		btnNewButton_1 = new JButton("List of athletes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnalphabetic.isSelected())
					Championship.sortByAlphabeticOrder();
				for (Athlete athete : Championship.getSelectionAthletes()) {
					textArea.setText(Championship.ListAthletes());
				}

			}
		});
		btnNewButton_1.setBounds(24, 393, 218, 43);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("List Race Stats");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Method for listing race Stats
				textArea.setText( RaceManager.updateRaceResults());
				
			}
		});
		btnNewButton_2.setBounds(24, 455, 218, 43);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(WindowDate.class.getResource("/Image/Imagen.png")));
		lblNewLabel.setBounds(0, 0, 574, 686);
		contentPane.add(lblNewLabel);
		
	
	}

	
}
