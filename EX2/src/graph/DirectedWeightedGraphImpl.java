package graph;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import utils.GraphUtils;

import java.util.*;

public class DirectedWeightedGraphImpl implements DirectedWeightedGraph {

    private int MC = 0;

    private Map<Integer, NodeData> nodes;               //  Node-ID --> NodeData
    private Map<Integer, EdgeData> edges;               //   Edge-ID --> edge
    private Map<Integer, Set<EdgeData>> srcToEdges = new HashMap<>();     //   Node-ID --> set of edges which this Node-ID is a src node for them
    private Map<Integer, Set<EdgeData>> destToEdges = new HashMap<>();    //   Node-ID --> set of edges which this Node-ID is a dest node for them

    public DirectedWeightedGraphImpl(Map<Integer, NodeData> nodes, Map<Integer, EdgeData> edges) {
        this.nodes = nodes;
        this.edges = edges;

        edges.entrySet().forEach((e) -> {
            EdgeData edge = e.getValue();

            // Adding to srcToEdges
            Set<EdgeData> srcEdges = srcToEdges.get(edge.getSrc());
            if(srcEdges == null){
                srcEdges = new HashSet<>();
            }
            srcEdges.add(edge);
            srcToEdges.put(edge.getSrc(), srcEdges);

            // Adding to destToEdges
            Set<EdgeData> destEdges = destToEdges.get(edge.getSrc());
            if(destEdges == null){
                destEdges = new HashSet<>();
            }
            destEdges.add(edge);
            destToEdges.put(edge.getDest(), destEdges);
        });


    }


    @Override
    public NodeData getNode(int key) {// todo - what should be returned in case it doesnt exist??
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {// todo - what should be returned in case it doesnt exist??, What if they've created multiple edges with the same src & dest??
        int id = GraphUtils.nodesToEdgeId(src, dest);
        return edges.get(id);
    }

    @Override
    public void addNode(NodeData n) {// todo - is it acceptable if the axis location is negative?? (i.e x= -1)
        if (!nodes.containsKey(n.getKey())){
            this.incrementMC();
            nodes.put(n.getKey(), n);
            srcToEdges.put(n.getKey(), new HashSet<EdgeData>());
            destToEdges.put(n.getKey(), new HashSet<EdgeData>());
        }
    }

    @Override
    public void connect(int src, int dest, double w) {
        // todo what if src OR dest doesnt exist?
        // Assuming @src and @dest already exists in the graph
        // Assuming there is no exiting edge from src to dest

        this.incrementMC();
        int id = GraphUtils.nodesToEdgeId(src, dest);
        EdgeData edgeData = new GraphEdgeData(id, src, dest, w);
        edges.put(id, edgeData);

        // todo putting it all inside a single private method - so no duplicate code will be here

        Set<EdgeData> connectedEdgesFromSrc = srcToEdges.get(src);
        if (connectedEdgesFromSrc != null){
            connectedEdgesFromSrc.add(edgeData);
        }else {
            connectedEdgesFromSrc = new HashSet<EdgeData>();
            connectedEdgesFromSrc.add(edgeData);
            srcToEdges.put(src, connectedEdgesFromSrc);
        }


        Set<EdgeData> connectedEdgesToDest = destToEdges.get(dest);
        if (connectedEdgesToDest != null){
            connectedEdgesToDest.add(edgeData);
        }else {
            connectedEdgesToDest = new HashSet<EdgeData>();
            connectedEdgesToDest.add(edgeData);
            destToEdges.put(src, connectedEdgesToDest);
        }
    }

    private void incrementMC() {
        this.MC += 1;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return edges.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return srcToEdges.get(node_id).iterator();
    }

    @Override
    public NodeData removeNode(int key) {   // What if they'll call removeNode() before removing all of the edged it is a member of??
        this.incrementMC();
        NodeData node = nodes.get(key);

        nodes.remove(key);
        srcToEdges.remove(key);
        destToEdges.remove(key);

        return node;
    }
    @Override
    public EdgeData removeEdge(int src, int dest) {
        this.incrementMC();

        int id = GraphUtils.nodesToEdgeId(src, dest);

        EdgeData edge = edges.get(id);
        edges.remove(id);

        // Remove edge from @srcToEdges
        Set<EdgeData> edgesFromSrc  = srcToEdges.get(src);
        edgesFromSrc.remove(edge);

        // Remove edge from @destToEdges
        Set<EdgeData> edgesFromDest = destToEdges.get(dest);
        edgesFromDest.remove(edge);

        return edge;
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int getMC() {
        return MC;
    }



    public void print(){
        for (int nodeId : this.srcToEdges.keySet())
        {
            // search  for value
            Set<EdgeData> edges = srcToEdges.get(nodeId);
            System.out.println("nodeId = " + nodeId );
            edges.forEach(e -> System.out.println("          --> " + e.getDest()));

        }

    }

    public Map<Integer, NodeData> getNodes() {
        return nodes;
    }

    public Map<Integer, EdgeData> getEdges() {
        return edges;
    }

    public static void main(String[] args) {
//         Map<Integer, NodeData> nodes = new HashMap<>();               //  Node-ID --> NodeData
//         Map<Integer, EdgeData> edges = new HashMap<>();               //   Edge-ID --> edge
//
//        nodes.put(1, new GraphNodeData(1, new GraphGeoLocation(0.0, 0.0, 0.0),0));
//        nodes.put(2, new GraphNodeData(2, new GraphGeoLocation(0.0, 1.0, 0.0),0));
//        nodes.put(3, new GraphNodeData(3, new GraphGeoLocation(1.0, 0.0, 0.0),0));
//        nodes.put(4, new GraphNodeData(4, new GraphGeoLocation(1.0, 1.0, 0.0),0));
//
//        edges.put(new GraphEdgeData(234234, 1, 2, 0.0));
//        edges.put(new GraphEdgeData(345345, 2, 3, 0.0));
//        edges.put(new GraphEdgeData(456454, 3, 4, 0.0));
//        edges.put(new GraphEdgeData(678686, 4, 1, 0.0));
//
//
//        DirectedWeightedGraph d1 = new DirectedWeightedGraphImpl(nodes,edges);
//
//        d1.connect(1,4, 99.0);
//        d1.connect(2,4, 99.0);
//        d1.removeEdge(3,4);
//        d1.print();
    }
}
