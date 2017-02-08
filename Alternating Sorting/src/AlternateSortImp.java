/**
 * Created by Chandan Suri on 2/9/2017.
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class AlternateSortImp {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        System.out.print("Please enter the size of array => ");
        int num = d.nextInt();
        Integer arr[] = new Integer[num];
        System.out.print("Please Enter the elements => \n");
        for (int i=0;i<num;i++)
        {
            arr[i] = d.nextInt();
        }
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(arr));
        alternateSort(linkedList);
    }

    public static void alternateSort(LinkedList<Integer> list)
    {
        Collections.sort(list);
        for (int i=1;i<(list.size()+1)/2;i++)
        {
            Integer x = list.getLast();
            list.removeLast();
            list.add(2*i-1, x);
        }
        System.out.println(list);
    }
}
