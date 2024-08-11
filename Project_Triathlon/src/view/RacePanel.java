package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class RacePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel lblNewLabel_1;
    private List<JButton> buttons;
    private List<JLabel> labels;
    private List <JLabel> energylabels;
    private int startX;
    private int endX;
    private ImageIcon finishLineIcon;
    private Image scaledFinishLineImage;
    public List<JButton> getButtons(){
    	return buttons;
    }
    public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public RacePanel() {
        buttons = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            JButton btn;
            if (i%2==0)
                btn = new JButton("-");
            else
                btn = new JButton("+");
            btn.setBounds(31 +(i % 2) * 46, 35 + (i / 2) * 46, 50, 21);
            buttons.add(btn);
            add(btn);
        }
        labels=new ArrayList<>();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/running.png"));
        Image image = icon.getImage(); // Obtén la imagen del ImageIcon
        Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Escala la imagen
        ImageIcon scaledIcon = new ImageIcon(scaledImage); // Crea un nuevo ImageIcon con la imagen escalada

        for (int i = 0, j = 0; i < 10; i++, j+=2) { // Suponiendo 10 corredores
            JLabel lblNewLabel = new JLabel(scaledIcon);
            lblNewLabel.setBounds(31, buttons.get(j).getY(), 110, 30); // Start position X set to 31
            labels.add(lblNewLabel);
            add(lblNewLabel);
        }
        
 
        
        energylabels = new ArrayList<>();
        int yPosition = (buttons.get(2).getY()-buttons.get(0).getY())/2;
        for (int i=0; i<10; i++) {
            JLabel energy = new JLabel ("Energy");
            energy.setBounds(54, labels.get(i).getY() + yPosition, 100, 20); // Ajustar X según sea necesario
            energylabels.add(energy);
            add(energy);
        }
        finishLineIcon = new ImageIcon(getClass().getResource("/Image/finish_line.png"));





        setPreferredSize(new Dimension(966, 613));
        setLayout(null);
        
       
    }

    public void updateLabelPosition(int index, int newPositionX) {
        if (index >= 0 && index < labels.size()) {
            JLabel label = labels.get(index);
            label.setLocation(newPositionX, label.getY());
        }
    }
    public void updateEnergyLabel(int index, double energy) {
        if (index >= 0 && index < energylabels.size()) {
            JLabel energyLabel = energylabels.get(index);
            energyLabel.setText(String.format("Energy: %.2f%%", energy));
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        JButton firstbutton = buttons.get(1);
        startX = firstbutton.getX() + firstbutton.getWidth() + 10;
        endX = getWidth() - 80;
        for (int i = 1; i < buttons.size(); i += 2) {
            JButton btn = buttons.get(i);
            int y = btn.getY() + btn.getHeight() / 2;
            g2d.drawLine(startX, y, endX, y);
            if (finishLineIcon != null) {
                g2d.drawImage(finishLineIcon.getImage(), endX - finishLineIcon.getIconWidth(), y - (finishLineIcon.getIconHeight() / 2), null);
            }
        }
        if (finishLineIcon != null) {
            g2d.drawImage(finishLineIcon.getImage(), endX - finishLineIcon.getIconWidth(), (int)buttons.get(buttons.size()/2).getAlignmentY() - (finishLineIcon.getIconHeight() / 2), null);
        }
    }
	public List<JLabel> getLabels() {
		return labels;
	}
	public void setLabels(List<JLabel> labels) {
		this.labels = labels;
	}


    
    
}