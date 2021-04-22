package twins.boundaries;

public class UserBoundary {
	
	private UserIdBoundary userIDBoundary;
	private String role;
	private String username;
	private String avatar;
	
	public void UserBoundry() {}
	
	public void UserBoundry(UserIdBoundary userIDBoundary, String role, String username, String avatar) {
		this.userIDBoundary = userIDBoundary;
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
	public UserIdBoundary getUserID() {
		return userIDBoundary;
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
	public void setUserID(UserIdBoundary userIDBoundary) {
		this.userIDBoundary = userIDBoundary;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

}