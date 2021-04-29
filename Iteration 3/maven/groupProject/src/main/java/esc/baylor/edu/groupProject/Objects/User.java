package esc.baylor.edu.groupProject.Objects;

import java.io.Serializable;


/**
 * 
 * Each user object contains the username and password for the user
 * which are used to generate the filename for the users saved data
 * 
 * @author Timmy
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;	
	
	/**
	 * 
	 * @param u The users username
	 * @param p The users password
	 */
	public User(String u, String p) {
		username = u;
		password = p;
	}
	
	/**
	 * 
	 * @return The associated users username
	 */
	public String getUsername() {
		return username;
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
	
	public String findFilename() {
		String filename = null;
		filename = String.valueOf(this.hashCode());
		filename += ".txt";
		return filename;
	}
}



