/**
 * Created by Chandan Suri on 1/27/2017.
 */
import java.util.*;

class MyStack
{
    private Stack<Integer> stack;
    private Integer min_ele;

    public MyStack(){
        stack = new Stack<>();
    }

    public void getMin()
    {
        if(stack.isEmpty())
            System.out.println("Stack is Empty!!");
        else
            System.out.print("The Minimum Element in the Stack is => "+min_ele+"\n");
    }

    public void peek()
    {
        if(stack.isEmpty()){
            System.out.print("Stack is Empty!!\n");
            return;
        }

        Integer top = stack.peek();
        System.out.print("The Top Most Element in the Stack is => ");
        //If top < minimum element then min ele stores the value of t
        if(top < min_ele)
            System.out.println(min_ele);
        else
            System.out.println(top);
    }

    public void pop()
    {
        if(stack.isEmpty()){
            System.out.print("Stack is Empty!!\n");
            return;
        }
        System.out.print("Top Most Element Removed => ");
        Integer top = stack.pop();
        if(top < min_ele){
            System.out.print(min_ele+"\n");
            min_ele = 2*min_ele - top;
        }else{
            System.out.println(top);
        }
    }

    public void push(Integer n){
        if(stack.isEmpty()){
            min_ele = n;
            stack.push(n);
            System.out.println("Number has been inserted => "+n);
            return;
        }
        if(n < min_ele){
            stack.push(2*n- min_ele);
            min_ele = n;
        }else{
            stack.push(n);
        }
        System.out.print("Number that has been inserted is => "+n+"\n");
    }
}

public class MinEleLessComplex {

    public static void main(String args[]){
        Scanner d = new Scanner(System.in);
        MyStack stack = new MyStack();
        System.out.print("Performing different stack operations.\n");
        while(true){
            System.out.print("Please enter appropriate choice => \n" +
                    "1. Push an element into stack.\n" +
                    "2. Pop an element from stack.\n" +
                    "3. Seek the Top most element from stack.\n" +
                    "4. Get Minimum Element of the stack.\n" +
                    "Enter 0 for ending the program....\n");
            int choice = d.nextInt();
            boolean flag_to_exit = false;
            switch (choice){
                case 0:
                    flag_to_exit = true;
                    break;
                case 1:
                    System.out.print("Enter the element that is to be inserted => ");
                    int num = d.nextInt();
                    stack.push(num);
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    stack.peek();
                    break;
                case 4:
                    stack.getMin();
                    break;
            }

            if(flag_to_exit)
                break;
        }
        System.out.print("Ending....\n");
    }
}
