/**
 * Created by Chandan Suri on 1/27/2017.
 */
import java.util.*;

class Queue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public Queue(){

        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void insert(int data){

        if(stack1.size()==0)
            stack1.push(data);
        else {
            int len = stack1.size();
            for(int i=0;i<len;i++){
                stack2.push(stack1.pop());
            }
            stack1.push(data);
            for(int i=0;i<len;i++){
                stack1.push(stack2.pop());
            }
        }
    }

    public int remove(){
        if(stack1.size()==0)
            throw new NoSuchElementException("Underflow Exception");
        return stack1.pop();
    }

    public int peek(){
        if(stack1.size()==0)
            throw new NoSuchElementException("Underflow Exception");
        return stack1.peek();
    }

    public boolean isEmpty(){
        return stack1.size()==0;
    }

    public int getSize(){
        return stack1.size();
    }

    public void display(){
        System.out.print("\nQueue => \n");
        int len = getSize();
        if(len==0)
            System.out.print("It's Empty!!\n");
        else
        {
            for(int i=len-1;i>=0;i--){
                System.out.print(stack1.get(i)+" ");
            }
            System.out.println();
        }
    }
}


public class ImplementQueue {

    public static void main(String args[]){

        Scanner d = new Scanner(System.in);
        Queue queue = new Queue();

        while(true) {
            System.out.print("Please Enter a valid choice => \n" +
                    "1. Insert an element in the Queue.\n" +
                    "2. Delete an element from the Queue.\n" +
                    "3. See the front most element in the Queue.\n" +
                    "4. Check if the Queue is empty.\n" +
                    "5. Display the current queue size.\n" +
                    "Enter 0 if you want to quit.......\n");

            int choice = d.nextInt();
            boolean flag_to_exit = false;
            switch (choice) {
                case 0:
                    flag_to_exit = true;
                    break;
                case 1:
                    System.out.print("Enter an element to insert => ");
                    int num = d.nextInt();
                    queue.insert(num);
                    break;
                case 2:
                    int del_ele = queue.remove();
                    System.out.print("The Removed element is => "+ del_ele+"\n");
                    break;
                case 3:
                    System.out.print("The Front Element is => "+queue.peek()+"\n");
                    break;
                case 4:
                    if(queue.isEmpty())
                        System.out.print("The Queue is Empty!!!\n");
                    else
                        System.out.print("The Queue is not Empty!!!\n");
                    break;
                case 5:
                    System.out.print("The Size of the Queue is => "+queue.getSize());
                    break;
            }
            queue.display();
            if(flag_to_exit)
                break;
        }
        System.out.print("Ending...\n");
    }
}























