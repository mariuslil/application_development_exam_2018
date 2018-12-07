package no.ntnu.imt3281.click_areas;

import javafx.fxml.FXML;

import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Polygon;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import static javafx.scene.paint.Color.*;

public class CreateClickAreas {
    private Polygon shape = new Polygon();

    @FXML
    public void initialize(){
        areas.setText("[]");
        shape.setFill(null);
        shape.setStroke(BLACK);
        imageContainer.getChildren().add(shape);
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
        shape.getPoints().size();
        areas.insertText(areas.getLength()-1,"{\n\"name\": \""+areaName.getText()+"\",\n" +
                "\"polygon\": [\n" +
                "" +
                "]\n" +
                "},");
    }

    public void toFile(javafx.event.ActionEvent actionEvent) {

    }

    public void findPos(javafx.scene.input.MouseEvent mouseEvent) {
        points.getItems().add(mouseEvent.getSceneX()+" : "+mouseEvent.getSceneY());

        shape.getPoints().addAll(mouseEvent.getX(), mouseEvent.getY());


    }
}