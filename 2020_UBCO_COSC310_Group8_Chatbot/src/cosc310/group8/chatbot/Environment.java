/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc310.group8.chatbot;

/**
 *
 * @author A
 */
public interface Environment {
    
    void setDB(DataBase db);
    DataBase getDB();
    void hostSocket(int port);
    void hostSocket(int port, int maxLines);
    void connectSocket(String address, int port);
    void connectSocket(String address, int port, int maxLines);
    
}
