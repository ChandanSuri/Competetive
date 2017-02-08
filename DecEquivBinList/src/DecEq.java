import java.util.Scanner;

/**
 * Created by Chandan Suri on 2/9/2017.
 */
class Node
{
    private int data;
    private Node next;

    public Node()
    {
        data = 0;
        next = null;

    }
    public Node(int d)
    {
        data = d;
        next = null;
    }

    public int getData()
    {
        return data;
    }

    public Node getNext()
    {
        return next;
    }

    public void setData(int d)
    {
        data = d;
    }

    public void setNext(Node node)
    {
        next = node;
    }

}

class BinList
{
    private Node head;
    private Node end;
    private int size;

    public BinList()
    {
        head = null;
        end = null;
        size = 0;
    }

    public int decValue()
    {
        return decValue(head);
    }

    private int decValue(Node node)
    {
        int res=0;
        while (node!=null)
        {
            res = (res<<1) + node.getData();
            node = node.getNext();
        }
        return res;
    }

    public void insertAtEnd(int val)
    {
        Node i_node = new Node(val);
        size++;
        if(head==null){
            head = i_node;
            end = head;
        }else{
            end.setNext(i_node);
            end = i_node;
        }
    }
/*
    public void display()
    {
        if(size == 0)
        {
            System.out.println("This Linked List has no elements in it");
            return;
        }
        if (head.getNext()==null)
        {
            System.out.println(head.getData());
            return;
        }
        Node ptr = head;
        while(ptr.getNext()!=null)
        {
            System.out.print(ptr.getData()+"->");
            ptr = ptr.getNext();
        }
        System.out.println(ptr.getData());
        System.out.print("The List has been printed out\n");
    }
*/
}

public class DecEq {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        System.out.print("Enter the length of the Binary Number => ");
        int len = d.nextInt();
        BinList list = new BinList();

        for (int i=0;i<len;i++)
        {
            list.insertAtEnd(d.nextInt());
        }
        //list.display();
        System.out.print("The Decimal Eq is => "+list.decValue());
    }
}
