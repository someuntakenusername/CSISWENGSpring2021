package esc.baylor.edu.groupProject.SwingGUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import esc.baylor.edu.groupProject.TransactionObjects.TransactionLog;


public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private TransactionLog tLog;
	
	
	public User(String u, String p) {
		username = u;
		password = p;
		tLog = null;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void writeUser(User serObj) {
		try {
		    FileOutputStream f = new FileOutputStream(new File("users.txt"));
		    ObjectOutputStream o = new ObjectOutputStream(f);

		    o.writeObject(serObj);

		    o.close();
		    f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void findUser(String username, String password) {
		
	}
	
	public void load(User serObj) {
		try {
			FileInputStream fi = new FileInputStream(new File("users.txt"));
	    	ObjectInputStream oi = new ObjectInputStream(fi);
	    
	    	serObj = (User) oi.readObject();

	    	oi.close();
	    	fi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



