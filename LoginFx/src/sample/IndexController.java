package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class IndexController {
    @FXML TextField caloriesText, satFatText, transFatText, cholesterolText, carbsText, proteinText;

    public void submitData(ActionEvent event) throws IOException {
        /*Need some kind of input validation here (try catch when parsing non-integers?) and a way to ensure all boxes
        Are filled before submitting (May need exception handling here in case a box is not filled instead*/
        ArrayList<Integer> breakdown = new ArrayList<>();
        //More efficient way of adding all the text boxes here, maybe need to rethink the data structure used
        breakdown.add(Integer.parseInt(caloriesText.getText()));
        breakdown.add(Integer.parseInt(satFatText.getText()));
        breakdown.add(Integer.parseInt(transFatText.getText()));
        breakdown.add(Integer.parseInt(cholesterolText.getText()));
        breakdown.add(Integer.parseInt(carbsText.getText()));
        breakdown.add(Integer.parseInt(proteinText.getText()));
        System.out.println("ArrayList contains:" + breakdown.toString());
        System.out.println("Button pressed!");
    }
}
