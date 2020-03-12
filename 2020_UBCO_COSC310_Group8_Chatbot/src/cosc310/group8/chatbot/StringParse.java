package cosc310.group8.chatbot;

import java.util.ArrayList;

public class StringParse{
    
    private String cTopic = "unknown", cType = "unknown", cKeyword = "unknown";

    public String parse(String input, DataBase db){
        //String response;
        input = input.toLowerCase();
        if(input.equals("exit") || input.equals("good bye")
                || input.equals("good bye.") || input.equals("bye") 
                || input.equals("bye.")){
            System.out.println("Good bye!");
            System.exit(0);
        }
        if(input.equals("/printdb")){
            db.printDB();
            return "";
        }
        String[] parsed = input.split(" ");
        ArrayList<Integer> usedWords = new ArrayList<>(2);
        int topicFlag = -1;
        for(int i = 0; i < parsed.length; i++){
            if(db.isTopic(parsed[i])){
                cTopic = parsed[i];
                topicFlag = i;
                break;
            }
        }
        int typeFlag = -1;
        for(int i = 0; i < parsed.length; i++){
            if(db.isType(parsed[i]) && i != topicFlag){
                cType = parsed[i];
                typeFlag = i;
                break;
            }
        }
        for(int i = 0; i < parsed.length; i++){
            if(db.isKeyword(parsed[i]) && i != topicFlag && i != typeFlag){
                cKeyword = parsed[i];
                break;
            }
        }
//        System.out.println("Getting response for: topic="+cTopic+", type="+cType+", keyword="+cKeyword); //For debugging only.
        return db.getResponse(cTopic, cType, cKeyword);
    }




}
