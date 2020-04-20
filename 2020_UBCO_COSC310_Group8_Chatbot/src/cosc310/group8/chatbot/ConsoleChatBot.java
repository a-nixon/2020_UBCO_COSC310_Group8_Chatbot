package cosc310.group8.chatbot;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
public class ConsoleChatBot implements Runnable, Environment{
    private String role;
    private String userName;
    private String question;
    
    private DataBase db;
    private StringParse parser;
    
    public ConsoleChatBot(){
        //this.role = "Call Center Agent";
        
            
    }

    public String getRole(){
        return this.role;
    }
    public void setRole(String s){
        this.role = s;
    }

    
    //Renamed from init to implement multi-threading.
    @Override
    public void run(){
        db = new DataBase();
        parser = new StringParse();
        Scanner input = new Scanner(System.in);
        System.out.println("Chatbot: Hello!");
        while (true) {//Exit is handled in StringParser
            System.out.print("User$ ");
            this.question = input.nextLine();
            System.out.println(parser.parse(question, this));
        }
    }

    @Override
    public void setDB(DataBase db) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public DataBase getDB (){
        return db;
    }

    @Override
    public void startSockets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}