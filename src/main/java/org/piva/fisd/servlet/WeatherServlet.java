package org.piva.fisd.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.piva.fisd.json.entity.WeatherTemp;
import org.piva.fisd.database.httpclient.HttpClient;
import org.piva.fisd.database.httpclient.HttpClientImpl;
import org.piva.fisd.util.PropertiesReader;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "WeatherServlet", urlPatterns = "/weather")
public class WeatherServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("weather.html");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PropertiesReader propertiesReader = new PropertiesReader();

        String apiKey = propertiesReader.getProperty("api.weather");
        String city = req.getParameter("city");
        String apiUrl = "http://api.openweathermap.org/data/2.5/weather";

        HttpClient httpClient = new HttpClientImpl();
        String json = httpClient.get(apiUrl, Map.of("Content-Type", "application/json"),
                Map.of("q", city, "appid", apiKey));
        ObjectMapper mapper = new ObjectMapper();
        WeatherTemp weatherTemp = mapper.readValue(json, WeatherTemp.class);

        int result = (int) (weatherTemp.getMain().getTemp() - 273.15);
        req.setAttribute("result", result);

        req.getRequestDispatcher("/weather.jsp").forward(req, resp);
    }
}
