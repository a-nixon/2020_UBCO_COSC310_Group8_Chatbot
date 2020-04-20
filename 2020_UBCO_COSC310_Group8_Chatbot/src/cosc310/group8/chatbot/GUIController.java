/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc310.group8.chatbot;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author A
 */
public class GUIController implements Initializable, Environment{
    
    private DataBase db;
    private StringParse parser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DataBase();
        parser = new StringParse();
        output.appendText("Chatbot: Hello!\n");
    }    
    @FXML
    public TextField input;
    @FXML
    public TextArea output;
    @FXML
    public void onInput(ActionEvent event){
        String inText = input.getText();
        input.setText("");
        if (inText.startsWith("/")) {
            output.appendText(parser.parse(inText, this)+"\n");
        } else {
            output.appendText("User: " + inText + "\n");
            output.appendText(parser.parse(inText, this)+"\n");
        }
    }

    @Override
    public void setDB(DataBase db) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public DataBase getDB(){
        return db;
    }

    @Override
    public void startSockets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
