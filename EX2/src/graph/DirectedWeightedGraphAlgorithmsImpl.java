package graph;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import utils.GraphUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DirectedWeightedGraphAlgorithmsImpl implements DirectedWeightedGraphAlgorithms {
    DirectedWeightedGraph graph;

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {

        // Duplicate edges
        Iterator<EdgeData> eIterator = this.graph.edgeIter();
        Map<Integer, EdgeData> myEdges = new HashMap<>();
        while (eIterator.hasNext()){
            EdgeData nextEdge = eIterator.next();

            int id = GraphUtils.nodesToEdgeId(nextEdge.getSrc(), nextEdge.getDest());
            EdgeData newEdge = new GraphEdgeData(id, nextEdge.getSrc(), nextEdge.getSrc(), nextEdge.getWeight());

            myEdges.put(id, newEdge);
        }


        // Duplicate nodes
        Iterator<NodeData> nIterator = this.graph.nodeIter();
        Map<Integer, NodeData> myNodes = new HashMap<>();
        while (nIterator.hasNext()){
            NodeData nextNode = nIterator.next();

            GraphGeoLocation location = new GraphGeoLocation(
                    nextNode.getLocation().x(),
                    nextNode.getLocation().y(),
                    nextNode.getLocation().z()
            );
            NodeData newNode = new GraphNodeData(nextNode.getKey(), location, nextNode.getWeight());

            myNodes.put(nextNode.getKey(), newNode);
        }


        return new DirectedWeightedGraphImpl(myNodes, myEdges);
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
