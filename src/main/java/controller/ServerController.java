package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.User;

import java.sql.Connection;
import java.net.Socket;

public class ServerController {
    private ServerSocket server;
    private int port = 9090;
    private Connection con;
    
    public ServerController(){
        try {
            connectToDB();
            server = new ServerSocket(port);
            while (true){
                listening();
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToDB() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/networkProgramming";
        String user = "root";
        String password = "1";
        con = DriverManager.getConnection(url, user, password);
        System.out.println("connected to DB");
    }

    public void listening() throws IOException, ClassNotFoundException, SQLException{
        Socket clientSocket = server.accept();
        System.out.println(clientSocket);
        ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
        Object data = ois.readObject();
        if (data instanceof User){
            User userData = (User)data;
            Statement stmt =  con.createStatement();
            String query = "SELECT * FROM dangki WHERE username='" + userData.getUsername() + "'"; 
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(rs.next());
            if (rs.next()){
                System.out.println("true thi vao");
                oos.writeObject("Username is unavailable");
            } else {
                System.out.println("deo true ma van vao");
                String query2 = "INSERT INTO dangki VALUES ('" +  userData.getUserId() + "','" + userData.getUsername() + "','" + userData.getPassword() + "','" + userData.getAddress() + "','" + userData.getDoB() + "','" + userData.getSex() + "','" + userData.getDes() +"')";
                stmt.executeUpdate(query2);
                oos.writeObject("ok");
            }
        }

    }

}
