import java.util.*;

/**
 * Created by Chandan Suri on 2/9/2017.
 */

public class ThreesMultiples {

    private static int top = 0;
    private static Integer new_arr[];

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        System.out.print("Enter the no. of numbers => ");
        int num = d.nextInt();
        int[] arr = new int[num];
        new_arr = new Integer[num];
        System.out.print("Enter the digits => \n");
        for (int i=0;i<num;i++)
        {
            arr[i] = d.nextInt();
        }
        findMaxMultipleOf3(arr, num);
        if (top==0)
            System.out.print("Not Possible!!\n");

    }

    public static void findMaxMultipleOf3(int arr[], int n)
    {
        Arrays.sort(arr,0, n);
        Queue<Integer> queue0 = new LinkedList<>();
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        int sum=0;

        for (int i=0;i<n;i++)
        {
            sum += arr[i];
            if (arr[i]%3==0)
                queue0.add(arr[i]);
            else if (arr[i]%3==1)
                queue1.add(arr[i]);
            else
                queue2.add(arr[i]);
        }

        if (sum%3==1)
        {
            if (!queue1.isEmpty())
                queue1.remove();
            else {
                if (!queue2.isEmpty())
                    queue2.remove();
                else
                    return;
                if (!queue2.isEmpty())
                    queue2.remove();
                else
                    return;
            }
        }
        else if (sum%3==2)
        {
            if (!queue2.isEmpty())
                queue2.remove();
            else {
                if (!queue1.isEmpty())
                    queue1.remove();
                else
                    return;
                if (!queue1.isEmpty())
                    queue1.remove();
                else
                    return;
            }
        }

        populateAux(queue0, queue1, queue2);
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        Arrays.sort(new_arr,0, top, comparator);
        printArr();
    }

    private static void populateAux(Queue<Integer> queue0,
                                    Queue<Integer> queue1, Queue<Integer> queue2)
    {
        while (!queue0.isEmpty())
            new_arr[top++] = queue0.remove();

        while (!queue1.isEmpty())
            new_arr[top++] = queue1.remove();

        while (!queue2.isEmpty())
            new_arr[top++] = queue2.remove();

    }

    private static void printArr()
    {
        for (int i=0;i<top;i++)
            System.out.print(new_arr[i]+" ");
        System.out.println();
    }
}
