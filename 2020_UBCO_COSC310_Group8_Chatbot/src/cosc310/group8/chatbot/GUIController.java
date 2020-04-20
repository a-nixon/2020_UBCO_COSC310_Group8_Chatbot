/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc310.group8.chatbot;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author A
 */
public class GUIController implements Initializable, Environment{
    
    private DataBase db;
    private StringParse parser;
    public final StringProperty consoleInput = new SimpleStringProperty();
    private boolean networked = false;

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
        consoleInput.setValue(inText);
        if (inText.startsWith("/")) {
            output.appendText(parser.parse(inText, this)+"\n");
        } else if(!networked){
            output.appendText("User: " + inText + "\n");
            output.appendText(parser.parse(inText, this)+"\n"); 
        }
    }
    /**
     * Sets the database
     * @param db 
     */
    @Override
    public void setDB(DataBase db) {
        this.db = db;
    }
    /**
     * returns the database being used
     * @return 
     */
    @Override
    public DataBase getDB(){
        return db;
    }

    /**
     * The same as hostSocket(int port, int maxLines) but with a default max of 100
     * @param port 
     */
    @Override
    public void hostSocket(int port) {
        hostSocket(port, 100);
    }
    
    /**
     * Starts a sockets server
     * @param port the port number to listen on
     * @param maxLines the maximum number of responses to give to client before
     * closing the connection, maxLines is meant as a preventative measure
     * against infinite loops
     */
    @Override
    public void hostSocket(int port, int maxLines) {
        Server server = new Server(this, port, maxLines, parser);
        server.messageProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            output.appendText(newValue);
        });
        new Thread(server).start();
        networked = true;
    }

    @Override
    public void connectSocket(String address, int port){
        connectSocket(address, port, 100);
    }
    
    /**
     * Try to connect to a sockets  server
     * @param address the address of the server
     * @param port the port to connect over
     * @param maxLines the maximum number of interactions with the server
     */
    @Override
    public void connectSocket(String address, int port, int maxLines) {
        Client client = new Client(this, port, address, maxLines, parser);
        client.messageProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            output.appendText(newValue);
        });
        new Thread(client).start();
        networked = true;
    }
    public void connectTerminal(String address, int port){
        connectTerminal(address, port, 100);
    }
    public void connectTerminal(String address, int port, int maxLines){
        ClientTerminal client = new ClientTerminal(this, port, address, maxLines);
        client.messageProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            output.appendText(newValue);
        });
        new Thread(client).start();
        networked = true;
    }
    public void setNetworked(boolean b){
        networked = b;
    }
    
}
