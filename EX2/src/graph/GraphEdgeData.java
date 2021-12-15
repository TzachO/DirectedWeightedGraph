package graph;

import api.EdgeData;
import com.google.gson.annotations.SerializedName;

public class GraphEdgeData implements EdgeData {

    private int id;
    private int src;
    private int dest;

    @SerializedName(value = "w")
    private double weight;
    private String info;
    private int tag;

    public GraphEdgeData(int id, int src, int dest, double weight, String info, int tag) {
        this.id = id;
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.info = info;
        this.tag = tag;
    }

    public GraphEdgeData(int id, int src, int dest, double weight) {
        this.id = id;
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int getSrc() {
        return src;
    }

    @Override
    public int getDest() {
        return dest;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public void setTag(int tag) {
        this.tag = tag;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public String getInfo() {
        return info;
    }

}
