package cosc310.group8.chatbot;

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
        int iTopic, iType, iKey;
        //Use switches to convert from string identifiers to indices.
        switch(topic){
            //TODO cases
            case "unknown":
            default:
                iTopic = 0;
                break;
        }
        switch(type){
            //TODO cases
            case "unknown":
            default:
                iType = 0;
                break;
        }
        switch(keyword){
            //TODO cases
            case "unknown":
            default:
                iKey = 0;
                break;
        }
        String response = responses[iTopic][iType][iKey]
                [(int)(Math.random()*responses[iTopic][iType][iKey].length)]; //Randomly choose one of the correct resposes. 
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
    
}
