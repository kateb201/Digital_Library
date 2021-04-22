package twins.boundaries;

public class InvokedBy {
	private UserIdBoundary userIdBoundary;

    public InvokedBy() {
	}

	public InvokedBy(String space, String email) {
        this.userIdBoundary = new UserIdBoundary(space, email);
    }

    public UserIdBoundary getUserId() {
        return userIdBoundary;
    }

    public void setUserId(UserIdBoundary userIdBoundary) {
        this.userIdBoundary = userIdBoundary;
    }
}
