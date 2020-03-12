package cosc310.group8.chatbot;

import java.util.Scanner;
public class ChatBot implements Runnable{
    private String role;
    private String userName;
    private String question;
    
    private final DataBase db;
    private StringParse parser;
    
    public ChatBot(){
        this.role = "Call Center Agent";
        db = new DataBase();
        parser = new StringParse();
            
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
        Scanner input = new Scanner(System.in);
        System.out.println("Hello!");
        while (true) {//Exit is handled in StringParser
            this.question = input.nextLine();
            System.out.println(parser.parse(question, db));
        }

    }
}