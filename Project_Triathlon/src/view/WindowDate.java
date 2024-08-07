package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Championship;
import model.Athlete;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.TextField;
import java.awt.TextArea;

public class WindowDate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_3;
    private JRadioButton rdbtnNewRadioButton_1;
    private JRadioButton rdbtnNewRadioButton;
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
		
		
		btnNewButton = new JButton("Listar Estadisticas de los atletas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		 btnNewButton_3 = new JButton("Limpiar ");
		 btnNewButton_3.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		textArea.setText("");
		 	}
		 });
		btnNewButton_3.setBounds(24, 522, 218, 38);
		contentPane.add(btnNewButton_3);
		
		 rdbtnNewRadioButton_1 = new JRadioButton("Por orden alfabetico");
		rdbtnNewRadioButton_1.setBounds(247, 347, 219, 43);
		contentPane.add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton = new JRadioButton("Por posicion en el campeoanto");
		rdbtnNewRadioButton.setBounds(248, 293, 218, 43);
		contentPane.add(rdbtnNewRadioButton);
		btnNewButton.setBounds(24, 293, 218, 43);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Listado de atletas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				for ( Athlete athete : Championship.getSelectionAthletes()) {
					textArea.setText(WindowDate.ListAthete());
				}
				
			}
		});
		btnNewButton_1.setBounds(24, 393, 218, 43);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Listar estadisticas de la carrera");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnNewButton_2.setBounds(24, 455, 218, 43);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(WindowDate.class.getResource("/Image/Imagen.png")));
		lblNewLabel.setBounds(0, 0, 574, 686);
		contentPane.add(lblNewLabel);
		
	
	}
	
	
	
	public static String ListAthete () {
		
		StringBuilder sb = new StringBuilder();
		
		for ( Athlete athete : Championship.getSelectionAthletes()) {
		
		sb.append("\n Surname: ").append(athete.getSurname()).append("\n Name: ").append(athete.getName())
		  .append("\n Nationality: ").append(athete.getNationality()) .append("\n StageWinS: ").append(athete.getStagesWinS())
		  .append("\n StagesWinP: ").append(athete.getStagesWinP()).append("\n StagesWinC: ").append(athete.getStagesWinC())
		  .append("\n RacerWin: ").append(athete.getRacerWin()).append("\nRacer Desertion: ").append(athete.getRacerdesertion())
		.append("\nRacer Complete: ").append(athete.getRacercomplete());
			
		sb.append("\n");
		}
		
		return sb.toString();
	
		
	}
	
	
	
	
}
