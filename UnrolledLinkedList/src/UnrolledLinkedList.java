import java.util.*;

class Node
{
    private int arr_size;
    private int num_ele;
    private int[] arr_ele;
    private Node next;

    public Node()
    {
        arr_size = -1;
        num_ele = 10;
        arr_ele = new int[num_ele];
        next = null;
    }

    public Node(int max_ele){

        arr_size = -1;
        num_ele = max_ele;
        arr_ele = new int[num_ele];
        next = null;
    }

    public Node(int max_ele, Node n){
        arr_size = -1;
        num_ele = max_ele;
        arr_ele = new int[num_ele];
        next = n;
    }

    public void setNext(Node n)
    {
        next = n;
    }

    public boolean setData(int data)
    {
        arr_size++;
        if(arr_size >= num_ele){
            System.err.println("The Position is not Valid.");
            return false;
        }
        arr_ele[arr_size] = data;
        return true;
    }

    public void setData(int data[], int size_data){

        if(size_data > num_ele){
            System.err.print("Sorry! The array entered is larger than can be saved.\n");
            return;
        }
        arr_size = size_data-1;
        for(int i=0;i<size_data;i++){
            arr_ele[i] = data[i];
        }
    }

    public Node getNext()
    {
        return next;
    }

    public int getData(int pos){
        if(pos >= num_ele){
            System.err.print("The position is not valid.\n");
            return 0;
        }
        return arr_ele[pos];
    }

    public int[] getData()
    {
        return arr_ele;
    }

    public int getArr_size()
    {
        return arr_size;
    }
}

class LinkedList
{
    private Node start;
    private Node end;
    private int size;

    public LinkedList()
    {
        start = null;
        end = null;
        size = 0;
    }

    public boolean isEmpty(){
        return start == null;
    }

    public int getSize(){
        return size;
    }

    public void insertAtStart(int data[], int max_size, int curr_size){

        Node i_node = new Node(max_size, null);
        i_node.setData(data, curr_size);
        size++;
        if (start == null) {
            start = i_node;
            end = start;
        }else{
            i_node.setNext(start);
            start = i_node;
        }
    }

    public void insertAtEnd(int data[], int max_size,int curr_size){

        Node i_node = new Node(max_size, null);
        i_node.setData(data, curr_size);
        size++;
        if(start==null){
            start = i_node;
            end = start;
        }else{
            end.setNext(i_node);
            end = i_node;
        }
    }

    public void insertAtPos(int data[], int max_size, int pos, int curr_size){

        Node i_node = new Node(max_size, null);
        i_node.setData(data, curr_size);
        pos--;
        Node ptr = start;

        while(pos!=1){
            ptr = ptr.getNext();
            pos--;
        }
        Node tmp = ptr.getNext();
        ptr.setNext(i_node);
        i_node.setNext(tmp);
        size++;
    }

    public void insertAtPos(int data, int pos){
        Node ptr = start;
        for(int i=1;i<pos;i++){
            ptr = ptr.getNext();
        }
        ptr.setData(data);
    }

    public void deleteAtPos(int pos){
        if(pos==1){
            start = start.getNext();
            size--;
            return;
        }
        if(pos == size){
            Node ptr = start;
            while(ptr.getNext()!=end){
                ptr = ptr.getNext();
            }
            end = ptr;
            end.setNext(null);
            size--;
            return;
        }
        Node ptr = start;
        pos--;
        while(pos!=1){
            ptr = ptr.getNext();
        }
        Node tmp = ptr.getNext().getNext();
        ptr.setNext(tmp);
        size--;
    }

    public void display()
    {
        if(size==0){
            System.out.print("There are no elements in the List.\n");
            return;
        }
        if(start.getNext()==null){
            System.out.print("The Elements at the start node are => ");
            int[] d = start.getData();
            for(int i=0;i<=start.getArr_size();i++){
                System.out.print(d[i]+"\t");
            }
            System.out.println();
            return;
        }
        Node ptr = start;
        while(ptr.getNext()!=null){
            int[] d = ptr.getData();
            System.out.print("["+d[0]+" ");
            for(int i=1;i<ptr.getArr_size();i++){
                System.out.print(d[i]+" ");
            }
            if(ptr.getArr_size()==0)
                System.out.print("] -> ");
            else
                System.out.print(d[ptr.getArr_size()]+"] -> ");
            ptr = ptr.getNext();
        }
        int[] d = ptr.getData();
        System.out.print("["+d[0]+" ");
        for(int i=1;i<ptr.getArr_size();i++){
            System.out.print(d[i]+" ");
        }
        if(ptr.getArr_size()==0)
            System.out.print("]");
        else
            System.out.print(d[ptr.getArr_size()]+"]");
        System.out.println("The List has been printed out!!");
    }

}

public class UnrolledLinkedList {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        LinkedList list = new LinkedList();

        System.out.println("Unrolled Linked List => ");
        int choice;
        while(true){
            int max_size;
            int curr_size;
            int data[];
            int pos;
            System.out.println("Please Enter a choice => \n" +
                    "1. Insert Element at the beginning.(An Array) \n" +
                    "2. Insert Element at the end. (An Array) \n" +
                    "3. Insert Element at the Position Entered. (An Array) \n" +
                    "4. Insert Single Element at a Position \n" +
                    "5. Delete a Node from the List \n" +
                    "6. Check if the list is empty \n" +
                    "7. Display the List Size\n" +
                    "Enter 0 for Exiting the process......");
            choice = d.nextInt();
            boolean flag_to_exit = false;
            switch(choice){
                case 0:
                    flag_to_exit = true;
                    break;
                case 1:
                    System.out.print("Please Enter the size of the Array you want at the Node => ");
                    max_size = d.nextInt();
                    System.out.print("Enter the number of elements you want to enter => ");
                    curr_size = d.nextInt();
                    System.out.print("Please Enter the array to be stored as the beginning Node => ");
                    data = new int[max_size];
                    for(int i = 0;i<curr_size;i++){
                        data[i] = d.nextInt();
                    }
                    list.insertAtStart(data, max_size, curr_size);
                    break;
                case 2:
                    System.out.print("Please Enter the size of the Array you want at the Node => ");
                    max_size = d.nextInt();
                    System.out.print("Enter the number of elements you want to enter => ");
                    curr_size = d.nextInt();
                    System.out.print("Please Enter the array to be stored as the End Node => ");
                    data = new int[max_size];
                    for(int i = 0;i<curr_size;i++){
                        data[i] = d.nextInt();
                    }
                    list.insertAtEnd(data, max_size, curr_size);
                    break;
                case 3:
                    System.out.print("Please Enter the size of the Array you want at the Node => ");
                    max_size = d.nextInt();
                    System.out.print("Enter the number of elements you want to enter => ");
                    curr_size = d.nextInt();
                    System.out.print("Enter the position at which you want to enter the New Node in the List => ");
                    pos = d.nextInt();
                    System.out.print("Please Enter the array to be stored as a Node => ");
                    data = new int[max_size];
                    for(int i = 0;i<curr_size;i++){
                        data[i] = d.nextInt();
                    }
                    list.insertAtPos(data, max_size, pos, curr_size);
                    break;
                case 4:
                    System.out.print("Please Enter the data you want to enter at the Node => ");
                    int single_data = d.nextInt();
                    System.out.print("Please Enter the position at which you want to enter the element => ");
                    pos = d.nextInt();
                    list.insertAtPos(single_data,pos);
                    break;
                case 5:
                    System.out.print("Please enter the position where deletion has to take place => ");
                    pos = d.nextInt();
                    if(pos<1 || pos>list.getSize()){
                        System.out.print("Invalid Position.\n");
                    }else{
                        list.deleteAtPos(pos);
                    }
                    break;
                case 6:
                    if(list.isEmpty()){
                        System.out.print("Sorry! The List is Empty!!\n");
                    }else{
                        System.out.print("The List is Not Empty!!\n");
                    }
                    break;
                case 7:
                    System.out.print("The List Size is => "+list.getSize()+"\n");
                    break;
            }
            list.display();
            if(flag_to_exit){
                break;
            }
        }
        System.out.println("Exiting.......");
    }
}
