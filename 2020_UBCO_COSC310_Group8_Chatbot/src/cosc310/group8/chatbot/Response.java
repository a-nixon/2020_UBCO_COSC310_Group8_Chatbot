/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc310.group8.chatbot;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author A
 */
class Response extends HBox{
    
    private final String text; //The text to return from the data base
    private final String topic, type, keyword;
    private final ArrayList<ArrayList<ArrayList<ArrayList<Response>>>> list;
    private final VBox parent;
    private final ResponseManager rm;
    
    public Response(VBox parent, ResponseManager rm,
            ArrayList<ArrayList<ArrayList<ArrayList<Response>>>> list,
            String topic, String type, String keyword, String text){
        this.parent = parent;
        this.rm = rm;
        this.list = list;
        this.topic = topic;
        this.type = type;
        this.keyword = keyword;
        this.text = text;
        
//        if(rm.string2index(ResponseManager.TOPICS_ID, topic) == -1){
//            list.add(rm.string2index(ResponseManager.TOPICS_ID, topic),
//                    new ArrayList<>());
//        }
//        if(rm.string2index(ResponseManager.TYPES_ID, type) == -1){
//            list.get(rm.string2index(ResponseManager.TOPICS_ID, topic))
//                    .add(rm.string2index(ResponseManager.TYPES_ID, type),
//                            new ArrayList<>());
//        }
//        if(rm.string2index(ResponseManager.KEYWORDS_ID, keyword) == -1){
//            list.get(rm.string2index(ResponseManager.TOPICS_ID, topic))
//                    .get(rm.string2index(ResponseManager.TYPES_ID, type))
//                    .add(rm.string2index(ResponseManager.KEYWORDS_ID, keyword),
//                            new ArrayList<>());
//        }
        
        list.get(rm.string2index(ResponseManager.TOPICS_ID, topic))
                .get(rm.string2index(ResponseManager.TYPES_ID, type))
                .get(rm.string2index(ResponseManager.KEYWORDS_ID, keyword))
                .add(this);
        getChildren().add(new Label(text));
        Button b = new Button("Delete");
        b.setOnAction((EventHandler) e -> {
            list.get(rm.string2index(ResponseManager.TOPICS_ID, topic))
                .get(rm.string2index(ResponseManager.TYPES_ID, type))
                .get(rm.string2index(ResponseManager.KEYWORDS_ID, keyword))
                .remove(this);
            parent.getChildren().remove(this);
        });
        getChildren().add(b);
    }
    
    /**
     * Gets the topic that this response will be filed under in the response
     * database.
     * @return topic value of response entry.
     */
    public String getTopic(){
        return topic;
    }
    /**
     * Gets the type that this response will be filed under in the response
     * database.
     * @return type value of response entry.
     */
    public String getType(){
        return type;
    }
    /**
     * Gets the keyword that this response will be filed under in the response
     * database.
     * @return keyword value of response entry.
     */
    public String getKeyword(){
        return keyword;
    }
    /**
     * Gets the text that will be added to the response database.
     * @return the response text.
     */
    public String getText(){
        return text;
    }
    @Override
    public String toString(){
        return topic+" at "+rm.string2index(ResponseManager.TOPICS_ID, topic)
                +", "+type+" at "
                +rm.string2index(ResponseManager.TYPES_ID, type)+", "+keyword
                +" at "+rm.string2index(ResponseManager.KEYWORDS_ID, keyword)
                +": "+text;
    }
    
}
