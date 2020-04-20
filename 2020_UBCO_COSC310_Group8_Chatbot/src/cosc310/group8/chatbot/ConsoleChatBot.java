package cosc310.group8.chatbot;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
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
            System.out.println(newValue);
        });
        new Thread(server).start();
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
            System.out.println(newValue);
        });
        new Thread(client).start();
    }
    public void connectTerminal(String address, int port){
        connectTerminal(address, port, 100);
    }
    public void connectTerminal(String address, int port, int maxLines){
        System.err.println("connectTerminal() should be unreachable from the commandline.");
    }
    
}