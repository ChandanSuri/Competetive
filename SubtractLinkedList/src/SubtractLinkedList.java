/**
 * Created by Chandan Suri on 1/26/2017.
 */
import java.util.*;

class Node{

    private int data;
    private Node next;

    public Node(){
        data = 0;
        next = null;
    }

    public Node(int d){
        data = d;
        next = null;
    }

    public void setData(int d){
        data = d;
    }

    public void setNext(Node n){
        next = n;
    }

    public Node getNext(){
        return next;
    }

    public int getData(){
        return data;
    }
}

class linkedList{

    protected Node start;
    protected Node end;
    private int size;
    public boolean borrow = false;

    public linkedList(){
        start = null;
        end = null;
        size = 0;
    }

    public boolean isEmpty(){
        return start==null;
    }

    public int getSize(){
        return size;
    }

    public void insertAtStart(int val){

        Node i_node = new Node(val);
        size++;
        if(start == null){
            start = i_node;
            end = start;
        }else{
            i_node.setNext(start);
            start = i_node;
        }
    }

    public void insertAtEnd(int val){
        Node i_node = new Node(val);
        size++;
        if(start==null){
            start = i_node;
            end = start;
        }else{
            end.setNext(i_node);
            end = i_node;
        }
    }

    public void subtract(Node n1, Node n2){

        if(n1==null||n2==null)
            return;

        subtract(n1.getNext()!=null ? n1.getNext():null , n2.getNext()!=null ? n2.getNext():null);
        if(borrow){
            n1.setData(n1.getData()-1);
        }
        if(n1.getData()<n2.getData()){
            borrow = true;
            int data = n1.getData()+10-n2.getData();
            insertAtStart(data);
        }else{
            borrow = false;
            int data = n1.getData()-n2.getData();
            insertAtStart(data);
        }
    }

    public void display(){

        if(size==0){
            System.out.print("This Linked List has no elements in it.\n");
            return;
        }
        if(start.getNext()==null){
            System.out.println(start.getData());
            return;
        }
        Node ptr = start;
        while(ptr.getNext()!=null){
            System.out.print(ptr.getData()+"->");
            ptr = ptr.getNext();
        }
        System.out.println(ptr.getData());
    }
}

public class SubtractLinkedList {

    public static void main(String args[]){
        Scanner d = new Scanner(System.in);
        linkedList list1 = new linkedList();
        linkedList list2 = new linkedList();
        linkedList final_list = new linkedList();

        System.out.print("Linked List Subtraction => \n");
        //Inputs
        System.out.print("Input 1st Linked List => \n" +
                "Enter the Number of elements to be inputted in the List => ");
        int size1 = d.nextInt();
        System.out.print("Enter the Linked List => ");
        list1.insertAtStart(d.nextInt());
        for(int i=1;i<size1;i++){
            list1.insertAtEnd(d.nextInt());
        }
        System.out.print("Input 2nd Linked List => \n" +
                "Enter the Number of elements to be inputted in the List => ");
        int size2 = d.nextInt();
        System.out.print("Enter the Linked List => ");
        list2.insertAtStart(d.nextInt());
        for(int i=1;i<size2;i++){
            list2.insertAtEnd(d.nextInt());
        }
        //Appending zeros for short List among the two
        if(list1.getSize()>list2.getSize()){
            for(int i=1;i<=list1.getSize()-list2.getSize();i++)
                list2.insertAtStart(0);
            final_list.subtract(list1.start, list2.start);
        }else if(list1.getSize()<list2.getSize()){
            for(int i=1;i<=list2.getSize()-list1.getSize();i++)
                list1.insertAtStart(0);
            final_list.subtract(list2.start, list1.start);
        }else{
            int greater = checkWhichGreater(list1.start, list2.start);
            if(greater==1){
                final_list.subtract(list1.start,list2.start);
            }else if(greater==2){
                final_list.subtract(list2.start,list1.start);
            }else{
                System.out.print("Both the Numbers are equal. Thus, result will be zero.\n");
                System.out.print("Exiting...\n");
                return;
            }
        }
        //Display the final List
        final_list.display();
        System.out.print("Exiting...\n");
    }

    public static int checkWhichGreater(Node n1, Node n2){

        while(n1!=null||n2!=null){
            if(n1.getData()>n2.getData()){
                return 1;
            }else if(n2.getData()>n1.getData()){
                return 2;
            }
            n1 = n1.getNext();
            n2 = n2.getNext();
        }
        return 0;
    }
}
