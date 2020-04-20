/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc310.group8.chatbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author A
 */
public class Client extends Task<Integer>{
    
    private final int maxLines;
    private final StringParse parser;
    private final int port;
    private final String addr;
    private Socket soc;
    private final Environment e;
    
    public Client(Environment e, int port, String address, int maxLines, StringParse parser){
        this.maxLines = maxLines;
        this.parser = parser;
        this.port = port;
        addr = address;
        this.e = e;
    }

    @Override
    public Integer call(){ //Should be refactored 
        int iter = 0;
        try {
            soc = new Socket(addr, port);
            updateMessage("Connected to: "+soc.getInetAddress().toString()+"\n");
            BufferedReader input = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            PrintWriter output = new PrintWriter(soc.getOutputStream(), true);
            while (maxLines == 0 ? true : iter < maxLines && !soc.isClosed()) {
                String serverMsg = input.readLine();
                updateMessage("server: " + serverMsg+"\n");
                String response = parser.parse(serverMsg, e);
                updateMessage("chatbot: " + response+"\n");
                output.println(response);
                iter++;
            }
            soc.close();
        } catch (IOException iOException) {
            updateMessage("Client exception.\n");
            System.out.println("Client exception");
        }
        return iter;
    }
    @Override
    public boolean cancel(boolean cancel){
        try {
            soc.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.cancel(cancel);
    }
    
}
