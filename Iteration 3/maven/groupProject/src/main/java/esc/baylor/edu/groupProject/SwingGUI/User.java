package esc.baylor.edu.groupProject.SwingGUI;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import esc.baylor.edu.groupProject.TransactionObjects.Category;
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
		    FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
		    ObjectOutputStream o = new ObjectOutputStream(f);

		    // Write objects to file
		    o.writeObject(p1);
		    o.writeObject(p2);

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
			ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("test.txt"));
			objectIn.readObject();
			objectIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
    }
	
	
}

try {
    FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
    ObjectOutputStream o = new ObjectOutputStream(f);

    // Write objects to file
    o.writeObject(p1);
    o.writeObject(p2);

    o.close();
    f.close();

    FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
    ObjectInputStream oi = new ObjectInputStream(fi);

    // Read objects
    Person pr1 = (Person) oi.readObject();
    Person pr2 = (Person) oi.readObject();

    System.out.println(pr1.toString());
    System.out.println(pr2.toString());

    oi.close();
    fi.close();

} catch (FileNotFoundException e) {
    System.out.println("File not found");
} catch (IOException e) {
    System.out.println("Error initializing stream");
} catch (ClassNotFoundException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}


