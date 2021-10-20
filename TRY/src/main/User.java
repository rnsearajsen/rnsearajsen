package main;

public class User {
	  protected int user_id;
	  protected String username;
	  protected String password;
	  protected String access;
	  protected String firstname;
	  protected String lastname;
	  
public User() {	  
	  }	  
public User(int user_id) {
	  this.user_id = user_id; }

public User(int user_id, String username, String password, String access, String firstname, String lastname) {	  
    this(username, password, access, firstname, lastname);
    this.user_id = user_id;     
}
public User(String username, String password, String access, String firstname, String lastname) {
	  this.username = username;
	  this.password = password;
	  this.access = access;
	  this.firstname = firstname;
	  this.lastname = lastname;
}
/**
 * @return the user_id
 */
public int getUser_id() {
	return user_id;
}
/**
 * @param user_id the user_id to set
 */
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
/**
 * @return the username
 */
public String getUsername() {
	return username;
}
/**
 * @param username the username to set
 */
public void setUsername(String username) {
	this.username = username;
}
/**
 * @return the password
 */
public String getPassword() {
	return password;
}
/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * @return the access
 */
public String getAccess() {
	return access;
}
/**
 * @param access the access to set
 */
public void setAccess(String access) {
	this.access = access;
}
/**
 * @return the firstname
 */
public String getFirstname() {
	return firstname;
}
/**
 * @param firstname the firstname to set
 */
public void setFirstname(String firstname) {
	this.firstname = firstname;
}
/**
 * @return the lastname
 */
public String getLastname() {
	return lastname;
}
/**
 * @param lastname the lastname to set
 */
public void setLastname(String lastname) {
	this.lastname = lastname;
}

}
