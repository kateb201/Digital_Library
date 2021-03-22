package demo;

public class invokedBy {
	private userId userId;

    public invokedBy() {
	}

	public invokedBy(String space, String email) {
        this.userId = new userId(space, email);
    }

    public userId getUserId() {
        return userId;
    }

    public void setUserId(userId userId) {
        this.userId = userId;
    }
}
