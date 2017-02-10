import java.util.*;

/**
 * Created by Chandan Suri on 2/10/2017.
 */
public class JourneyToTheMoon {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        int n = d.nextInt();
        int pairs = d.nextInt();

        final List<List<Integer>> companions = new ArrayList<>(n);
        for (int i=0;i<n;i++)
            companions.add(new ArrayList<>());

        for (int i=0;i<pairs;i++)
        {
            int A = d.nextInt();
            int B = d.nextInt();
            companions.get(A).add(B);
            companions.get(B).add(A);
        }

        boolean visited[] = new boolean[n];
        List<Integer> country_sizes = new ArrayList<>();
        for (int i=0;i<n;i++)
        {
            int c_size = 0;
            Queue<Integer> q = new ArrayDeque<>();
            q.add(i);
            while (!q.isEmpty())
            {
                int ast_id = q.poll();
                if (!visited[ast_id]){
                    ++c_size;
                    visited[ast_id] = true;
                    q.addAll(companions.get(ast_id));
                }
            }
            if (c_size>0){
                country_sizes.add(c_size);
            }
        }

        int num_pairs = 0;
        int num_partners = n;
        for (int c_size:country_sizes)
        {
            num_pairs += (c_size)*(num_partners-=c_size);
        }
        System.out.print(num_pairs);
    }
}
