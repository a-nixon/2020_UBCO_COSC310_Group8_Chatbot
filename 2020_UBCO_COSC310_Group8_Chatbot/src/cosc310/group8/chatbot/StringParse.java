package cosc310.group8.chatbot;

public class StringParse{

    public static String parse(String input){
        String sentence = input;
        String response = "";
        String[] parsed = sentence.split(" ");
        String qWord = parsed[0];

        if(qWord.equalsIgnoreCase("who")){
            //System.out.println("This is a who question");
        }
        if(qWord.equalsIgnoreCase("what")){
            //System.out.println("This is a what question");
        }
        if(qWord.equalsIgnoreCase("where")){
            //System.out.println("this is a where question");
        }
        if(qWord.equalsIgnoreCase("when")){
            //System.out.println("This is a when question");
        }
        if(qWord.equalsIgnoreCase("why")){
            //System.out.println("this is a why question");
        }
        if(qWord.equalsIgnoreCase("how")){
            //System.out.println("this is a how question");
        }

        return response;
    }




}