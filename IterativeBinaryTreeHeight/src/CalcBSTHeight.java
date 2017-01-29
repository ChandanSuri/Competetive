/**
 * Created by Chandan Suri on 1/30/2017.
 */
import java.util.*;

class Node{

    private Node left, right;
    private int data;

    public Node()
    {
        left = null;
        right = null;
        data = 0;
    }

    public Node(int d)
    {
        left = null;
        right = null;
        data = d;
    }

    public void setLeft(Node n)
    {
        left = n;
    }

    public void setRight(Node n)
    {
        right = n;
    }

    public Node getLeft()
    {
        return left;
    }

    public Node getRight()
    {
        return right;
    }

    public void setData(int d)
    {
        data = d;
    }

    public int getData()
    {
        return data;
    }
}

class BST {
    private Node root;

    public BST() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(int d) {
        root = insert(root, d);
    }

    private Node insert(Node node, int d) {
        if (node == null)
            node = new Node(d);
        else {
            if (d <= node.getData())
                node.setLeft(insert(node.getLeft(), d));
            else
                node.setRight(insert(node.getRight(), d));
        }
        return node;
    }

    public int treeHeight()
    {
        return treeHeight(root);
    }
    private int treeHeight(Node node)
    {
        if(node==null)
            return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int height = 0;
        while(true){
            int nodeCount = queue.size();
            if(nodeCount==0)
                return height;
            height++;
            while (nodeCount>0){
                Node temp = queue.peek();
                queue.remove();
                if(temp.getLeft()!=null){
                    queue.add(temp.getLeft());
                }
                if(temp.getRight()!=null){
                    queue.add(temp.getRight());
                }
                nodeCount--;
            }
        }
    }
}

public class CalcBSTHeight {
    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);

        BST Tree = new BST();
        System.out.print("Enter 0 to exit inserting....\n");
        while (true){
            System.out.print("Enter the number to insert in the tree => ");
            int num = d.nextInt();
            if(num==0)
                break;
            Tree.insert(num);
        }
        System.out.print("The Height of the Tree is => "+Tree.treeHeight());
    }
}
