package cosc310.group8.chatbot;

import java.util.ArrayList;

public class StringParse{
    
    private String cTopic = "unknown", cType = "unknown", cKeyword = "unknown";

    public String parse(String input, Environment chatbot){
        //String response;
        String[] parsed = input.toLowerCase().split(" ");
        switch(parsed[0]){
            case "/exit": //Need to remove hard coded differences
            case "exit":
            case "good":
            case "bye":
            case "bye.":
                if (!parsed[0].equals("good") || parsed[1].equals("bye") || parsed[1].equals("bye.")) {
                    System.out.println("Good bye!");
                    System.exit(0);
                }
            case "/printdb":
                chatbot.getDB().printDB();
                return "";
            case "/refreshdb":
                chatbot.setDB(new DataBase());
                return "Database loaded.";
            case "/join":
                switch (parsed.length) {
                    case 3:
                        chatbot.connectSocket(parsed[1], Integer.parseInt(parsed[2]));
                        return "Connecting to server. . .";
                    case 4:
                        chatbot.connectSocket(parsed[1], Integer.parseInt(parsed[2]), Integer.parseInt(parsed[3]));
                        break;
                    default:
                        return "\"/join\" requires the form \"/join <address> <port>\"";
                }
            case "/host":
                switch (parsed.length) {
                    case 2:
                        chatbot.hostSocket(Integer.parseInt(parsed[1]));
                        return "Starting server. . .";
                    case 3:
                        chatbot.hostSocket(Integer.parseInt(parsed[1]), Integer.parseInt(parsed[2]));
                        return "Starting server. . .";
                    default:
                        return "\"/host\" requires the form \"/join <address> <port>\"";
                }
            case "/remote":
                if (chatbot instanceof GUIController) {
                    switch (parsed.length) {
                        case 3:
                            ((GUIController)chatbot).connectTerminal(parsed[1], Integer.parseInt(parsed[2]));
                            return "Connecting to server. . .";
                        case 4:
                            ((GUIController)chatbot).connectTerminal(parsed[1], Integer.parseInt(parsed[2]), Integer.parseInt(parsed[3]));
                            break;
                        default:
                            return "\"/remote\" requires the form \"/join <address> <port>\"";
                    }
                }else{
                    return "/remote is only available on GUI consoles.";
                }
            case "/help":
            case "/h":
                return    "Help:\n"
                        + "/help                 \tShows this dialogue.\n"
                        + "/exit                 \tExits the program (all windows).\n"
                        + "/refreshdb            \tReloads the database.\n"
                        + "/printdb              \tPrints the database to the system console.\n"
                        + "/host<port>           \tStarts a chatbot server on the specified port.\n"
                        + "/join<address><port>  \tConnects the chatbot to the specified server.\n"
                        + "/remote<address><port>\tAllows the user to use the GUI to connect to the specified server. Only available in the GUI.";
        }
        
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
