package cosc310.group8.chatbot;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Alec Nixon
 */
class RMLauncher{

    /**
     * 
     * @param RMStage the stage with which to show the Response Manager
     * @throws Exception 
     */
    public RMLauncher(Stage RMStage) throws Exception{
        FXMLLoader RMLoader = new FXMLLoader(getClass().getResource("ResponseManager.fxml"));
        RMStage.setTitle("Editor");
        RMStage.setScene(new Scene(RMLoader.load()));
        RMStage.show();
        //ResponseManager RM = (ResponseManager)RMLoader.getController();
    }
    
}
