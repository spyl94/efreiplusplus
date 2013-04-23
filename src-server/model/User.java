package model;

import java.io.Serializable;

public class User implements Serializable {
    
    private String login;
    private String pass;
    private int sid;
    private int tid;
    
    public User() {
        
    }
    
    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
        sid=0;
        tid=0;
    }
    
    public User(String login, String pass, int sid, int tid) {
        this.login = login;
        this.pass = pass;
        this.sid = sid;
        this.tid = tid;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getPass() {
        return pass;
    }
    
    public int getSid() {
        return sid;
    }
    public int getTid() {
        return tid;
    }

}
