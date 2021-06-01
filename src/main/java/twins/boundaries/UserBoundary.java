package twins.boundaries;

public class UserBoundary {
    
    private UserId userId;
    private String username;
    private String role;
    private String avatar;

    public UserBoundary() {
    	
    }

    public UserBoundary(UserId userId, String role, String username, String avatar) {
        this.userId = userId;
        this.username = username;
        this.userId.space = "2021b.katya.boyko";
        this.role = role;
        this.avatar = avatar;
    }
    
    public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}

	public String getAvatar() {
        return avatar;
    }

    public String getRole() {
        return role;
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

    public void setUsername(String username) {
        this.username = username;
    }

}