package no.ntnu.imt3281.yr_places;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {

    public main() throws IOException {
        Database db = Database.getDB();
        ArrayList<ArrayList<String>> places = new ArrayList<ArrayList<String>>();
        places = AllThePlaces.getAllPlaces();
        for (List i: places
             ) {
            Place p = new Place(i);
            db.addPlace(p);
        }
    }
}
