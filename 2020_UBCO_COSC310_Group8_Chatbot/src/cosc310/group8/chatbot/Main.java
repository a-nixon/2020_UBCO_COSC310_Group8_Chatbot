package cosc310.group8.chatbot;


import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    
    //Command line flags:
    private static boolean ShouldLaunchResponseManager = false;
    
    
    public static void main(String[] args){
        /**
         * Command line argument parsing.
         */
        for(int i = 0; i < args.length; i++){
            switch(args[i]){
                case "-manager": //Flag to launch the Response Manager.
                    ShouldLaunchResponseManager = true;
                    break;
                default:
                    break;
            }
        }
        launch(args);
    }

    /**
     * A requirement of JavaFX.
     * @param primaryStage a window to contain the GUI.
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        if (ShouldLaunchResponseManager) {
            RMLauncher RML = new RMLauncher(primaryStage);
        }
        Thread bot = new Thread(new ChatBot());
        bot.start();
        
    }
}