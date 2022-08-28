package com.github.yaseen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.util.ArrayList;

public class MotivatorController {
    @FXML
    private TextFlow textFlow;
    @FXMLs
    private Label textLabel;

    private ArrayList<String> quotes;
    private int pos = 0;

    public void loadFile() {
        try {
            FileInputStream fis = new FileInputStream("Motivational_Quotes.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            quotes = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                quotes.add(line);
            }
            fis.close();
            br.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void loadQuotes() {
        if (quotes == null) {
            loadFile();
        }
        if (pos == quotes.size()) {
            pos = 0;
        }

        textLabel.setText(quotes.get(pos));
        pos++;
    }
}
