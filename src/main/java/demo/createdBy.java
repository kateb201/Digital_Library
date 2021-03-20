package demo;

public class createdBy {
    private userId userId;

    public createdBy(String space, String email) {
        this.userId = new userId(space, email);
    }

    public userId getUserId() {
        return userId;
    }

    public void setUserId(userId userId) {
        this.userId = userId;
    }
}
