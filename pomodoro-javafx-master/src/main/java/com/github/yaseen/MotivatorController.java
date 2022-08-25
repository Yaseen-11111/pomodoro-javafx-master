package com.github.yaseen;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;

public class MotivatorController {
    @FXML
    private TextFlow textFlow;

    public void loadFile(String filePath) {
        try {
            FileInputStream fis = new FileInputStream("Motivational_Quotations.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                Text quote = new Text(line);
                textFlow.getChildren().add(quote);


            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void loadQuotes(String[] quotes) {

    }
}
