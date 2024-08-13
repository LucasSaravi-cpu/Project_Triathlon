package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import view.WindowRace;
import controller.Championship;
public class WindowStart extends JFrame {
	
	//------------------------------------------------>||ATTRIBUTES||<--------------------------------------------------------\\


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private WindowRace wr;
    private Scoreboard dt;

    //------------------------------------------------>||CONSTRUCTORS||<------------------------------------------------------------\\
	public WindowStart(Championship controller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton_2 = new JButton("START");
		btnNewButton_2.setBounds(35, 314, 201, 35);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wr = controller.getWindowRace();
				wr.setVisible(true);
				controller.startRace();
			    controller.getScoreboard().setVisible(true);
				// We have to set dt visible when the race ends!!!!
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("LOAD");
		btnNewButton_1.setBounds(35, 426, 201, 35);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.setBounds(35, 371, 201, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 841, 540);
		lblNewLabel.setIcon(new ImageIcon(WindowStart.class.getResource("/Image/ImgStart.png")));
		contentPane.add(lblNewLabel);
	}
}
