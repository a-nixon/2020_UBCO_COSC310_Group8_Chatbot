import java.util.Scanner;
public class chatBot{
    private String role;
    private String userName;
    
    public chatBot(){
        this.role = "Call Center Agent";
        
        Scanner input = new Scanner(System.in);
        System.out.println("Hello, my name is chatBot. May I start with your name?");
        this.userName = input.nextLine();
        System.out.println("Hello " + userName + " How may I help you?");
        
            
    }

    public String getRole(){
        return this.role;
    }
    public void setRole(String s){
        this.role = s;
    }
}