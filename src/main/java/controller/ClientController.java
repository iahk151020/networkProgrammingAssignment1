package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import models.User;

public class ClientController {
   private Socket socket;

   public ClientController(){}

   public void openConnection() throws UnknownHostException, IOException{
        socket = new Socket("localhost", 9090);
   }

   public void closeConnection() throws IOException{
       socket.close();
   }

   public void sendData(User userData) throws IOException{
       ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
       oos.writeObject(userData);
   }

   public String getData() throws IOException, ClassNotFoundException{
       String reply = null;
       ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
       Object data = ois.readObject();
       
       if (data instanceof String){
           reply = (String)data;
       }
       System.out.println("reply: " + reply);
       return reply;
    }

}
