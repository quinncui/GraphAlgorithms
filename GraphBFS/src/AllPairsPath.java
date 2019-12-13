public class AllPairsPath {

    private Graph graph;
    private SingleSourcePath[] paths;

    public AllPairsPath(Graph graph){

        this.graph = graph;

        paths = new SingleSourcePath[graph.getV()];
        for(int v = 0; v < graph.getV(); v ++) {
            paths[v] = new SingleSourcePath(graph, v);
        }
    }

    public boolean isConnectedTo(int s, int t){
        graph.validateVertex(s);
        return paths[s].isConnectedTo(t);
    }

    public Iterable<Integer> path(int s, int t){
        graph.validateVertex(s);
        return paths[s].path(t);
    }

    public static void main(String[] args) {
        Graph graph = new Graph("GraphBFS/graph.txt");
        AllPairsPath allPairsPath = new AllPairsPath(graph);
        for (int i = 0; i < allPairsPath.paths.length; i++) {
            System.out.println(allPairsPath.paths[i].path(6));
        }
    }
}
