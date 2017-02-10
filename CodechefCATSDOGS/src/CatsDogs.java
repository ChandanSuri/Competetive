import java.util.Scanner;

/**
 * Created by Chandan Suri on 2/10/2017.
 */
public class CatsDogs {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        int t = d.nextInt();
        while (t!=0){
            t--;
            int cats = d.nextInt();
            int dogs = d.nextInt();
            int legs = d.nextInt();
            int max_cats_on_ride = 2*dogs;
            int left_cats = max_cats_on_ride-cats;
            boolean more_cats = left_cats>0;
            int max_legs = cats*4 + dogs*4;
            int min_legs = dogs*4;
            if (!more_cats){
                min_legs += (-1*left_cats)*4;
            }
            if (legs >= min_legs && legs<=max_legs && legs%4==0){
                System.out.println("yes");
            }else {
                System.out.println("no");
            }
        }
    }
}
