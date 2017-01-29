/**
 * Created by Chandan Suri on 1/29/2017.
 */
import java.util.*;

class Queue
{
    private String[] data;
    private int front, rear, size, curr_size;

    public Queue(int n){
        size = n;
        curr_size = 0;
        data = new String[size];
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

    public String peek()
    {
        if(isEmpty())
            throw new NoSuchElementException("Underflow Exception");
        return data[front];
    }

    public void insert(String d){

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

    public String remove()
    {
        if(isEmpty())
            throw new NoSuchElementException("Underflow Exception");
        else
        {
            curr_size--;
            String ele = data[front];
            if(front==rear)
            {
                front = -1;
                rear = -1;
            }else
                front++;
            return ele;
        }
    }
}

public class GenBinaryNums {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        System.out.print("Please Enter the number till which Binary Equivalents have to be printed out => ");
        int num = d.nextInt();
        generateBinNums(num);
        System.out.print("Ending....\n");
    }

    private static void generateBinNums(int n)
    {
        Queue queue = new Queue(2*n);
        queue.insert("1");

        while (n--!=0)
        {
            String s1 = queue.peek();
            queue.remove();
            System.out.print(s1+"\n");
            String s2 = s1;
            s1 = s1 + "0";
            s2 = s2 + "1";
            queue.insert(s1);
            queue.insert(s2);
        }
    }
}
