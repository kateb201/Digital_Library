package twins.boundaries;

public class OperationId {

	private String space;
	private int id;

	public OperationId() {
		this.space = "2021b.twins";
		this.id = 451;
	}
	public OperationId(String space, int id) {
			this.space = space;
			this.id = id;
	
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
