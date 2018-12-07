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

    public void initialize() {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("former.json"));

            List points = new ArrayList<String>();

            JSONArray jsonArray = (JSONArray) obj;
            //points.add(jsonArray);
            //System.out.println(points.get(1));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Shapes", jsonArray);
            points.add(jsonObject);

            //System.out.println(jsonArray.get(1));
            System.out.println(jsonObject);


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
