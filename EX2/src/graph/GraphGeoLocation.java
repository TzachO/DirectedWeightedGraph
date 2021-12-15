package graph;

import api.GeoLocation;

/**
 * @Author Tzah
 */

public class GraphGeoLocation implements GeoLocation {

    private double x;
    private double y;
    private double z;

    public GraphGeoLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(GeoLocation g) {
        return Math.sqrt((this.x() - g.x()) * (this.x() - g.x())
        + (this.y() - g.y()) * (this.y() - g.y()));
    }
}
