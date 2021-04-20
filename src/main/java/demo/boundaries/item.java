package demo.boundaries;

public class item {
	 private ItemId itemId;

	    public item() {
	}

		public item(String space, int id) {
	        this.itemId = new ItemId(space, id);
	    }

		public ItemId getItemId() {
			return itemId;
		}

		public void setItemId(ItemId itemId) {
			this.itemId = itemId;
		}

	  
}
