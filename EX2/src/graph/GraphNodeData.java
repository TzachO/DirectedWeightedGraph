package graph;

import api.GeoLocation;
import api.NodeData;
import com.google.gson.annotations.SerializedName;

public class GraphNodeData implements NodeData {

    @SerializedName(value = "id")
    private int key;
    private String pos;
    private GeoLocation location;
    private double weight;
    private String info;
    private int tag;

    public GraphNodeData(int key, GeoLocation location, double weight){
        this.key = key;
        this.location = location;
        this.weight = weight;
//        this.info = info;
//        this.tag = tag;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public GeoLocation getLocation() { return this.location; }

    @Override
    public void setLocation(GeoLocation p) { this.location = p; }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double w) { this.weight = w; }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String s) { this.info = s; }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    public String getPos() {
        return pos;
    }
}
