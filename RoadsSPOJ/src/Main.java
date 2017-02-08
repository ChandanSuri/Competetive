/**
 * Created by Chandan Suri on 2/3/2017.
 */

import java.io.IOException;
import java.util.*;

class Pair
{
    int final_total_dist;
    int final_total_toll;

    public Pair(int d, int t)
    {
        final_total_dist = d;
        final_total_toll = t;
    }
}

class Road
{
    int destination;
    int road_length;
    int toll;

    public Road(int d, int l, int t){
        destination = d;
        road_length = l;
        toll = t;
    }
}

class Graph
{
    private int numVertices;
    private LinkedList<Road> adj[];

    public Graph(int num)
    {
        numVertices = num+1;
        adj = new LinkedList[numVertices];
        for (int i=1;i<numVertices;i++)
            adj[i] = new LinkedList<>();
    }

    public void addEdge(int v1, int v2, int len, int toll)
    {
            adj[v1].add(new Road(v2, len, toll));

    }

    public Pair findShortestPath(int src)
    {
        int dist[] = new int[numVertices];
        int total_toll[] = new int[numVertices];
        //boolean visited[] = new boolean[numVertices];

        for (int i=1;i<numVertices;i++) {
            total_toll[i] = Integer.MAX_VALUE;
            //visited[i] = false;
        }
        dist[src] = 0;
        total_toll[src] = 0;
        for (int count=1;count<numVertices-1;count++){
            int city = minToll(total_toll);
            //visited[city] = true;
            if (city==numVertices-1)
                break;
            Iterator<Road> iterator = adj[city].listIterator();
            while (iterator.hasNext()){
                Road road = iterator.next();
                int dest = road.destination;
                int road_length = road.road_length;
                int toll = road.toll;

                if(total_toll[city]!=Integer.MAX_VALUE && total_toll[city] + toll < total_toll[dest]) {
                    dist[dest] = dist[city] + road_length;
                    total_toll[dest] = total_toll[city] + toll;
                }
            }
        }

        return new Pair(dist[numVertices-1], total_toll[numVertices-1]);
    }

    private int minToll(int toll[])
    {
        int min_toll= Integer.MAX_VALUE;
        int min_index = 0;
        for (int v =1;v<numVertices;v++){
            if(toll[v] <= min_toll){
                min_toll = toll[v];
                min_index = v;
            }
        }
        return min_index;
    }

}

public class Main {

    public static void main(String[] args)throws IOException {

        Scanner d = new Scanner(System.in);
        int test_cases = d.nextInt();//number of test cases

        while (test_cases >0)
        {
            test_cases -= 1;
            int max_coins = d.nextInt();
            int num_cities = d.nextInt();
            int num_roads = d.nextInt();

            Graph graph = new Graph(num_cities);

            for (int i=0;i<num_roads;i++){
                int src = d.nextInt();
                int dest = d.nextInt();
                int rl = d.nextInt();
                int toll = d.nextInt();

                graph.addEdge(src, dest, rl, toll);
                //graph.addEdge(dest, src, rl, toll);
            }

            Pair pair = graph.findShortestPath(1);
            if (pair.final_total_toll <= max_coins){
                System.out.print(pair.final_total_dist+"\n");
            }else {
                System.out.print("-1\n");
            }
        }
    }
}
















