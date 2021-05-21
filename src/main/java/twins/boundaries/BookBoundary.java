package twins.boundaries;

public class BookBoundary {
    VolumeInfo info;
    String publisher;
    String publishedDate;
    String Description;
    int pageCount;
    String language;

    public BookBoundary() {
    }

    public VolumeInfo getInfo() {
        return info;
    }

    public void setInfo(VolumeInfo info) {
        this.info = info;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
