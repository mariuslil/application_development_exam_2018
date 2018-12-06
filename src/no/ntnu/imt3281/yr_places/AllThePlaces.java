package no.ntnu.imt3281.yr_places;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class AllThePlaces {

    public static ArrayList<ArrayList<String>> getAllPlaces() throws IOException {
        ArrayList<ArrayList<String>> places = new ArrayList<ArrayList<String>>();
        ArrayList<String> place = new ArrayList<>();

        URL url = new URL("http://fil.nrk.no/yr/viktigestader/noreg.txt");
        Scanner s = new Scanner(url.openStream());

        int i = 0;
        while (s.hasNextLine()) {
            place.add(i, s.nextLine());
            places.add(i, place);
            i++;
        }
        return places;
    }
}
