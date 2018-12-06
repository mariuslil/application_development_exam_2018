package no.ntnu.imt3281.weather;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WeatherController {

    @FXML
    private WebView map;

    @FXML
    private WebView forecast;

    public WeatherController(){
        /*
        URL url = new URL("http://folk.ntnu.no/oeivindk/imt3281/map/");
        URLConnection conn = url.openConnection();
        InputStream in = conn.getInputStream();
        */

    }

    @FXML
    void getForecast(MouseEvent event) {

    }

    public void getForecast(javafx.scene.input.MouseEvent mouseEvent) {

    }
}
