/**
 * Created by Chandan Suri on 1/19/2017.
 */
import java.util.Scanner;

public class BalancedParen
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number stating number of pairs of balanced parentheses=> ");
        int num_pairs;
        num_pairs = sc.nextInt();
        balParen(num_pairs, num_pairs, "");
        System.out.println("Ending...");
    }

    private static void balParen(int open_paren, int close_paren, String str) {

        if (open_paren == 0 && close_paren == 0) {
            System.out.println(str);
        }
        if (open_paren > close_paren) {
            return ;
        }

        if (open_paren > 0) {
            balParen(open_paren - 1, close_paren, str + "{");
        }
        if (close_paren > 0) {
            balParen(open_paren, close_paren - 1, str + "}");
        }


    }

}
