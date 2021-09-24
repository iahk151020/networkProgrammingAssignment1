package models;

import java.io.Serializable;

public class User implements Serializable{
    private String userid ;
    private String username;
    private String password;
    private String address;
    private String DoB;
    private String sex;
    private String description;
   

    public User(String userid, String username, String password, String address, String DoB, String sex, String description){
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.address = address;
        this.DoB = DoB;
        this.sex = sex;
        this.description = description;
    }

    public String getUserId(){
        return this.userid;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getAddress(){
        return this.address;
    }

    public String getDoB(){
        return this.DoB;
    }

    public String getSex(){
        return this.sex;
    }

    public String getDes(){
        return this.description;
    }

  
    


}
