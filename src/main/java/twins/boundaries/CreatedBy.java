package twins.boundaries;

public class CreatedBy {
    private UserIdBoundary userIdBoundary;

    public CreatedBy(String space, String email) {
        this.userIdBoundary = new UserIdBoundary(space, email);
    }

    public UserIdBoundary getUserId() {
        return userIdBoundary;
    }

    public void setUserId(UserIdBoundary userIdBoundary) {
        this.userIdBoundary = userIdBoundary;
    }
}
