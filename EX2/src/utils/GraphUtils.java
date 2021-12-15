package utils;

public class GraphUtils {

    /**
     * Translate from 2 node ids into the related edge id.
     * @param nodeId1 - source
     * @param nodeId2 - dest
     * @return
     */
    public static int nodesToEdgeId(int nodeId1, int nodeId2){
        return Integer.parseInt(Integer.toString(nodeId1) + Integer.toString(nodeId2));
    }
}
