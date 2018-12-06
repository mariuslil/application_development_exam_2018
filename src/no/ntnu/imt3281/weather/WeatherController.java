package no.ntnu.imt3281.weather;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import no.ntnu.imt3281.yr_places.DataStore;
import no.ntnu.imt3281.yr_places.Place;

import java.sql.SQLException;

public class WeatherController {

    @FXML
    private WebView map;

    @FXML
    private WebView forecast;

    /**
     * Loads the map in the GUI.
     */
    public WeatherController(){
       WebEngine engine = map.getEngine();
       engine.load("http://folk.ntnu.no/oeivindk/imt3281/map/");
       engine.setOnAlert(Event -> getForecast(Event.getData()));
    }

    /**
     * Finds the closets location to the coordinates and calls the forecast function to get the forecast.
     * @param location
     */
    public void getForecast(String location) {
        Place p = null;
        String[] coor =  location.split("\t");
        double lat = Double.parseDouble(coor[0]);
        double lng = Double.parseDouble(coor[1]);
        try {
            p = DataStore.getDataStore().getClosestPlace(lat,lng);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("StedsNavn: " + p.getStedsnavn() + " Kommune: " + p.getKommune());

        Forecast.getForecast(p.getVarselURL());

    }
}
