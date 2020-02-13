/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubc.cosc310.group8.chatbot;

/**
 *
 * @author A
 */
public class Main {
    public static void Main(String[] args){
        //Read in command line arguments
        for(int i = 0; i < args.length; i++){ //not using enhanced loop so that we can manually increase i to handle flag variables. e.g. -flagthattakesanumber 3 -> if args[i].equals("-flagthattakesanumber"){i++; intVar = Integer.parseInt(args[i]);}
            switch(args[i]){
                default:
                    break;
            }
        }
    }
}
