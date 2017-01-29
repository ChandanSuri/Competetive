/**
 * Created by Chandan Suri on 1/26/2017.
 */

import java.util.*;

class Node
{
    private Node left, right;
    private int data;
    private int height;

    public Node()
    {
        left = null;
        right = null;
        data = 0;
        height = 0;
    }

    public Node(int d)
    {
        left = null;
        right = null;
        data = d;
        height = 0;
    }

    public void setLeft(Node n)
    {
        left = n;
    }

    public void setRight(Node n)
    {
        right = n;
    }

    public void setData(int d)
    {
        data = d;
    }

    public void setHeight(int h)
    {
        height = h;
    }

    public Node getLeft()
    {
        return left;
    }

    public Node getRight()
    {
        return right;
    }

    public int getData()
    {
        return data;
    }

    public int getHeight()
    {
        return height;
    }
}

class AVLTree
{
    private Node root;

    public AVLTree()
    {
        root = null;
    }

    public boolean isEmpty()
    {
        return root==null;
    }

    public void makeEmpty()
    {
        root = null;
    }

    public void insert(int data)
    {
        root = insert(root, data);
    }

    private int height(Node n){
        return n==null?0:n.getHeight();
    }

    private int max(int lhs, int rhs)
    {
        return lhs>rhs?lhs:rhs;
    }

    private Node insert(Node node, int data)
    {
        if(node==null)
            node = new Node(data);
        else if(data < node.getData())
        {
            node.setLeft(insert(node.getLeft(), data));
            if(height(node.getLeft())-height(node.getRight()) == 2){
                if(data < node.getLeft().getData())
                    node = rotateWithLeftChild(node);
                else
                    node = doubleRotateWithLeftChild(node);
            }
        }else if(data > node.getData())
        {
            node.setRight(insert(node.getRight(), data));
            if(height(node.getRight())- height(node.getLeft()) == 2){
                if(data > node.getRight().getData())
                    node = rotateWithRightChild(node);
                else
                    node = doubleRotateWithRightChild(node);
            }
        }//In case of duplicate, do nothing..

        node.setHeight(max(height(node.getLeft()),height(node.getRight())) + 1);
        return node;
    }

    public void delete(int val)
    {
        if(isEmpty())
            System.out.print("The Tree is Empty!!\n");
        else if(!search(val))
            System.out.print("Sorry!! "+val+" could not be found in the Tree..\n");
        else {
            root = delete(root, val);
            System.out.print(val+" has been deleted from the tree.\n");
        }
    }

    private Node delete(Node node, int val)
    {
        if(val < node.getData())
            node.setLeft(delete(node.getLeft(), val));
        else if(val > node.getData())
            node.setRight(delete(node.getRight(), val));
        else{
            Node left_node, right_node;
            left_node = node.getLeft();
            right_node = node.getRight();

            Node temp;
            if(left_node==null && right_node==null)
                temp = null;
            else if(left_node==null)
                temp = right_node;
            else if (right_node==null)
                temp = left_node;
            else{
                Node rt =right_node;
                while (right_node.getLeft()!=null){
                    right_node = right_node.getLeft();
                }
                right_node.setLeft(left_node);
                temp = rt;
            }
            node = temp;
            if(node==null)
                return null;
            node.setHeight(max(height(node.getLeft()), height(node.getRight())) + 1);
            int diff = getDiff(node);
            int left_diff = getDiff(node.getLeft());
            int right_diff = getDiff(node.getRight());

            if(diff > 1 && left_diff>=0)
                return rotateWithLeftChild(node);
            if(diff > 1 && left_diff < 0)
                return doubleRotateWithLeftChild(node);

            if(diff < -1 && right_diff<=0)
                return rotateWithRightChild(node);
            if(diff< -1 && right_diff > 0)
                return doubleRotateWithRightChild(node);

        }
        return node;
    }

    public int getDiff(Node n)
    {
        if(n==null)
            return 0;
        else
            return height(n.getLeft())- height(n.getRight());
    }

    private Node rotateWithLeftChild(Node k2)
    {
        Node k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);

        k2.setHeight(max(height(k2.getLeft()),height(k2.getRight())) + 1);
        k1.setHeight(max(height(k1.getLeft()), k2.getHeight()) + 1);
        return k1;
    }

    private Node rotateWithRightChild(Node k1)
    {
        Node k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);

        k1.setHeight(max(height(k1.getLeft()),height(k1.getRight())) + 1);
        k2.setHeight(max(height(k2.getRight()), k1.getHeight()) + 1);
        return k2;
    }

    private Node doubleRotateWithLeftChild(Node k3)
    {
        k3.setLeft(rotateWithRightChild(k3.getLeft()));
        return rotateWithLeftChild(k3);
    }

    private Node doubleRotateWithRightChild(Node k1)
    {
        k1.setRight(rotateWithLeftChild(k1.getRight()));
        return rotateWithRightChild(k1);
    }

    public int countNodes()
    {
        return countNodes(root);
    }

    private int countNodes(Node node)
    {
        if(node==null)
            return 0;
        else{
            int nodes = 1;
            nodes += countNodes(node.getLeft());
            nodes += countNodes(node.getRight());
            return nodes;
        }
    }

    public boolean search(int val)
    {
        return search(root, val);
    }

    private boolean search(Node node, int val)
    {
        boolean found = false;
        while((node!=null) && !found){

            int data = node.getData();
            if(val<data)
                node = node.getLeft();
            else if(val>data)
                node = node.getRight();
            else{
                found = true;
                break;
            }
            found = search(node, val);
        }
        return found;
    }

    public void inorder()
    {
        inorder(root);
    }

    private void inorder(Node node)
    {
        if(node!=null)
        {
            inorder(node.getLeft());
            System.out.print(node.getData()+" ");
            inorder(node.getRight());
        }
    }

    public void preorder()
    {
        preorder(root);
    }

    private void preorder(Node node)
    {
        if(node!=null)
        {
            System.out.print(node.getData()+" ");
            preorder(node.getLeft());
            preorder(node.getRight());
        }
    }

    public void postorder()
    {
        postorder(root);
    }

    private void postorder(Node node)
    {
        if(node!=null)
        {
            postorder(node.getLeft());
            postorder(node.getRight());
            System.out.print(node.getData()+" ");
        }
    }
}

public class AVLTreeImplement {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        AVLTree Tree = new AVLTree();
        System.out.print("AVL Tree Implementation => \n");
        while (true){
            
            boolean flag_to_exit = false;
            System.out.print("Please Enter a valid choice => \n" +
                    "1. Insert an element in the Tree.\n" +
                    "2. Delete an element from the Tree.\n" +
                    "3. Count Number of Nodes in the Tree.\n" +
                    "4. Search for an element in the Tree.\n" +
                    "5. Check if the Tree is Empty.\n" +
                    "Enter 0 to exit the program...\n");

            int choice = d.nextInt();
            switch (choice){
                case 0:
                    flag_to_exit = true;
                    break;
                case 1:
                    System.out.print("Enter the data to be inserted => ");
                    int data = d.nextInt();
                    Tree.insert(data);
                    break;
                case 2:
                    System.out.print("Enter the value to be deleted => ");
                    int del_data = d.nextInt();
                    Tree.delete(del_data);
                    break;
                case 3:
                    System.out.print("The Number of Nodes in the Tree are => "+Tree.countNodes()+"\n");
                    break;
                case 4:
                    System.out.print("Enter the data to be searched => ");
                    int s_data = d.nextInt();
                    if(!Tree.search(s_data))
                        System.out.print("Sorry! Not Found...\n");
                    else
                        System.out.print("The Data is present in the tree...\n");
                    break;
                case 5:
                    if (Tree.isEmpty())
                        System.out.print("The Tree is Empty!!\n");
                    else
                        System.out.print("The Tree is not Empty!!\n");
                    break;
            }

            //Printing the tree made
            System.out.print("\nPREORDER => ");
            Tree.preorder();
            System.out.print("\nPOSTORDER => ");
            Tree.postorder();
            System.out.print("\nINORDER => ");
            Tree.inorder();
            System.out.println();

            if(flag_to_exit)
                break;
        }
        System.out.print("Ending........\n");
    }
}



























