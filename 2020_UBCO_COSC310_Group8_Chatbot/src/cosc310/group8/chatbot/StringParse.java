package cosc310.group8.chatbot;

import java.util.ArrayList;

public class StringParse{
    
    private String cTopic = "unknown", cType = "unknown", cKeyword = "unknown";

    public String parse(String input, Environment chatbot){
        //String response;
        input = input.toLowerCase();
        switch(input){
            case "/exit": //Need to remove hard coded differences
            case "exit":
            case "good bye":
            case "good bye.":
            case "bye":
            case "bye.":
                System.out.println("Good bye!");
                System.exit(0);
            case "/printdb":
                chatbot.getDB().printDB();
                return "";
            case "/refreshdb":
                chatbot.setDB(new DataBase());
                return "Database loaded.";
            case "/help":
                return    "/exit     \tExits the program (all windows).\n"
                        + "/refreshdb\tReloads the database.\n"
                        + "/printdb  \tPrints the database to the system console.";
        }
        String[] parsed = input.split(" ");
        ArrayList<Integer> usedWords = new ArrayList<>(2);
        int topicFlag = -1;
        for(int i = 0; i < parsed.length; i++){
            if(chatbot.getDB().isTopic(parsed[i])){
                cTopic = parsed[i];
                topicFlag = i;
                break;
            }
        }
        int typeFlag = -1;
        for(int i = 0; i < parsed.length; i++){
            if(chatbot.getDB().isType(parsed[i]) && i != topicFlag){
                cType = parsed[i];
                typeFlag = i;
                break;
            }
        }
        for(int i = 0; i < parsed.length; i++){
            if(chatbot.getDB().isKeyword(parsed[i]) && i != topicFlag && i != typeFlag){
                cKeyword = parsed[i];
                break;
            }
        }
//        System.out.println("Getting response for: topic="+cTopic+", type="+cType+", keyword="+cKeyword); //For debugging only.
        return "Chatbot: "+chatbot.getDB().getResponse(cTopic, cType, cKeyword);
    }




}
