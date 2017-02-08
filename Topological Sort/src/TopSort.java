/**
 * Created by Chandan Suri on 2/8/2017.
 */
import java.util.*;

class Graph
{
    private int num_vertices;
    private LinkedList<Integer> adj[];

    Graph(int v)
    {
        num_vertices = v;
        adj = new LinkedList[num_vertices];
        for (int i=0;i<v;i++)
            adj[i] = new LinkedList<>();
    }

    public void addEdge(int v1, int v2)
    {
        adj[v1].add(v2);
    }

    public void topologicalSort()
    {
        Stack stack = new Stack();

        boolean visited[] = new boolean[num_vertices];
        for (int i = 0;i<num_vertices;i++)
            visited[i] = false;

        for (int i = 0;i<num_vertices;i++)
            if (!visited[i])
                topSortUtil(i, visited, stack);

        while (!stack.isEmpty())
            System.out.print(stack.pop()+" ");
    }

    private void topSortUtil(int vertex, boolean visited[], Stack stack)
    {
        visited[vertex] = true;
        Integer i;

        Iterator<Integer> iterator = adj[vertex].iterator();
        while (iterator.hasNext())
        {
            i = iterator.next();
            if (!visited[i])
                topSortUtil(i, visited, stack);
        }
        stack.push(new Integer(vertex));
    }
}

public class TopSort {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        System.out.print("Please Enter the number of vertices in the Graph => ");
        int n = d.nextInt();

        Graph  graph = new Graph(n);
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
        System.out.print("After Topological Sorting => \n");
        graph.topologicalSort();
        System.out.print("Ending....\n");
        d.close();
    }
}
