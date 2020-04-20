package cosc310.group8.chatbot;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Alec Nixon
 */
class GUILauncher{

    /**
     * 
     * @param RMStage the stage with which to show the Response Manager
     * @throws Exception 
     */
    public GUILauncher(Stage RMStage, DataBase db) throws Exception{
        FXMLLoader GUILoader = new FXMLLoader(getClass().getResource("chatbot.fxml"));
        RMStage.setTitle("COSC 310 Chatbot");
        RMStage.setScene(new Scene(GUILoader.load()));
        RMStage.show();
        
        //ResponseManager RM = (ResponseManager)GUILoader.getController();
    }
    
}
