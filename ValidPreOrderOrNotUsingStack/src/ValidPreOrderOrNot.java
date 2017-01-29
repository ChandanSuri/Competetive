/**
 * Created by Chandan Suri on 1/27/2017.
 */
import java.util.*;
//O(n) Complexity....
class BinarySearchTree{

    public boolean canRepresentBST(int pre[], int n){
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MIN_VALUE;

        for(int i = 0;i<n;i++){
            if(pre[i]<root){
                return false;
            }
            while (!stack.empty() && stack.peek()<pre[i]){
                root = stack.peek();
                stack.pop();
            }
            stack.push(pre[i]);
        }
        return true;
    }
}

public class ValidPreOrderOrNot {

    public static void main(String args[]){

        Scanner d = new Scanner(System.in);
        BinarySearchTree BST = new BinarySearchTree();
        System.out.print("Enter the number of elements you want in the preorder => ");
        int n = d.nextInt();
        System.out.print("Enter the Elements to checked as preorder of a BST => \n");
        int pre[] = new int[n];
        for(int i =0;i<n;i++){
            pre[i] = d.nextInt();
        }

        boolean can_rep = BST.canRepresentBST(pre, n);
        if(can_rep)
            System.out.print("Yes! It can represent the preorder of a BST.\n");
        else
            System.out.print("No! It cannot represent the preorder of a BST.\n");

        System.out.print("Ending.....\n");
    }
}
