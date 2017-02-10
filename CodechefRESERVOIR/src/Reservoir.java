import java.util.Scanner;

/**
 * Created by Chandan Suri on 2/10/2017.
 */
public class Reservoir {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        int t = d.nextInt();
        while (t!=0)
        {
            t--;
            int n = d.nextInt();
            int m = d.nextInt();
            char reservoir[][] = new char[n][m];
            for (int i=0;i<n;i++)
            {
                String str = d.next();
                for (int j=0;j<m;j++)
                {
                    reservoir[i][j] = str.charAt(j);
                }
            }
            //LOGIC
            boolean not_stable = false;
            for (int i=0;i<n;i++)
            {
                for (int j=0;j<m;j++)
                {
                    if (isValid(i+1,j,n,m) && reservoir[i][j]=='B'
                            && (reservoir[i+1][j]=='A' || reservoir[i+1][j]=='W'))
                    {
                        not_stable = true;
                        break;
                    }else if(isValid(i+1,j,n,m) && isValid(i,j-1,n,m) && isValid(i,j+1,n,m)
                            && reservoir[i][j]=='W'
                            && (reservoir[i+1][j]=='A' || reservoir[i][j-1]=='A' || reservoir[i][j+1]=='A'))
                    {
                        not_stable = true;
                        break;
                    }else if ((j==0 || j==m-1) && reservoir[i][j]=='W')
                    {
                        not_stable = true;
                        break;
                    }
                }
                if (not_stable)
                    break;
            }
            if (not_stable)
                System.out.println("no");
            else
                System.out.println("yes");
        }
    }

    public static boolean isValid(int r, int c, int n, int m)
    {
        return r>=0 && r<n && c>=0 && c<m;
    }
}
