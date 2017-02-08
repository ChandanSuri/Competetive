/**
 * Created by Chandan Suri on 2/3/2017.
 */
import java.util.*;

class Graph
{
    private int numVertices;
    private LinkedList<Integer> adj[];
    private boolean visited[];
    public Graph(int num)
    {
        numVertices = num;
        adj = new LinkedList[numVertices];
        visited = new boolean[numVertices];
        for (int i=0;i<numVertices;i++) {
            adj[i] = new LinkedList<>();
            visited[i] = false;
        }
    }

    public void addEdge(int v1, int v2){
        adj[v1].add(v2);
    }

    public void DFS()
    {
        for (int vertex=0;vertex<numVertices;vertex++){
            if (!visited[vertex])
                DFSUtil(vertex);
        }
    }

    private void DFSUtil(int v)
    {
        visited[v] = true;
        System.out.print(v+" ");

        Iterator<Integer> iterator = adj[v].listIterator();
        while (iterator.hasNext()){
            v = iterator.next();
            if(!visited[v])
                DFSUtil(v);
        }
    }
}

public class DFSGraph {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        System.out.print("Please Enter the number of nodes in Graph => ");
        int n = d.nextInt();

        Graph graph = new Graph(n);
        while (true){
            System.out.print("Please Enter an edge,i.e., 2 vertices (Enter -1 to exit input) => ");
            int v1 = d.nextInt();
            if(v1==-1)
                break;
            int v2 = d.nextInt();
            graph.addEdge(v1, v2);
        }
        System.out.print("After Depth First Traversal => \n");
        graph.DFS();
        System.out.print("Ending.......\n");

    }
}




















