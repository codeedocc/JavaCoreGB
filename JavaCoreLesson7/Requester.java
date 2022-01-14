package GeekBrains.JavaCore.JavaCoreLesson7;

import com.fasterxml.jackson.databind.*;
import okhttp3.*;

import java.io.IOException;

public class Requester {
    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String API_KEY = "AuCSfPEa0Lv2dnGUGSBCAEDHeBJWkRxT";

    static public String sendCityIdRequest(String cityName) throws IOException {
        String cityId;
        String weather;

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

        System.out.println("Город: " + cityName);
        System.out.println("Код города: " + cityId);

        return "В городе " + cityName + " на дату " + date + " днем будет " + weatherText + ", температура - " + tempC + "C°";

    }
    static public String sendTempRequest(String cityID) { return null; }
}