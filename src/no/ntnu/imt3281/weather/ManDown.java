package no.ntnu.imt3281.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class ManDown extends javafx.scene.control.Label {

    public ManDown(){
        setText("Har mannen falt: "+ isManDown());
    }

    private String isManDown(){
        URL url;
        Scanner s;
        try {
            url = new URL("https://www.harmannenfalt.no/index.html");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            boolean found = false;
            while (in.readLine() != "") {
                if (found) {
                    return in.readLine();
                }
                if (in.readLine().contains()) {
                    found = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
