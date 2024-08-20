package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import listeners.ChronometerListener;


public class WindowsChronometer extends JFrame implements ChronometerListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel labelCronometro;



    public WindowsChronometer() {
        setTitle("Chronometer");
        setSize(267, 200);
        setBounds(300, 10, 267, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear y añadir el JLabel para mostrar el tiempo
        labelCronometro = new JLabel("00:00:00", SwingConstants.CENTER);
        labelCronometro.setFont(new Font("Serif", Font.BOLD, 24));
        add(labelCronometro, BorderLayout.CENTER);

        // Hacer la ventana visible
        setVisible(true);
    }


    @Override
    public void onTimeruptodate(int Hours, int minutes, int seconds) {
        // Actualizar el texto del JLabel
        labelCronometro.setText(String.format("%02d:%02d:%02d", Hours, minutes,seconds));
    }

}