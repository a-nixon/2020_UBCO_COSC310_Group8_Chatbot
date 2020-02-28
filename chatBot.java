import java.util.Scanner;
public class chatBot{
    private String role;
    private String userName;
    private String question;
    
    public chatBot(){
        this.role = "Call Center Agent";
        init();

            
    }

    public String getRole(){
        return this.role;
    }
    public void setRole(String s){
        this.role = s;
    }

    public void init(){
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, my name is chatBot. May I start with your name?");
        this.userName = input.nextLine();
        System.out.println("Hello " + userName + " How may I help you?");
        this.question = input.nextLine();
        stringParse.parse(question);

    }
}