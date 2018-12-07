package no.ntnu.imt3281.click_areas;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickAreas {
    List names = new ArrayList<String>();

    public void initialize() {
        
    }

    @FXML
    private Label areaToClick;

    @FXML
    private TextArea results;

    @FXML
    void clicked(MouseEvent event) {

    }

    public void clicked(javafx.scene.input.MouseEvent mouseEvent) {
        Point click = new Point(mouseEvent.getX(), mouseEvent.getY());
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("former.json"));

            JSONObject jsonObject;
            JSONArray jsonArray = (JSONArray) obj;


            for(int i = 0; i < jsonArray.size();i++) {
                jsonObject = (JSONObject) jsonArray.get(i);

                names.add(jsonObject.get("name"));

                JSONArray poly = (JSONArray) jsonObject.get("polygon");

                Point[] xy = new Point[poly.size()];

                for (int j = 0; j < poly.size();j++) {
                    JSONObject point = (JSONObject) poly.get(j);
                    xy[j] = new Point(Double.parseDouble(point.get("x").toString()), Double.parseDouble(point.get("y").toString()));
                }

                if(new Polygon(xy).contains(click)) {
                    System.out.println("DET VAR RIKTIG");
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }
}
