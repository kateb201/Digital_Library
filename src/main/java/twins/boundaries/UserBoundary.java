package twins.boundaries;

public class UserBoundary {

    private String username;
    private String email;
    private String role;
    private String avatar;

    public UserBoundary() {
    }

    public UserBoundary(String username, String email, String role, String avatar) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}