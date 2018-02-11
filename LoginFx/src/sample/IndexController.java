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
import java.util.InputMismatchException;

public class IndexController {
    @FXML TextField caloriesText, fatText, satFatText, carbsText, sugarText, fibreText, proteinText, saltText;

    public void submitData(ActionEvent event) throws IOException {
        /*Need some kind of input validation here (try catch when parsing non-integers?) and a way to ensure all boxes
        Are filled before submitting (May need exception handling here in case a box is not filled instead*/
        //Array will always be structured in the following order: [calories][fat][satfat][carbs][sugars][fibre][protein][salt][others]
        ArrayList<Integer> breakdown = new ArrayList<>();
        //More efficient way of adding all the text boxes here, maybe need to rethink the data structure used
        //TODO try to find a better way of validating input here
        try {
            breakdown.add(Integer.parseInt(caloriesText.getText()));
            breakdown.add(Integer.parseInt(fatText.getText()));
            breakdown.add(Integer.parseInt(satFatText.getText())); //NEEDS TO BE REARRANGED
            breakdown.add(Integer.parseInt(carbsText.getText()));
            breakdown.add(Integer.parseInt(sugarText.getText()));
            breakdown.add(Integer.parseInt(fibreText.getText()));
            breakdown.add(Integer.parseInt(proteinText.getText()));
            breakdown.add(Integer.parseInt(saltText.getText()));
        } catch (InputMismatchException e ) {
            System.out.println("Error: Input mismatch!");
            System.out.println(e.getMessage());
        } catch (NumberFormatException g) {
            System.out.println("Error: Number format expection!");
            System.out.println(g.getMessage());
        }
        System.out.println("ArrayList contains:" + breakdown.toString());
        System.out.println("Button pressed!");
        doSomeStuff(breakdown);
    }

    private static void doSomeStuff(ArrayList<Integer> input) {
        for (int i = 0; i < input.size(); i++) {
            System.out.println(input.get(i));
        }
    }
}
