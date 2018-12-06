package no.ntnu.imt3281.yr_places;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class AllThePlaces {

    /**
     * Gets all the locations from the URL and returns them as an list of lists of strings, that contains the info for each place.
     * @return
     * @throws IOException
     */
    public static ArrayList<ArrayList<String>> getAllPlaces() throws IOException {
        ArrayList<ArrayList<String>> places = new ArrayList<ArrayList<String>>();

        URL url = new URL("http://fil.nrk.no/yr/viktigestader/noreg.txt");
        Scanner s = new Scanner(url.openStream());

        String[] info;
        s.nextLine(); //Throws away first line
        while (s.hasNextLine()) { //Checks that notEOF
            ArrayList<String> place = new ArrayList<>();
            info = s.nextLine().split("\t");
            for (String i : info) { //For each piece of info
                place.add(i);
            }
            places.add(place);
        }
        return places;
    }
}
