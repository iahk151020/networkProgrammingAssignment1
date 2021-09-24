package viewClass;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import controller.ClientController;
import models.User;


public class ClientView extends JFrame implements ActionListener {

    
    private JTextField usernameF;
    private JPasswordField passwordF;
    private JTextField dobF;
    private JTextField addressF;
    private JTextField desF;
    private JTextField userIdF;
    private JTextField sexF;
    private JButton btnDK;
    private ClientController clientController;


    public ClientView(){
        super("Registration form");
        clientController = new ClientController();
        JPanel content = new JPanel();

        usernameF = new JTextField(15);
        dobF = new JTextField(15);
        addressF = new JTextField(15);
        desF = new JTextField(15);
        userIdF = new JTextField(15);
        sexF = new JTextField(15);
        passwordF = new JPasswordField(15);
        passwordF.setEchoChar('*');

        content.setLayout(new FlowLayout());
        content.add(new JLabel("userId:"));
        content.add(userIdF);
        content.add(new JLabel("username:"));
        content.add(usernameF);
        content.add(new JLabel("password:"));
        content.add(passwordF);
        btnDK = new JButton("Dang ki");
        
       content.add(new JLabel("address:"));
        content.add(addressF);
        content.add(new JLabel("DoB:"));
        content.add(dobF);
        content.add(new JLabel("Sex"));
        content.add(sexF);
        content.add(new JLabel("Description:"));
        content.add(desF);
        content.add(btnDK);
        btnDK.addActionListener(this);
        
        
        this.setContentPane(content);
        this.pack();
        this.addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
            System.exit(0);
            }
        });

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource().equals(btnDK)){
            User userData = new User(userIdF.getText(), usernameF.getText(), passwordF.getText(), addressF.getText(), dobF.getText(), sexF.getText(), desF.getText());
            try {
                clientController.openConnection();
                clientController.sendData(userData);
                String reply = clientController.getData();
                System.out.println("Client View reply: " + reply);
                if (reply.equals("ok")){
                    System.out.println("ok thi vao");
                    JOptionPane.showMessageDialog(this, "Dang ki thanh cong");
                } else {
                    System.out.println("deo ok ma van vao");
                    JOptionPane.showMessageDialog(this, "Nhap lai thong tin");
                    clientController.closeConnection();
                }
            } catch (IOException | ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
        }
        
    }



}
