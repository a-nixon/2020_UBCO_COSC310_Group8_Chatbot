package cosc310.group8.chatbot;

import java.util.ArrayList;

public class StringParse{
    
    private String cTopic = "unknown", cType = "unknown", cKeyword = "unknown";

    public String parse(String input, DataBase db){
        String sentence = input;
        String response = "";
        String[] parsed = sentence.split(" ");
        ArrayList<Integer> usedWords = new ArrayList<>(2);
        for(int i = 0; i < parsed.length; i++){
            if(db.isTopic(parsed[i])){
                cTopic = parsed[i];
                break;
            }
        }
        for(int i = 0; i < parsed.length; i++){
            if(db.isType(parsed[i]) && !usedWords.contains(i)){
                cTopic = parsed[i];
                break;
            }
        }
        for(int i = 0; i < parsed.length; i++){
            if(db.isKeyword(parsed[i]) && !usedWords.contains(i)){
                cKeyword = parsed[i];
                break;
            }
        }

        return db.getResponse(cTopic, cType, cKeyword);
    }




}