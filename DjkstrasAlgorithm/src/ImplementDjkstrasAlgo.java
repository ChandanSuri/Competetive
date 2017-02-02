/**
 * Created by Chandan Suri on 2/2/2017.
 */
import java.util.*;

class ShortestPath
{
    public int numVertices;
    public ShortestPath(int num){
        numVertices = num;
    }
    public int minDistance(int dist[], boolean sptNodes[])
    {
        int min = Integer.MAX_VALUE;
        int min_ind = -1;

        for(int i=0;i<numVertices;i++){
            if (dist[i]<=min && !sptNodes[i]) {
                min = dist[i];
                min_ind = i;
            }
        }
        return min_ind;
    }
    public int[] djkstras(int graph[][], int source)
    {
        int dist[] = new int[numVertices];
        boolean sptNodes[] = new boolean[numVertices];

        for (int vertex=0;vertex<numVertices;vertex++){
            dist[vertex] = Integer.MAX_VALUE;
            sptNodes[vertex] = false;
        }

        dist[source] = 0;

        for(int i=0;i<numVertices-1;i++)
        {
            int v1 = minDistance(dist, sptNodes);

            sptNodes[v1] = true;
            for (int v2=0;v2<numVertices;v2++)
            {
                if (!sptNodes[v2] && dist[v1]!=Integer.MAX_VALUE
                        && graph[v1][v2]!=0 && dist[v1]+graph[v1][v2]<dist[v2]){
                    dist[v2] = graph[v1][v2] + dist[v1];
                }
            }
        }
        return dist;

    }

    public void printDistances(int dist[])
    {
        System.out.print("Distances from 0th Node(Source) to all the Nodes => \n");
        for (int v=0;v<numVertices;v++){
            System.out.print(v +" -> "+dist[v]+"\n");
        }
    }
}

public class ImplementDjkstrasAlgo {
    public static void main(String[] args) {
        Scanner d = new Scanner(System.in);

        System.out.print("Enter the number of nodes => ");
        int n = d.nextInt();
        ShortestPath path = new ShortestPath(n);
        System.out.print("Enter the adjacency matrix => \n");
        int[][] graph = new int[n][n];
        for (int i=0;i<n;i++)
        {
            System.out.print((i+1)+" Row => \n");
            for (int j=0;j<n;j++)
            {
                graph[i][j] = d.nextInt();
            }
        }
        int distances[] = path.djkstras(graph, 0);
        path.printDistances(distances);
        System.out.print("Ending...\n");

    }
}
