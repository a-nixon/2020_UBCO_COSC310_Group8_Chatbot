/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc310.group8.chatbot;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A
 */
public class DBLoader {
    public static Object[] load(String filepath){
        Object[] r = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filepath))){
            r = (Object[]) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(DBLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
