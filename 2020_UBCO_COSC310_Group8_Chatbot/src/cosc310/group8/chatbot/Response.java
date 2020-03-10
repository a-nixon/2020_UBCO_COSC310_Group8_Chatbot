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
    
    private String text; //The text to return from the data base
    private String topic, type, keyword;
    private ArrayList<Response> list;
    private VBox parent;
    
    public Response(VBox parent, ArrayList<Response> list, String topic, String type, String keyword, String text){
        this.parent = parent;
        this.list = list;
        this.topic = topic;
        this.type = type;
        this.keyword = keyword;
        this.text = text;
        
        list.add(this);
        this.getChildren().add(new Label(text));
        Button b = new Button("Delete");
        b.setOnAction((EventHandler) e -> {
            list.remove(this);
            parent.getChildren().remove(this);
        });
        this.getChildren().add(b);
    }
    
    public String getTopic(){
        return topic;
    }
    public String getType(){
        return type;
    }
    public String getKeyword(){
        return keyword;
    }
    public String getText(){
        return text;
    }
    
}
