package cosc310.group8.chatbot;

import java.util.ArrayList;

/**
 *
 * @author Alec Nixon, 30905160
 */
public class DataBase { 
    /**
     * A 3D array of strings. the first dimension corresponds to the keyword,
 the second to the type of input (questions, statements, etc), the third
 to specific keywords, and the fourth is multiple possible answers (to 
 provide variety).
 The responses is ragged.
 The first entry of the first three dimensions are reserved for responses
 to unknown input.
     */
    private final String[][][][] responses;
    private final String[] topics;
    private final String[] types;
    private final String[] keywords;
    
    /**
     * Reads content in from a file to the data base.
     */
    public DataBase(){
        Object[] ser = DBLoader.load(Main.getFilepath());
        responses = (String[][][][]) ser[1];
        String[][] header = (String[][]) ser[0];
        topics = header[0];
        types = header[1];
        keywords = header[2];
    }
    
    public String getResponse(String topic, String type, String keyword){
        int iTopic = string2index(topics, topic);
        int iType = string2index(types, type);
        int iKey = string2index(keywords, keyword);
        //Use switches to convert from string identifiers to indices.
//        switch(topic){
//            //TODO cases
//            case "unknown":
//            default:
//                iTopic = 0;
//                break;
//        }
//        switch(type){
//            //TODO cases
//            case "unknown":
//            default:
//                iType = 0;
//                break;
//        }
//        switch(keyword){
//            //TODO cases
//            case "unknown":
//            default:
//                iKey = 0;
//                break;
//        }
        String response;
        if (responses[iTopic][iType][iKey].length > 0) {
            response = responses[iTopic][iType][iKey][(int) (Math.random() * responses[iTopic][iType][iKey].length)]; //Randomly choose one of the correct resposes.
        } else {
            response = responses[string2index(topics, "unknown")]
                    [string2index(types, "unknown")]
                    [string2index(keywords, "unknown")]
                    [(int) (Math.random() 
                    * responses[string2index(topics, "unknown")]
                    [string2index(types, "unknown")]
                    [string2index(keywords, "unknown")].length)];
        }
        //Allow the keyword to change in response to input. If the keyword should change, add the new keyword to the end of the response separated with a "|" (no quotation marks).
        if(response.contains("|")){
            String newTopic = response.substring(response.indexOf("|")+1);
            //TODO update keyword (call method in Main?)
            response = response.substring(0, response.indexOf("|"));
        }
        return response;
    }
    public boolean isTopic(String s){
        for(String topic : topics){
            if (s.equals(topic)){
                return true;
            }
        }
        return false;
    }
    public boolean isType(String s){
        for(String type : types){
            if (s.equals(type)){
                return true;
            }
        }
        return false;
    }
    public boolean isKeyword(String s){
        for(String keyword : keywords){
            if (s.equals(keyword)){
                return true;
            }
        }
        return false;
    }
    public int numTopics(){
        return topics.length;
    }
    public int numTypes(){
        return types.length;
    }
    public int numKeywords(){
        return keywords.length;
    }
    /**
     * 
     * @param list the list the index is being found for.
     * @param entry the the String being searched for
     * (does not need to be the same object).
     * @return 
     */
    public static int string2index(String[] list, String entry){
        for(int i = 0; i < list.length; i++){
            if (entry.equals(list[i])){
                return i;
            }
        }
        for(int i = 0; i < list.length; i++){
            if(list[i].equals("unknown")){
                return i;
            }
        }
        System.err.println("Could not find entry for list");
        return -1;
    }
    public void printDB(){
        System.out.println("Topics:");
        for (int i = 0; i < topics.length; i++) {
            System.out.println("\t"+i+": "+topics[i]);
        }
        System.out.println("Types:");
        for (int i = 0; i < types.length; i++) {
            System.out.println("\t"+i+": "+types[i]);
        }
        System.out.println("Keywords:");
        for (int i = 0; i < keywords.length; i++) {
            System.out.println("\t"+i+": "+keywords[i]);
        }
        for(int i1 = 0; i1 < responses.length; i1++){
            System.out.println(topics[i1]);
            for(int i2 = 0; i2 < responses[i1].length; i2++){
                System.out.println("\t"+types[i2]);
                for(int i3 = 0; i3 < responses[i1][i2].length; i3++){
                    System.out.println("\t\t"+keywords[i3]);
                    System.out.println("\t\t\tNum. responses: "
                            +responses[i1][i2][i3].length);
                    for(String r : responses[i1][i2][i3]){
                        System.out.println("\t\t\t"+r);
                    }
                }
            }
        }
    }
    
}
