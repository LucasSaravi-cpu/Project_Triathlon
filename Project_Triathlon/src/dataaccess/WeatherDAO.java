package dataaccess;

import model.weather.MeasurementUnit;
import model.weather.WeatherConditions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeatherDAO {

    public void insertWeatherCondition(MeasurementUnit measurementunit, double lowertier, double uppertier, double swimming, double cycling, double pedestrianism, String description) throws SQLException {
        String sql = "INSERT INTO weatherconditions (measurementunit, lowertier, uppertier, swimmingweathering, cyclingweathering, pedestrianismweathering, description) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, measurementunit.getUnit());
            pstmt.setDouble(2, lowertier);
            pstmt.setDouble(3, uppertier);
            pstmt.setDouble(4, swimming);
            pstmt.setDouble(5, cycling);
            pstmt.setDouble(6, pedestrianism);
            pstmt.setString(7, description);
            pstmt.executeUpdate();
        }
    }

    public void updateWeatherCondition(int id, MeasurementUnit measuremeantunit, double lowertier, double uppertier , double swimmingweathering, double cyclingweathering, double pedestrianismweathering, String description) throws SQLException {
        String sql = "UPDATE weatherconditions SET measuremeantunit = ?, lowertier = ?, swimmingweathering = ?, cyclingweathering = ? ,pedestrianismweathering = ?, description= ?  WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement sw = conn.prepareStatement(sql)) {

            sw.setString(1, measuremeantunit.getUnit());
            sw.setDouble(2, lowertier);
            sw.setDouble(3, uppertier);
            sw.setDouble(4,swimmingweathering);
            sw.setDouble(5, cyclingweathering);
            sw.setDouble(6, pedestrianismweathering);
            sw.setString(7,description);
            sw.setLong(8, id);
            sw.executeUpdate();
        }
    }


    public void deleteWeatherCondition(long wcId) throws SQLException {
        String sql = "DELETE FROM weatherconditions WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, wcId);
            pstmt.executeUpdate();
        }
    }

    public List<WeatherConditions> getAllWeatherConditions() throws SQLException {
        String query = "SELECT id ,description, measurementunit,lowertier,uppertier,swimmingweathering,cyclingweathering,pedestrianismweathering FROM weatherconditions ";
        List<WeatherConditions> weatherConditions = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet TableWeatherConditions = stmt.executeQuery(query)) {

            while (TableWeatherConditions.next()) {
                MeasurementUnit measurementunit = new MeasurementUnit(TableWeatherConditions.getString("measurementunit"));


                WeatherConditions weatherconditions = new  WeatherConditions(TableWeatherConditions.getInt("id"),TableWeatherConditions.getString("description"),
                        measurementunit,TableWeatherConditions.getDouble("lowertier"),TableWeatherConditions.getDouble("uppertier"),
                        TableWeatherConditions.getDouble("swimmingweathering"),TableWeatherConditions.getDouble("cyclingweathering"),TableWeatherConditions.getDouble("pedestrianismweathering"));

                weatherConditions.add(weatherconditions);

            }
        }
        return weatherConditions;
    }
}
