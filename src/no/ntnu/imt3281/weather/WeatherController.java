package no.ntnu.imt3281.weather;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import no.ntnu.imt3281.yr_places.DataStore;
import no.ntnu.imt3281.yr_places.Place;

import java.sql.SQLException;
import java.util.ArrayList;

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

        WebEngine engine = forecast.getEngine();

        System.out.println("StedsNavn: " + p.getStedsnavn() + " Kommune: " + p.getKommune());

        //Gets the list with all the forecasts
        ArrayList<String> weather = Forecast.getForecast(p.getVarselURL());

        //Loads the page with all the forecast information
        engine.loadContent("<h1>"+p.getStedsnavn()+" i "+p.getKommune()+"</h1>");
        for (int i = 0; i < weather.size()/2 ; i+=2) {
            engine.loadContent("<div><h3>"+weather.get(i)+"</h3><br><p>"+weather.get(i+1)+"</p></div>");
        }


    }
}
