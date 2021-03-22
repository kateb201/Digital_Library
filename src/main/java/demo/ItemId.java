package demo;

public class ItemId {

    private String space;
    private int id;
    
    

    public ItemId() {
	
	}

	public ItemId(String space, int id) {
        this.space = space;
        this.id = id > 0 ? id : 99;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
