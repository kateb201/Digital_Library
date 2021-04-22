package twins.boundaries;

public class Location {
    private double lat;
    private double lng;

    public Location() {
        this.lat = 34.2503;
        this.lng = 25.6847;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
