package no.ntnu.imt3281.weather;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ManDown extends javafx.scene.control.Label {

    public ManDown(){
        URL url;
        Scanner s;
        try {
            url = new URL("http://fil.nrk.no/yr/viktigestader/noreg.txt");
            s = new Scanner(url.openStream());
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setText("");

    }


}
