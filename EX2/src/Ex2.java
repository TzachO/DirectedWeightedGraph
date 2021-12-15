import api.EdgeData;
import api.NodeData;
import graph.*;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import utils.GraphUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = null;
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        // ********************************
    }

    public static double foo()
    {
        return 2;
    }
    public static void main(String[] args)  {

        try{
//            final int FILE_ARGUMENT_LOCATION = 0;
//
//            Gson gson = new Gson();
//            String filePath = args[FILE_ARGUMENT_LOCATION];
//
//            Type nodeType = new TypeToken<ArrayList<GraphNodeData>>(){}.getType();
//            Type edgeType = new TypeToken<ArrayList<GraphEdgeData>>(){}.getType();
//
//            JsonParser parser = new JsonParser();
//            JsonObject input = (JsonObject) parser.parse(new FileReader(filePath));
//
//            String Nodes = input.get("Nodes").toString();
//            String Edges = input.get("Edges").toString();
//
//            List<GraphEdgeData> EdgesList = gson.fromJson(Edges, edgeType);
//            List<GraphNodeData> nodeList = gson.fromJson(Nodes, nodeType);

            List<GraphNodeData> nodeList = (List<GraphNodeData>) readInput("Nodes", args);
            List<GraphEdgeData> edgesList = (List<GraphEdgeData>) readInput("Edges", args);

            nodeList.forEach(n -> {
                final int X = 0;
                final int Y = 1;
                final int Z = 2;

                String[] positions = n.getPos().split(",");
                n.setLocation(new GraphGeoLocation(
                        Double.parseDouble(positions[X]),
                        Double.parseDouble(positions[Y]),
                        Double.parseDouble(positions[Z]))
                );
            });


            Map<Integer, NodeData> nodeMap = nodeList.stream().collect(Collectors.toMap(NodeData::getKey, Function.identity()));
            Map<Integer, EdgeData> edgesMap = edgesList.stream().collect(Collectors.toMap( e -> GraphUtils.nodesToEdgeId(e.getSrc(), e.getDest()), Function.identity()));

            DirectedWeightedGraph graph = new DirectedWeightedGraphImpl(nodeMap, edgesMap);

            DirectedWeightedGraphAlgorithms algoRunner = new DirectedWeightedGraphAlgorithmsImpl();
            ((DirectedWeightedGraphAlgorithmsImpl)algoRunner).init(graph);
            DirectedWeightedGraph duplicatedGraph = algoRunner.copy();


        }catch (FileNotFoundException ex){
            System.out.println("File not found!");
        }
    }

    private static Object readInput(String inputName, String[] args) throws FileNotFoundException {
        final int FILE_ARGUMENT_LOCATION = 0;

        Gson gson = new Gson();
        String filePath = args[FILE_ARGUMENT_LOCATION];

        Type dataType;
        if (inputName.equals("Edges")){
            dataType = new TypeToken<ArrayList<GraphEdgeData>>(){}.getType();
        }
        else {
            // Equals to 'Nodes'
            dataType = new TypeToken<ArrayList<GraphNodeData>>(){}.getType();
        }

        JsonParser parser = new JsonParser();
        JsonObject input = (JsonObject) parser.parse(new FileReader(filePath));

        String objects = input.get(inputName).toString();

        return gson.fromJson(objects, dataType);
    }
}