package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class WindowDate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public WindowDate() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 699);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Listar Estadisticas de los atletas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_3 = new JButton("Limpiar ");
		btnNewButton_3.setBounds(24, 522, 218, 38);
		contentPane.add(btnNewButton_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 10, 544, 263);
		contentPane.add(textArea);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Por orden alfabetico");
		rdbtnNewRadioButton_1.setBounds(247, 347, 219, 43);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Por posicion en el campeoanto");
		rdbtnNewRadioButton.setBounds(248, 293, 218, 43);
		contentPane.add(rdbtnNewRadioButton);
		btnNewButton.setBounds(24, 293, 218, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Listado de atletas");
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
}
