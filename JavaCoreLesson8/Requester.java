package GeekBrains.JavaCore.JavaCoreLesson8;

import com.fasterxml.jackson.databind.*;
import okhttp3.*;
import org.sqlite.JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Requester {
    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String API_KEY = "AuCSfPEa0Lv2dnGUGSBCAEDHeBJWkRxT";

    private static final String PATH_TO_DB = "jdbc:sqlite:src/main/resources/WeatherTable.db";
    static Connection connection;



    public static String sendCityIdRequest(String cityName) throws IOException, SQLException {
        String cityId;

        DriverManager.registerDriver(new JDBC());
        connection = DriverManager.getConnection(PATH_TO_DB);


        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("locations")
                .addPathSegment("v1")
                .addPathSegment("cities")
                .addPathSegment("search")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", cityName)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        String responseJson = response.body().string();

        JsonNode cityIdNode = objectMapper
                .readTree(responseJson)
                .at("/0/Key");

        cityId = cityIdNode.asText();

        HttpUrl httpUrWeather = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment(cityId)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru")
                .build();

        Request requestWeather = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrWeather)
                .build();

        Response responseWeather = okHttpClient.newCall(requestWeather).execute();

        String responseJsonWeather = responseWeather.body().string();

        JsonNode dateNode = objectMapper
                .readTree(responseJsonWeather)
                .at("/DailyForecasts/0/Date");

        JsonNode weatherTextNode = objectMapper
                .readTree(responseJsonWeather)
                .at("/DailyForecasts/0/Day/IconPhrase");

        JsonNode temperatureNode = objectMapper
                .readTree(responseJsonWeather)
                .at("/DailyForecasts/0/Temperature/Maximum/Value");

        String date = dateNode.asText();
        String weatherText = weatherTextNode.asText();
        String temperature = temperatureNode.asText();

        double t = Double.parseDouble(temperature);
        double temp = (t - 32) * 5 / 9;
        int tempC = (int) Math.round(temp);

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO weathertable('city', 'localDate', 'weatherText', 'temperature') VALUES (?, ?, ?, ?)"
        )) {
            preparedStatement.setObject(1, cityName);
            preparedStatement.setObject(2, date);
            preparedStatement.setObject(3, weatherText);
            preparedStatement.setObject(4, tempC);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try { connection.close(); } catch (Exception e) { /* ignored */ }
        }

        return "В городе " + cityName + " на дату " + date + " днем будет " + weatherText + ", температура: " + tempC + "°C";

    }
}
