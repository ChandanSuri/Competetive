import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Chandan Suri on 2/8/2017.
 */

class Graph
{
    private int num_vertices;
    private int[][] adj_matrix;
    private int color[];
    public Graph(int v)
    {
        num_vertices = v;
        adj_matrix = new int[num_vertices][num_vertices];
        color = new int[num_vertices];
        for (int i=0;i<v;i++)
        {
            for (int j=0;j<v;j++)
            {
                adj_matrix[i][j] = 0;
            }
        }
    }

    public void addEdge(int v1, int v2)
    {
        adj_matrix[v1][v2] = 1;
    }

    public boolean isBipartite()
    {
        for (int i=0;i<num_vertices;i++)
            color[i] = 0;
        for (int i=0;i<num_vertices;i++)
            if (color[i] == 0)
                if (!isBipartiteUtil(i, color))
                    return false;
        return true;
    }

    private boolean isBipartiteUtil(int v, int color[])
    {
        color[v] = 1;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.push(v);

        while (!queue.isEmpty())
        {
            int ele = queue.poll();
            int col = color[ele];
            for (int vertex = 0;vertex < v;vertex++)
            {
                if (adj_matrix[ele][vertex]== 1 && color[vertex]==0)
                {
                    if (col==2)
                        color[vertex] = 1;
                    else
                        color[vertex] = 2;
                    queue.push(vertex);
                }
                else if (adj_matrix[ele][vertex]== 1 && color[vertex]==col)
                    return false;
            }
        }
        return true;
    }
}

public class BipGraphsImp {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        System.out.print("Please Enter the number of vertices => ");
        int num = d.nextInt();
        Graph graph = new Graph(num);
        System.out.print("Please add the edges....\n");
        while (true)
        {
            System.out.print("Please enter Y to input edge else N to exit inputting...\n");
            char  ch = d.next().charAt(0);
            if (ch == 'N')
                break;
            int v1 = d.nextInt();
            int v2 = d.nextInt();
            graph.addEdge(v1, v2);
        }

        System.out.print("The Graph is Bipartite => ");
        if (graph.isBipartite())
            System.out.print("Yes\n");
        else
            System.out.print("No\n");
        System.out.print("Ending...\n");

    }
}
