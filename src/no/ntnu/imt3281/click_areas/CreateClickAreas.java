package no.ntnu.imt3281.click_areas;

import javafx.fxml.FXML;

import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Polygon;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.plaf.FileChooserUI;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

    /**
     * adds the info about the shape into the textArea in JSON format.
     * @param actionEvent
     */
    public void addArea(javafx.event.ActionEvent actionEvent) {
        String text ="";
        if (areas.getLength() > 2) { //If not first
            text = ", ";
        }
        text = text + "{\n\t\"name\": \""+areaName.getText()+"\",\n" +
                "\t\"polygon\": [\n";
        for(int i = 0; i < shape.getPoints().size();i+=2) { //Gets all the points from the drawn shape
            text = text + "\t\t{\"x\": "+shape.getPoints().get(i)+", " +
                          "\"y\": "+shape.getPoints().get(i+1)+"},\n";
        }
        text = text + "\t]\n}";

        //Insert between the [] brackets
        areas.insertText(areas.getLength()-1,text);
        //Clear the board
        shape.getPoints().clear();
        points.getItems().clear();
    }

    /**
     *
     * @param actionEvent
     */
    public void toFile(javafx.event.ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json", "*.json");
        chooser.getExtensionFilters().add(extFilter);

        File file = chooser.showSaveDialog(primaryStage);

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(areas.getText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Finds the position of the mousepointer and starts drawing a shape from it.
     * @param mouseEvent
     */
    public void findPos(javafx.scene.input.MouseEvent mouseEvent) {
        points.getItems().add(mouseEvent.getX()+" : "+mouseEvent.getY());

        shape.getPoints().addAll(mouseEvent.getX(), mouseEvent.getY());


    }
}