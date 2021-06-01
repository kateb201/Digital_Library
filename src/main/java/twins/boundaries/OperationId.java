package twins.boundaries;

public class OperationId {

	private String space;
	private String id;

	public OperationId() {
		this.space = "2021b.katya.boyko";
		this.id = "451";
	}
	public OperationId(String space, String id) {
			this.space = space;
			this.id = id;
	
	}
	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
