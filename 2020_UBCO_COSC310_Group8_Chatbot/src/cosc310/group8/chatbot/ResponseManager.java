package cosc310.group8.chatbot;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Alec Nixon
 */
public class ResponseManager implements Initializable {
    
    //Stores the currently selected topic, type, and keyword.
    private String cTopic = "", cType = "", cKeyword = "";
    
    //Database stucture variables
    ArrayList<ArrayList<ArrayList<ArrayList<Response>>>> responses = new ArrayList<>();
    private ArrayList<String> topics = new ArrayList<>();
    private ArrayList<String> types = new ArrayList<>();
    private ArrayList<String> keywords = new ArrayList<>();
    public static final int TOPICS_ID = 0, TYPES_ID = 1, KEYWORDS_ID = 2;
    public final ArrayList<ArrayList<String>> LISTS = new ArrayList<>();
    
  
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
        removeCatPane("Topic", topicsTG, topics, topicBox);
    }
    @FXML
    private void addType(ActionEvent event) {
        addCatPane("Type/Action", typesTG, types, typeBox);
    }
    @FXML
    private void removeType(ActionEvent event) {
        removeCatPane("Type/Action", typesTG, types, typeBox);
    }
    @FXML
    private void addKeyword(ActionEvent event) {
        addCatPane("Keyword", keywordsTG, keywords, keywordBox);
    }
    @FXML
    private void removeKeyword(ActionEvent event) {
        removeCatPane("Keyword", keywordsTG, keywords, keywordBox);
    }
    @FXML
    private void addResponse(ActionEvent event) {
        if ((!cTopic.equals("")&&cTopic!=null)&&(!cType.equals("")&&cType!=null)
                &&(!cKeyword.equals("")&&cKeyword!=null)) {
            addResponsePane();
        }
    }
    @FXML
    private void onLoad(ActionEvent event){
        
        Object[] ser = DBLoader.load(filepath);
        
        //Construct the category ArrayLists from the db header array
        String[][] header = (String[][]) ser[0];
//        topics = new ArrayList(Arrays.asList(header[0]));
//        types = new ArrayList(Arrays.asList(header[1]));
//        keywords = new ArrayList(Arrays.asList(header[2]));
        for (String item : header[0]) {
            addCat(topics, item, topicsTG, topicBox);
        }
        for (String item : header[1]) {
            addCat(types, item, typesTG, typeBox);
        }
        for (String item : header[2]) {
            addCat(keywords, item, keywordsTG, keywordBox);
        }
        
        //onPrintCats(null);
        
        
        //Construct the responses nested ArrayList from the db array
        String[][][][] db = (String[][][][]) ser[1];
        responses = new ArrayList<>(db.length);
        for(int i1 = 0; i1 < db.length; i1++){
            responses.add(i1, new ArrayList<>(db[i1].length));
            for(int i2 = 0; i2 < db.length; i2++){
                responses.get(i1).add(i2, new ArrayList<>(db[i1][i2].length));
                for(int i3 = 0; i3 < db.length; i3++){
                    responses.get(i1).get(i2).add(i3, new ArrayList<>(db[i1][i2][i3].length));
                    for(int i4 = 0; i4 < db[i1][i2][i3].length; i4++){
                        responses.get(i1).get(i2).get(i3).add(i4,
                                new Response(responseBox, this, responses,
                                        topics.get(i1), types.get(i2),keywords.get(i3),db[i1][i2][i3][i4])
                                        )
                                        ;
                    }
                }
            }
        }
    }
    @FXML
    private void onSave(ActionEvent event){
        //Construct the db array
        String[][][][] db;
        db = new String[responses.size()][][][];
        for(int i1 = 0; i1 < responses.size(); i1++){
            db[i1] = new String[responses.get(i1).size()][][];
            for(int i2 = 0; i2 < responses.get(i1).size(); i2++){
                db[i1][i2] = new String[responses.get(i1).get(i2).size()][];
                for(int i3 = 0; i3 < responses.get(i1).get(i2).size(); i3++){
                    db[i1][i2][i3] = new String[responses.get(i1).get(i2).get(i3).size()];
                    for(int i4 = 0; i4 < responses.get(i1).get(i2).get(i3).size(); i4++){
                        db[i1][i2][i3][i4] = responses.get(i1).get(i2).get(i3).get(i4).getText();
                    }
                }
            }
        }
        //Construct the db header array
        String[][] dbHeader = new String[3][];
        dbHeader[0] = new String[topics.size()];
        for(int i = 0; i < topics.size(); i++){
            dbHeader[0][i] = topics.get(i);
        }
        dbHeader[1] = new String[types.size()];
        for(int i = 0; i < types.size(); i++){
            dbHeader[1][i] = types.get(i);
        }
        dbHeader[2] = new String[keywords.size()];
        for(int i = 0; i < keywords.size(); i++){
            dbHeader[2][i] = keywords.get(i);
        }
        
        
        //Serialization code based off of https://www.tutorialspoint.com/java/java_serialization.htm
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filepath))){
            Object[] ser = new Object[2];
            ser[0] = dbHeader;
            ser[1] = db;
            out.writeObject(ser);
        }catch(IOException e){
            System.err.println("Error saving database. . .");
        }
    }
    @FXML
    private void onSaveAs(ActionEvent event){
        //TODO
        System.err.println("Save As TODO");
    }
    @FXML
    private void onPrintCats(ActionEvent event){
        System.out.println("Currently selected topic: "+cTopic);
        System.out.println("Currently selected type: "+cType);
        System.out.println("Currently selected keyword: "+cKeyword);
    }
    @FXML
    private void onPrintDB(ActionEvent event){
        for(int i1 = 0; i1 < responses.size(); i1++){
            for(int i2 = 0; i2 < responses.get(i1).size(); i2++){
                for(int i3 = 0; i3 < responses.get(i1).get(i2).size(); i3++){
                    for(int i4 = 0; i4 < responses.get(i1).get(i2).get(i3).size(); i4++){
                        System.out.println(responses.get(i1).get(i2).get(i3).get(i4));
                    }
                }
            }
        }
    }
    @FXML
    private void onPrintToggles(ActionEvent event){
        System.out.println("Topics:");
        for(int i = 0; i < topicsTG.getToggles().size(); i++){
            System.out.println("\""
                    +(String) topicsTG.getToggles().get(i).getUserData()+"\"");
        }
        System.out.println("Types:");
        for(int i = 0; i < typesTG.getToggles().size(); i++){
            System.out.println("\""
                    +(String) typesTG.getToggles().get(i).getUserData()+"\"");
        }
        System.out.println("Keywords:");
        for(int i = 0; i < keywordsTG.getToggles().size(); i++){
            System.out.println("\""
                    +(String) keywordsTG.getToggles().get(i).getUserData()+"\"");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(int i = 0; i < controlBox.getChildren().size(); i++){
            for(int j = 0; j < ((VBox)controlBox.getChildren().get(i)).getChildren().size(); j++){
                ((Button)((VBox)controlBox.getChildren().get(i)).getChildren().get(j)).setMaxWidth(Double.MAX_VALUE);
            }
        }
        topicsTG.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            cTopic = (String) topicsTG.getSelectedToggle().getUserData();
        });
        typesTG.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            cType = (String) typesTG.getSelectedToggle().getUserData();
        });
        keywordsTG.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            cKeyword = (String) keywordsTG.getSelectedToggle().getUserData();
        });
        LISTS.add(topics);
        LISTS.add(types);
        LISTS.add(keywords);
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
     * @param cValue the String holding the current value for the appropriate
     * category.
     */
    private void addCatPane(String cat, ToggleGroup tg, ArrayList<String> list,
            VBox parent){
        Pane pane = new Pane();
        VBox vbox = new VBox();
        TextField tf = new TextField();
        Button b = new Button("Add");
        pane.getChildren().add(vbox);
        vbox.getChildren().add(new Label("Add new "+cat));
        vbox.getChildren().add(tf);
        vbox.getChildren().add(b);
        b.setOnAction((EventHandler) clickEvent -> {
            addCat(list, tf.getText(), tg, parent);
            tf.setText("");
        });
        center.getChildren().clear();
        center.getChildren().add(pane);
        tf.requestFocus();
    }

    private void addCat(ArrayList<String> list, String s, ToggleGroup tg, VBox parent) {
        RadioButton rb = new RadioButton(s);
        tg.getToggles().add(rb);
        rb.setUserData(s);
        rb.selectedProperty().set(true);
        parent.getChildren().add(rb);
        list.add(s);
        if(list == topics){
            responses.add(new ArrayList<>());
            for(int i1 = 0; i1 < types.size(); i1++){
                responses.get(responses.size()-1).add(new ArrayList<>());
                for(int i2 = 0; i2 < keywords.size(); i2++){
                    responses.get(responses.size()-1).get(i1).add(new ArrayList<>());
                }
            }
        }else if(list == types){
            for(int i1 = 0; i1 < topics.size(); i1++){
                responses.get(i1).add(new ArrayList<>());
                for(int i2 = 0; i2 < keywords.size(); i2++){
                    responses.get(i1).get(types.size()-1).add(new ArrayList<>());
                    
                }
            }
        }else if(list == keywords){
            for(int i1 = 0; i1 < topics.size(); i1++){
                for(int i2 = 0; i2 < types.size(); i2++){
                    responses.get(i1).get(i2).add(new ArrayList<>());
                }
            }
        }else{
            System.err.println("This is a debug message. addCatPane() "
                    + "should not be called with a list ofther than "
                    + "topics, types, or keywords.");
        }
    }
    private void removeCatPane(String cat, ToggleGroup tg, ArrayList<String> list,
            VBox parent){
        Pane pane = new Pane();
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        ObservableList<String> options = FXCollections.observableArrayList();
        list.stream().forEach((c) -> {
            options.add(c);
        });
        ComboBox cb = new ComboBox(options);
        Button b = new Button("Remove");
        Label l = new Label("WARNING: Removing "+cat
                +" will remove all responses under it!");
        l.setTextFill(Color.RED);
        l.maxWidthProperty().bind(pane.widthProperty());
        pane.getChildren().add(vbox);
        pane.prefWidthProperty().bind(center.widthProperty());
        pane.maxWidthProperty().bind(center.widthProperty());
        vbox.getChildren().add(new Label("Add new "+cat));
        hbox.getChildren().add(cb);
        hbox.getChildren().add(b);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(l);
        b.setOnAction((EventHandler) clickEvent -> {
            if (tg.getToggles().size() > 1) {
                int index = string2index(list, (String) cb.getValue());
                list.remove(index);
                Toggle t = null;
                for(Toggle m : tg.getToggles()){
                    if(m.getUserData().equals((String) cb.getValue())){
                        t = m;
                        if (tg.getToggles().get(0) != m) {
                            tg.getToggles().get(0).setSelected(true);
                        }else{
                            tg.getToggles().get(1).setSelected(true);
                        }
                        break;
                    }
                }
                tg.getToggles().remove(t);
                parent.getChildren().remove((RadioButton) t);
                removeCatPane(cat, tg, list, parent);
            }
        });
        center.getChildren().clear();
        center.getChildren().add(pane);
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
            Response r = new Response(responseBox, this, responses, cTopic, cType, cKeyword, tf.getText());
            responseBox.getChildren().add(r);
            tf.setText("");
        }); 
        center.getChildren().clear();
        center.getChildren().add(pane);
        tf.requestFocus();
    }
    /**
     * 
     * @param list the list the index is being found for.
     * @param entry the the String being searched for
     * (does not need to be the same object).
     * @return 
     */
    public static int string2index(ArrayList<String> list, String entry){
        for(int i = 0; i < list.size(); i++){
            if (entry.equals(list.get(i))){
                return i;
            }
        }
        return -1;
    }
    public int string2index(int listID, String entry){
        return string2index(LISTS.get(listID), entry);
    }
    
}
