package view;
import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    private JTextField descriptionField;
    private JTextField measurementUnitField;
    private JTextField upperTierField;
    private JTextField lowerTierField;
    private JTextField swimmingImpactField;
    private JTextField cyclingImpactField;
    private JTextField pedestrianismImpactField;

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public void setDescriptionField(JTextField descriptionField) {
        this.descriptionField = descriptionField;
    }

    public JTextField getMeasurementUnitField() {
        return measurementUnitField;
    }

    public void setMeasurementUnitField(JTextField measurementUnitField) {
        this.measurementUnitField = measurementUnitField;
    }

    public JTextField getUpperTierField() {
        return upperTierField;
    }

    public void setUpperTierField(JTextField upperTierField) {
        this.upperTierField = upperTierField;
    }

    public JTextField getLowerTierField() {
        return lowerTierField;
    }

    public void setLowerTierField(JTextField lowerTierField) {
        this.lowerTierField = lowerTierField;
    }

    public JTextField getSwimmingImpactField() {
        return swimmingImpactField;
    }

    public void setSwimmingImpactField(JTextField swimmingImpactField) {
        this.swimmingImpactField = swimmingImpactField;
    }

    public JTextField getCyclingImpactField() {
        return cyclingImpactField;
    }

    public void setCyclingImpactField(JTextField cyclingImpactField) {
        this.cyclingImpactField = cyclingImpactField;
    }

    public JTextField getPedestrianismImpactField() {
        return pedestrianismImpactField;
    }

    public void setPedestrianismImpactField(JTextField pedestrianismImpactField) {
        this.pedestrianismImpactField = pedestrianismImpactField;
    }

    public InputPanel() {
        setOpaque(false);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Description
        add(new JLabel("Description:"));
        descriptionField = new JTextField();
        add(descriptionField);

        // Measurement Unit
        add(new JLabel("Measurement Unit:"));
        measurementUnitField = new JTextField();
        add(measurementUnitField);

        // Lower Tier (Min Value)
        add(new JLabel("Lower Tier (Min Value):"));
        lowerTierField = new JTextField();
        add(lowerTierField);

        // Upper Tier (Max Value)
        add(new JLabel("Upper Tier (Max Value):"));
        upperTierField = new JTextField();
        add(upperTierField);

        // Swimming Weathering
        add(new JLabel("Swimming Impact:"));
        swimmingImpactField = new JTextField();
        add(swimmingImpactField);

        // Cycling Weathering
        add(new JLabel("Cycling Impact:"));
        cyclingImpactField = new JTextField();
        add(cyclingImpactField);

        // Pedestrianism Weathering
        add(new JLabel("Pedestrianism Impact:"));
        pedestrianismImpactField = new JTextField();
        add(pedestrianismImpactField);
    }

    public String getDescription() {
        return descriptionField.getText();
    }

    public String getMeasurementUnit() {
        return measurementUnitField.getText();
    }

    public double getUpperTier() {
        return Double.parseDouble(upperTierField.getText());
    }

    public double getLowerTier() {
        return Double.parseDouble(lowerTierField.getText());
    }

    public double getSwimmingImpact() {
        return Double.parseDouble(swimmingImpactField.getText());
    }

    public double getCyclingImpact() {
        return Double.parseDouble(cyclingImpactField.getText());
    }

    public double getPedestrianismImpact() {
        return Double.parseDouble(pedestrianismImpactField.getText());
    }
    public void setDescription(String description) {
        descriptionField.setText(description);
    }

    public void setMeasurementUnit(String measurementUnit) {
        measurementUnitField.setText(measurementUnit);
    }

    public void setUpperTier(String upperTier) {
        upperTierField.setText(upperTier);
    }

    public void setLowerTier(String lowerTier) {
        lowerTierField.setText(lowerTier);
    }

    public void setSwimmingImpact(String swimmingWeathering) {
        swimmingImpactField.setText(swimmingWeathering);
    }

    public void setCyclingImpact(String cyclingWeathering) {
        cyclingImpactField.setText(cyclingWeathering);
    }

    public void setPedestrianismImpact(String pedestrianismWeathering) {
        pedestrianismImpactField.setText(pedestrianismWeathering);
    }
}