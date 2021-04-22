package twins.boundaries;

public class InvokedBy {
	private UserId userId;

    public InvokedBy() {
	}

	public InvokedBy(String space, String email) {
        this.userId = new UserId(space, email);
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}
