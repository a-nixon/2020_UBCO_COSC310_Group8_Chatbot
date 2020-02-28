package cosc310.group8.chatbot;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Alec Nixon
 */
public class ResponseManager implements Initializable {
    
    //
    private Pane topicPane;
    private Pane typePane;
    private Pane keywordPane;
    
    
    //Toggle groups for radio buttons
    private final ToggleGroup topicsTG = new ToggleGroup();
    private final ToggleGroup typesTG = new ToggleGroup();
    private final ToggleGroup keywordsTG = new ToggleGroup();
    
    //FXML items
    @FXML
    private VBox topicBox;
    @FXML
    private VBox typeBox;
    @FXML
    private VBox keywordBox;
    @FXML
    private VBox responseBox;
    @FXML
    private Pane center;
    
    @FXML
    private void addTopic(ActionEvent event) {
        addCatPane("Topic", topicsTG, topicBox);
    }
    @FXML
    private void removeTopic(ActionEvent event) {
        
    }
    @FXML
    private void addType(ActionEvent event) {
        addCatPane("Type/Action", typesTG, typeBox);
    }
    @FXML
    private void removeType(ActionEvent event) {
        
    }
    @FXML
    private void addKeyword(ActionEvent event) {
        addCatPane("Keyword", keywordsTG, keywordBox);
    }
    @FXML
    private void removeKeyword(ActionEvent event) {
        
    }
    @FXML
    private void addResponse(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    private void addCatPane(String cat, ToggleGroup tg, VBox parent){
        Pane pane = new Pane();
        VBox vbox = new VBox();
        TextField tf = new TextField();
        Button b = new Button("Add");
        pane.getChildren().add(vbox);
        vbox.getChildren().add(new Label("Add new "+cat));
        vbox.getChildren().add(tf);
        vbox.getChildren().add(b);
        b.setOnAction((EventHandler) e -> {
            //TODO add item to database
            RadioButton rb = new RadioButton(tf.getText());
            tg.getToggles().add(rb);
            parent.getChildren().add(rb);
        });
        center.getChildren().clear();
        center.getChildren().add(pane);
    }
    
}
