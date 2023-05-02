import java.util.Arrays;

class DijktrasAlgorithm {

    int findMinDistantUnvistedVertex(int total_vertices, boolean[] visited, int[] pathDistance){

        int min_dist_vertex = -1;
        int min_dist = Integer.MAX_VALUE;

        for(int i=0; i<total_vertices; i++){
            if(!visited[i] && pathDistance[i] < min_dist){
                min_dist = pathDistance[i];
                min_dist_vertex = i;
            }
        }
        
        return min_dist_vertex;
    }


    void dijktra(int[][] graph, int source){
        
        int total_vertices = graph.length;
        // to avoid relaxing already visited vertex
        boolean[] visited = new boolean[total_vertices];
        // to maintain the distance of path from source to vertex v
        int[] pathDistance = new int[total_vertices];

        Arrays.fill(visited, false);
        Arrays.fill(pathDistance, Integer.MAX_VALUE);

        // path distance to self is 0
        pathDistance[source] = 0;
        
        // get the vertex that is not visited yet and closest to the source.
        // note, in the very first iteration, findMinDistantVertex will return the source vertex 
        // as it will have smallest path distance i.e, 0 and other all will have distance as Integer.MAX_VALUE
        for(int i=0; i<total_vertices; i++){
            // taking this for loop n times as we will be visiting every vertex
            int vertex = findMinDistantUnvistedVertex(total_vertices, visited, pathDistance);
            // marking the vertex as visited
            visited[vertex] = true;
            System.out.println("vertex : " + vertex);

            // trying to relax neighbouring vertices
            for(int j=0; j<total_vertices; j++){
                // if there is a edge
                if(graph[vertex][j] != 0){
                    int new_dist = pathDistance[vertex] + graph[vertex][j];
                
                    if(!visited[j] && new_dist < pathDistance[j]){
                        // relaxing vertex
                        pathDistance[j] = new_dist;
                    }
                }
            }

            System.out.println(String.format("Shortest distance between source: %s and dest: %s is %s", source, vertex, pathDistance[vertex]));
            System.out.println(Arrays.toString(pathDistance));
        }

    }


    public static void main(String[] args){

        int graph[][] = new int[][] { 
            { 0, 0, 1, 2, 0, 0, 0 }, 
            { 0, 0, 2, 0, 0, 3, 0 }, 
            { 1, 2, 0, 1, 3, 0, 0 },
            { 2, 0, 1, 0, 0, 0, 1 }, 
            { 0, 0, 3, 0, 0, 2, 0 }, 
            { 0, 3, 0, 0, 2, 0, 1 }, 
            { 0, 0, 0, 1, 0, 1, 0 } };
        
        (new DijktrasAlgorithm()).dijktra(graph, 0);

    }

}