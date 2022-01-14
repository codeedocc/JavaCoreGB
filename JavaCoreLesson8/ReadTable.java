package GeekBrains.JavaCore.JavaCoreLesson8;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.Scanner;

public class ReadTable {
    private static final String PATH_TO_DB = "jdbc:sqlite:src/main/resources/WeatherTable.db";
    static Connection connection;

    public static void readTable() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        connection = DriverManager.getConnection(PATH_TO_DB);

        Scanner sc = new Scanner(System.in);
        System.out.println("Для просмотра данных бд введите id:");

        int id = sc.nextInt();

        try {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM weathertable WHERE id = " + id);

            while (resultSet.next()) {
                String cityName = resultSet.getString("city");
                String date = resultSet.getString("localDate");
                String weatherText = resultSet.getString("weatherText");
                int tempC = resultSet.getInt("temperature");

                System.out.printf("%d. %s: %s. %s. %s°C\n", id, cityName, date, weatherText, tempC);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch (Exception e) { /* ignored */ }
        }
    }

}