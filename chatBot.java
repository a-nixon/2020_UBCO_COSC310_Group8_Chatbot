public class chatBot{
    private String role;

    
    public chatBot()
        this.role = "Call Center Agent";
    }

    public String getRole(){
        return this.role;
    }
    public String setRole(String s){
        this.role = s;
    }
}