package twins;

import twins.boundaries.BookBoundary;

public class Books {

    String kind;
    int totalItems;
    BookBoundary[] items = new BookBoundary[10];

    public Books() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public BookBoundary[] getItems() {
        return items;
    }

    public void setItems(BookBoundary[] items) {
        this.items = items;
    }
}
