package cosc310.group8.chatbot;


import java.util.Calendar;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    
    //Command line flags:
    private static boolean ShouldLaunchResponseManager = false;
    private static String filepath = "default.db";
    
    
    public static void main(String[] args){
        /**
         * Command line argument parsing.
         */
        for(int i = 0; i < args.length; i++){
            switch(args[i]){
                case "-manager": //Flag to launch the Response Manager.
                    ShouldLaunchResponseManager = true;
                    break;
                case "-db": //Flag to specify an alternate response database.
                    i++;
                    if(i<args.length){
                        filepath = args[i];
                    }else{
                        System.err.println("Please specify database path after -db flag.");
                    }
                default:
                    break;
            }
        }
        launch(args);
    }
    
    public static String date(){
        return Calendar.getInstance().getTime().toString();
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
    /**
     * Gets the database file path.
     * @return String
     */
    public static String getFilepath(){
        return filepath;
    }
    /**
     * Sets the database file path.
     * @param s the new file path
     */
    public static void setFilepath(String s){
        filepath = s;
    }
}
