import java.util.Scanner;

/**
 * Created by Chandan Suri on 2/9/2017.
 */
class Islands
{
    private int row;
    private int col;
    private int adj_matrix[][];

    public Islands(int r, int c)
    {
        row = r;
        col = c;
        adj_matrix = new int[r][c];
        for (int i=0;i<r;i++)
            for (int j=0;j<c;j++)
                adj_matrix[i][j] = 0;
    }

    public void addEdge(int v1, int v2)
    {
        adj_matrix[v1][v2] = 1;
    }

    public int count()
    {
        boolean visited[][] = new boolean[row][col];
        int count = 0;
        for (int i=0;i<row;i++)
        {
            for (int j=0;j<col;j++)
            {
                if (adj_matrix[i][j]==1 && !visited[i][j])
                {
                    DFS(i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private void DFS(int r, int c, boolean visited[][])
    {
        int row_num[] = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int col_num[] = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

        visited[r][c] = true;

        for (int i=0;i<8;i++)
            if (isValid(r+row_num[i], c+col_num[i], visited))
                DFS(r+row_num[i], c+col_num[i], visited);
    }

    private boolean isValid(int r, int c, boolean visited[][])
    {
        return (r>=0)&&(c>=0)&&(r<row)&&(c<col)&&(adj_matrix[r][c]==1)&&(!visited[r][c]);
    }
}

public class countIslands {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        System.out.print("Please Enter no. of rows => ");
        int rows = d.nextInt();
        System.out.print("Please Enter no. if columns => ");
        int cols = d.nextInt();
        Islands islands = new Islands(rows, cols);

        while (true)
        {
            System.out.print("Please enter Y to input edge else N to exit inputting...\n");
            char  ch = d.next().charAt(0);
            if (ch == 'N')
                break;
            int v1 = d.nextInt();
            int v2 = d.nextInt();
            islands.addEdge(v1, v2);
        }

        int num_islands = islands.count();
        System.out.print("The No. of Islands are => "+num_islands+".\n");
        System.out.print("Ending...\n");
    }
}
