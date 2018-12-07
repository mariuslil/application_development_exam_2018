package no.ntnu.imt3281.click_areas;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClickAreas {
    List shapes = new ArrayList<Polygon>();
    List names = new ArrayList<String>();

    public void initialize() {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("former.json"));
            List points = new ArrayList<ArrayList<Double>>();

            JSONObject jsonObject;
            JSONArray jsonArray = (JSONArray) obj;


            for(int i = 0; i < jsonArray.size();i++) {
                List coor = new ArrayList<Double>();
                jsonObject = (JSONObject) jsonArray.get(i);
                names.add(jsonObject.get("name"));
                JSONArray poly = (JSONArray) jsonObject.get("polygon");
                for (int j = 0; j < poly.size();j++) {
                    JSONObject point = (JSONObject) poly.get(j);
                    coor.add(point.get("x"));
                    coor.add(point.get("y"));
                }
                points.add(coor);
            }

            Double[] xy = null;
            for(int i = 0; i < points.size();i++) {
                xy[i] = Double.parseDouble(points.get(i).toString());
                System.out.println(xy);
            }


        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Label areaToClick;

    @FXML
    private TextArea results;

    @FXML
    void clicked(MouseEvent event) {

    }

    public void clicked(javafx.scene.input.MouseEvent mouseEvent) {

    }
}
