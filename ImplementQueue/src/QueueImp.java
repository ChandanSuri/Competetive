/**
 * Created by Chandan Suri on 1/27/2017.
 */
import java.util.*;
class Queue
{
    protected int[] data;
    protected int front, rear, size, curr_size;

    public Queue(int n){
        size = n;
        curr_size = 0;
        data = new int[size];
        front = -1;
        rear = -1;
    }

    public boolean isEmpty(){
        return front == -1;
    }

    public boolean isFull(){
        return front==0 && rear==size-1;
    }

    public int getSize()
    {
        return curr_size;
    }

    public int peek()
    {
        if(isEmpty())
            throw new NoSuchElementException("Underflow Exception");
        return data[front];
    }

    public void insert(int d){

        if(rear==-1)
        {
            front = 0;
            rear = 0;
            data[rear] = d;
        }else if(rear+1>=size)
            throw new IndexOutOfBoundsException("Overflow Exception");
        else if(rear+1<size)
            data[++rear] = d;
        curr_size++;
    }

    public int remove()
    {
        if(isEmpty())
            throw new NoSuchElementException("Underflow Exception");
        else
        {
            curr_size--;
            int ele = data[front];
            if(front==rear)
            {
                front = -1;
                rear = -1;
            }else
                front++;
            return ele;
        }
    }

    public void display()
    {
        System.out.print("\nQueue => ");
        if(curr_size == 0)
        {
            System.out.print("It's Empty!!!\n");
            return;
        }
        for(int i=front;i<=rear;i++)
            System.out.print(data[i]+" ");
        System.out.println();
    }
}

public class QueueImp {

    public static void main(String args[]){

        Scanner d = new Scanner(System.in);
        System.out.print("Enter the maximum size limit of Queue => ");
        int n = d.nextInt();
        Queue queue = new Queue(n);
        while(true) {
            System.out.print("Please Enter a valid choice => \n" +
                    "1. Insert an element in the Queue.\n" +
                    "2. Delete an element from the Queue.\n" +
                    "3. See the front most element in the Queue.\n" +
                    "4. Check if the Queue is Full.\n" +
                    "5. Check if the Queue is empty.\n" +
                    "6. Display the current queue size.\n" +
                    "Enter 0 if you want to quit.......\n");
            int choice = d.nextInt();
            boolean flag_to_exit = false;
            switch (choice){
                case 0:
                    flag_to_exit = true;
                    break;
                case 1:
                    System.out.print("Enter the element to be inserted => ");
                    int num = d.nextInt();
                    queue.insert(num);
                    break;
                case 2:
                    System.out.print("Removing the element (Front)...\n");
                    int r_ele = queue.remove();
                    System.out.print("The element that has been removed is => "+r_ele+"\n");
                    break;
                case 3:
                    int front_ele = queue.peek();
                    System.out.print("The Peek Element is => "+front_ele+"\n");
                    break;
                case 4:
                    if(queue.isFull())
                        System.out.print("The Queue is Full!!!\n");
                    else
                        System.out.print("The Queue is not Full!!!\n");
                    break;
                case 5:
                    if(queue.isEmpty())
                        System.out.print("The Queue is Empty...\n");
                    else
                        System.out.print("The Queue is not Empty....\n");
                    break;
                case 6:
                    System.out.print("The Current Size of the Queue is => "+queue.getSize());
                    break;
            }
            queue.display();
            if(flag_to_exit)
                break;
        }
        System.out.print("Ending.........\n");
    }
}
