import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Chandan Suri on 2/8/2017.
 */
class Graph
{
    private int num_vertices;
    private LinkedList<Integer> adj_list[];

    public Graph(int v)
    {
        num_vertices = v;
        adj_list = new LinkedList[num_vertices];
        for (int i=0;i<num_vertices;i++)
            adj_list[i] = new LinkedList<>();
    }

    public void addEdge(int v1, int v2)
    {
        adj_list[v1].add(v2);
    }

    public void printSCC()
    {
        Stack stack = new Stack();
        boolean visited[] = new boolean[num_vertices];
        for (int i=0;i<num_vertices;i++)
            visited[i] = false;
        for (int i =0;i<num_vertices;i++)
            if (!visited[i])
                topSort(i, visited, stack);
        Graph rev_graph = getTranspose();
        for (int i=0;i<num_vertices;i++)
            visited[i] = false;
        while (!stack.isEmpty())
        {
            int vertex = (int)stack.pop();
            if (!visited[vertex]) {
                rev_graph.DFSUtil(vertex, visited);
                System.out.println();
            }
        }
    }

    private void topSort(int vertex, boolean visited[], Stack stack)
    {
        visited[vertex] = true;
        Iterator<Integer> iterator = adj_list[vertex].iterator();
        while (iterator.hasNext())
        {
            int n = iterator.next();
            if (!visited[n])
                topSort(n, visited, stack);
        }
        stack.push(new Integer(vertex));
    }

    private Graph getTranspose()
    {
        Graph rev_graph = new Graph(num_vertices);
        for (int i=0;i<num_vertices;i++)
        {
            Iterator<Integer> iterator = adj_list[i].iterator();
            while (iterator.hasNext())
                rev_graph.adj_list[iterator.next()].add(i);
        }
        return rev_graph;
    }

    private void DFSUtil(int v, boolean visited[])
    {
        visited[v] = true;
        System.out.print(v+" ");

        Iterator<Integer> iterator = adj_list[v].iterator();
        while (iterator.hasNext())
        {
            int n = iterator.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
}

public class StrongGraphCheck {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        System.out.print("Please Enter number of vertices in Graph => ");
        int num = d.nextInt();
        Graph graph = new Graph(num);

        while (true)
        {
            System.out.print("Do you want to enter more edges => Press any key for yes, N for No");
            char ch = d.next().charAt(0);
            if (ch == 'N')
                break;
            int v1 = d.nextInt();
            int v2 = d.nextInt();
            graph.addEdge(v1, v2);
        }
        System.out.print("The Strongly Connected Components Are => \n");
        graph.printSCC();
        System.out.print("Ending...\n");
    }
}
