import java.util.Arrays;

public class BellmanFordAlgorithm {
    
    class Edge {
        int src;
        int dest;
        int weight;

        Edge(){
            src = 0;
            dest = 0;
            weight = 0;
        }

        void addEdge(int src, int dest, int weight){
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        int getSrc(){
            return this.src;
        }

        int getDest(){
            return this.dest;
        }

        int getWeight(){
            return this.weight;
        }
    }


    int total_vertices;
    int total_edges;
    Edge[] edges;

    BellmanFordAlgorithm(int total_vertices, int total_edges){
        // gerenarate graph
        this.total_vertices = total_vertices;
        this.total_edges = total_edges;

        edges = new Edge[total_edges];
        for (int i = 0; i < total_edges; i++) {
            edges[i] = new Edge();
        }
    }

    
    void findShortestPath(int source){
        int[] pathDistanceFromSource = new int[total_vertices];
        Arrays.fill(pathDistanceFromSource, Integer.MAX_VALUE);

        // distance of self-loop = 0
        pathDistanceFromSource[source] = 0;

        boolean isRelaxedInItr = false;

        for(int i=0; i<total_vertices; i++){

            if(i != 0){
                if(!isRelaxedInItr)
                    break;
            }
            isRelaxedInItr = false;

            for (int j = 0; j < total_edges; j++) {
                int src = edges[j].getSrc();
                int dest = edges[j].getDest();
                int weight = edges[j].getWeight();


                // relaxing vertex
                if(pathDistanceFromSource[src] != Integer.MAX_VALUE 
                    && pathDistanceFromSource[dest] > (pathDistanceFromSource[src] + weight)){
                        isRelaxedInItr = true;
                        pathDistanceFromSource[dest] = pathDistanceFromSource[src] + weight;
                }
            }

            if(i == total_vertices-1){
                System.out.println("This graph contains -ve weight cycle");
                return;
            }
        }

        System.out.println("Path distance are as follows : ");
        for (int i = 0; i < total_vertices; i++) {
            System.out.println(String.format("src: %s  dest: %s shortestPathDistance: %s", source, i, pathDistanceFromSource[i]));
            
        }

    }

    public static void main(String[] args) {
        BellmanFordAlgorithm graph = new BellmanFordAlgorithm(5, 8);

        graph.edges[0].addEdge(0, 1, -1);
        graph.edges[1].addEdge(0, 2, 4);
        graph.edges[2].addEdge(1, 2, 3);
        graph.edges[3].addEdge(1, 3, 2);
        graph.edges[4].addEdge(1, 4, 2);
        graph.edges[5].addEdge(3, 2, 5);
        graph.edges[6].addEdge(3, 1, 1);
        graph.edges[7].addEdge(4, 3, -3);

        graph.findShortestPath(0);

    }

}
