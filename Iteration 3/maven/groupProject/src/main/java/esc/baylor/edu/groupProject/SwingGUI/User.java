package esc.baylor.edu.groupProject.SwingGUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	/*
	public void writeUser() {
		String filename = null;
		filename = String.valueOf(this.hashCode());
		filename += ".txt";
		
		try {
		    FileOutputStream f = new FileOutputStream(new File(filename));
		    ObjectOutputStream o = new ObjectOutputStream(f);
		    
		    o.writeObject(this.tLog);

		    o.close();
		    f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void readUser() throws FileNotFoundException {
		String filename = null;
		filename = String.valueOf(this.hashCode());
		filename += ".txt";
		
		try {
			FileInputStream fi = new FileInputStream(new File(filename));
	    	ObjectInputStream oi = new ObjectInputStream(fi);
	    
	    	this.tLog = (TransactionLog) oi.readObject();

	    	oi.close();
	    	fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	*/
	
	public String findFilename() {
		String filename = null;
		filename = String.valueOf(this.hashCode());
		filename += ".txt";
		return filename;
	}
}



