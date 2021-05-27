package twins.boundaries;

public class CreatedBy {
    private UserId userId;

    public CreatedBy() {
    }
    
    public CreatedBy(String space, String email) {
        this.userId = new UserId(space, email);
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}
