package demo;

public class UserBoundary {
	
	private userId userID;
	private String role;
	private String username;
	private String avatar;
	
	public void UserBoundry() {}
	
	public void UserBoundry(userId userID, String role, String username, String avatar) {
		this.userID = userID;
		this.role = role;
		this.username = username;
		this.avatar = avatar;
	}
	
	public String getAvatar() {
		return avatar;
	}
	public String getRole() {
		return role;
	}
	public userId getUserID() {
		return userID;
	}
	public String getUsername() {
		return username;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setUserID(userId userID) {
		this.userID = userID;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

}