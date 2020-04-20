/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc310.group8.chatbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author A
 */
public class Server extends Task<Integer>{
    
    private final int maxLines;
    private final StringParse parser;
    private final int port;
    private Socket soc;
    private final Environment e;
    
    public Server(Environment e, int port, int maxLines, StringParse parser){
        this.maxLines = maxLines;
        this.parser = parser;
        this.port = port;
        this.e = e;
    }

    @Override
    public Integer call(){
        int iter = 0;
        try {
            ServerSocket servSoc = new ServerSocket(port);
            soc = servSoc.accept();
            updateMessage("Connection from: "+soc.getInetAddress().toString()+"\n");
            BufferedReader input = new BufferedReader(new InputStreamReader(soc.getInputStream()));
//            InputStream input = soc.getInputStream();
            PrintWriter output = new PrintWriter(soc.getOutputStream(), true);
            while (maxLines == 0 ? true : iter < maxLines && !soc.isClosed()) {
                String clientMsg = input.readLine();
//                String clientMsg = "";
//                boolean newMsg = false;
//                while(!newMsg){
//                    if(input.available() > 0){
//                        byte[] b = new byte[input.available()];
//                        input.read(b);
//                        clientMsg = clientMsg.concat(new String(b));
//                        if(clientMsg.contains("\n")){
//                            newMsg = true;
//                        }
//                    }
//                }
                System.out.println("Message from client.");
                updateMessage("client: " + clientMsg+"\n");
                String response = parser.parse(clientMsg, e);
                updateMessage("chatbot: " + response+"\n");
                output.println(response);
                iter++;
            }
            if (!soc.isClosed()) {
                soc.close();
            }
        } catch (IOException iOException) {
            updateMessage("Server exception.\n");
            System.out.println("Server exception.");
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
