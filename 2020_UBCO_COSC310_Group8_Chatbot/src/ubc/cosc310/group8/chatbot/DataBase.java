package ubc.cosc310.group8.chatbot;

/**
 *
 * @author Alec Nixon, 30905160
 */
public class DataBase {
    /**
     * A 3D array of strings. the first dimension corresponds to the topic,
     * the second to the type of input (questions, statements, etc), the third
     * to specific keywords, and the fourth is multiple possible answers (to 
     * provide variety).
     * The responses is ragged.
     * The first entry of the first three dimensions are reserved for responses
     * to unknown input.
     */
    private String[][][][] responses;
    
    /**
     * Reads content in from a file to the data base.
     */
    public DataBase(){
        //TODO
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
        //Allow the topic to change in response to input. If the topic should change, add the new topic to the end of the response separated with a "|" (no quotation marks).
        if(response.contains("|")){
            String newTopic = response.substring(response.indexOf("|")+1);
            //TODO update topic (call method in Main?)
            response = response.substring(0, response.indexOf("|"));
        }
        return response;
    }
    
}
