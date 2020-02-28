package cosc310.group8.chatbot;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Alec Nixon
 */
public class ResponseManager implements Initializable {
    
    //Stores the currently selected topic, type, and keyword.
    private String cTopic, cType, cKeyword;
    
    //
    ArrayList<Response> responses = new ArrayList<>();
    
    //Lists of topics, types, and keywords.
    private final ArrayList<String> topics = new ArrayList<>();
    private final ArrayList<String> types = new ArrayList<>();
    private final ArrayList<String> keywords = new ArrayList<>();
    
  
    private String filepath;
    
    
    //Toggle groups for radio buttons
    private final ToggleGroup topicsTG = new ToggleGroup();
    private final ToggleGroup typesTG = new ToggleGroup();
    private final ToggleGroup keywordsTG = new ToggleGroup();
    
    //FXML items
    @FXML
    private VBox controlBox;
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
        addCatPane("Topic", topicsTG, topics, topicBox);
    }
    @FXML
    private void removeTopic(ActionEvent event) {
        
    }
    @FXML
    private void addType(ActionEvent event) {
        addCatPane("Type/Action", typesTG, types, typeBox);
    }
    @FXML
    private void removeType(ActionEvent event) {
        
    }
    @FXML
    private void addKeyword(ActionEvent event) {
        addCatPane("Keyword", keywordsTG, keywords, keywordBox);
    }
    @FXML
    private void removeKeyword(ActionEvent event) {
        
    }
    @FXML
    private void addResponse(ActionEvent event) {
        addResponsePane();
    }
    @FXML
    private void onLoad(ActionEvent event){
        
    }
    @FXML
    private void onSave(ActionEvent event){
        
    }
    @FXML
    private void onSaveAs(ActionEvent event){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i = 0; i < controlBox.getChildren().size(); i++){
            for(int j = 0; j < ((VBox)controlBox.getChildren().get(i)).getChildren().size(); j++){
                ((Button)((VBox)controlBox.getChildren().get(i)).getChildren().get(j)).setMaxWidth(Double.MAX_VALUE);
            }
        }
        filepath = Main.getFilepath();
    } 
    /**
     * Dialogue for adding topics, types/actions, and keywords. This dialogue
     * gets displayed in the center(ish) of the Response Manager window after
     * clicking on the Add buttons.
     * @param cat put into the dialogue title. Expected to be "Topic"
     * "Type/action" or "Keyword" (will not break if this expectation is not met).
     * @param tg the ToggleGroup a radio button is being added to.
     * @param list one of the category lists.
     * @param parent the layout parent object for this toggle group.
     */
    private void addCatPane(String cat, ToggleGroup tg, ArrayList<String> list, VBox parent){
        Pane pane = new Pane();
        VBox vbox = new VBox();
        TextField tf = new TextField();
        Button b = new Button("Add");
        pane.getChildren().add(vbox);
        vbox.getChildren().add(new Label("Add new "+cat));
        vbox.getChildren().add(tf);
        vbox.getChildren().add(b);
        b.setOnAction((EventHandler) e -> {
            RadioButton rb = new RadioButton(tf.getText());
            tg.getToggles().add(rb);
            parent.getChildren().add(rb);
            list.add(tf.getText());
            tf.setText("");
        });
        center.getChildren().clear();
        center.getChildren().add(pane);
        tf.requestFocus();
    }
    private void addResponsePane(){
        Pane pane = new Pane();
        VBox vbox = new VBox();
        TextField tf = new TextField();
        Button b = new Button("Add");
        pane.getChildren().add(vbox);
        vbox.getChildren().add(new Label("Add new response"));
        vbox.getChildren().add(tf);
        vbox.getChildren().add(b);
        b.setOnAction((EventHandler) e -> {
            Response r = new Response(responseBox, responses, cTopic, cType, cKeyword, tf.getText());
            responseBox.getChildren().add(r);
            tf.setText("");
        }); 
        center.getChildren().clear();
        center.getChildren().add(pane);
        tf.requestFocus();
    }
    /**
     * 
     * @param cat which dimension the index is being fetched  for
     * (topic, type/action, keyword). Returns -1 if no index is found.
     * @param list the list the index is being found for.
     * @param entry the the String being searched for
     * (does not need to be the same object).
     * @return 
     */
    private int string2index(ArrayList<String> list, String entry){
        for(int i = 0; i < list.size(); i++){
            if (entry.equals(list.get(i))){
                return i;
            }
        }
        return -1;
    }
    
}
