package no.ntnu.imt3281.click_areas;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

import javax.security.auth.callback.Callback;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class CreateClickAreas {

    @FXML
    public void initialize(){

    }

    @FXML
    private ListView<String> points;

    @FXML
    private TextField areaName;

    @FXML
    private TextArea areas;

    @FXML
    private AnchorPane imageContainer;

    @FXML
    void addArea(ActionEvent event) {

    }

    @FXML
    void findPos(MouseEvent event) {

    }

    @FXML
    void toFile(ActionEvent event) {

    }

    public void addArea(javafx.event.ActionEvent actionEvent) {

    }

    public void toFile(javafx.event.ActionEvent actionEvent) {

    }

    public void findPos(javafx.scene.input.MouseEvent mouseEvent) {
        double x = mouseEvent.getSceneX();
        double y = mouseEvent.getSceneY();
        points.getItems().add(x+" : "+y);
        Polyline line = new Polyline();
        line.getPoints().addAll(x,y);

    }
}