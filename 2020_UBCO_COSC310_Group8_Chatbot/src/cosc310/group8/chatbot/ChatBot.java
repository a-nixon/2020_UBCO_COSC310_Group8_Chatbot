package cosc310.group8.chatbot;

import java.util.Scanner;
public class ChatBot implements Runnable{
    private String role;
    private String userName;
    private String question;
    
    public ChatBot(){
        this.role = "Call Center Agent";

            
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
        System.out.println("Hello, my name is chatBot. May I start with your name?");
        this.userName = input.nextLine();
        System.out.println("Hello " + userName + " How may I help you?");
        this.question = input.nextLine();
        StringParse.parse(question);

    }
}