package twins.boundaries;
public class NewUserDetails {
	
	private String email;
	private String role;
	private String userName;
	private String avatar;
	
	public NewUserDetails() {
	}
	
	public void NewUserDetails(String email, String role, String userName, String avatar) {
		this.email = email;
		this.role = role;
		this.userName  = userName;
		this.avatar = avatar;
	}
	
	public String getAvatar() {
		return avatar;
	}
	public String getEmail() {
		return email;
	}
	public String getRole() {
		return role;
	}
	public String getUserName() {
		return userName;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}