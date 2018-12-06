package no.ntnu.imt3281.yr_places;

import javafx.application.Application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String [] args) throws IOException, SQLException {
        DataStore db = DataStore.getDataStore();
        ArrayList<ArrayList<String>> places = new ArrayList<ArrayList<String>>();
        places = AllThePlaces.getAllPlaces();
        for (List i: places
             ) {
            Place p = new Place(i);
            db.addPlace(p);
        }
    }
}
