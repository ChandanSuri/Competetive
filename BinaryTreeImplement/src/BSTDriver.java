/**
 * Created by Chandan Suri on 1/29/2017.
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

class BST
{
   private Node root;

   public BST()
   {
       root = null;
   }

   public boolean isEmpty()
   {
       return root==null;
   }

   public void insert(int d)
   {
       root = insert(root, d);
   }

   private Node insert(Node node, int d)
   {
       if(node==null)
           node = new Node(d);
       else{
           if(d <= node.getData())
               node.setLeft(insert(node.getLeft(), d));
           else
               node.setRight(insert(node.getRight(), d));
       }
       return node;
   }

   public void delete(int del_data)
   {
       if(isEmpty())
           System.out.print("Tree is Empty!!\n");
       else if(!search(del_data))
           System.out.print("Sorry!! "+del_data+" was not present..\n");
       else{
           root = delete(root, del_data);
           System.out.print(del_data+" has been deleted from the Tree.\n");
       }
   }

   private Node delete(Node node, int del_data)
   {
       Node n;
       if(del_data==node.getData())
       {
           Node left_node, right_node;
           left_node = node.getLeft();
           right_node = node.getRight();
           if(left_node==null && right_node==null)
               return null;
           else if(left_node==null)
               return right_node;
           else if(right_node==null)
               return left_node;
           else {
               Node rt = right_node;
               while (right_node.getLeft()!=null)
                   right_node = right_node.getLeft();
               right_node.setLeft(left_node);
               return rt;
           }
       }
       if(del_data<node.getData())
       {
           n = delete(node.getLeft(), del_data);
           node.setLeft(n);
       }else {
           n = delete(node.getRight(), del_data);
           node.setRight(n);
       }
       return node;
   }

   public boolean search(int val)
   {
       return search(root, val);
   }

   private boolean search(Node node, int val)
   {
       boolean found = false;
       while((node!=null) && !found)
       {
           int data = node.getData();
           if(val<data)
               node = node.getLeft();
           else if(val>data)
               node = node.getRight();
           else
           {
               found = true;
               break;
           }
           found = search(node,val);
       }
       return found;
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

public class BSTDriver {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        BST Tree = new BST();
        System.out.print("Binary Search Tree => \n");

        while (true){

            boolean flag_to_exit = false;
            System.out.print("Please Enter a valid Choice => \n" +
                    "1. Insert an element in the Tree.\n" +
                    "2. Delete an element from the Tree.\n" +
                    "3. Check if the Tree is Empty.\n" +
                    "4. Search for an element in the Tree.\n" +
                    "5. Print the Post Order of the Tree.\n" +
                    "6. Print the In Order of the Tree.\n" +
                    "7. Print the Pre Order of the Tree.\n" +
                    "8. Count the number of Nodes.\n" +
                    "Enter 0 when want to exit the program.......\n ");

            int choice = d.nextInt();

            switch(choice){

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
                    if(Tree.isEmpty())
                        System.out.print("The Tree is Empty!!\n");
                    else
                        System.out.print("The Tree is not Empty!!\n");
                    break;
                case 4:
                    System.out.print("Enter an element to search in the Tree => ");
                    int s_data = d.nextInt();

                    if(!Tree.search(s_data))
                        System.out.print("Sorry! could not find the data entered..\n");
                    else
                        System.out.print("The Data was Found. Thus, Present!!!\n");
                    break;
                case 5:
                    System.out.print("Post Order => ");
                    Tree.postorder();
                    break;
                case 6:
                    System.out.print("In Order => ");
                    Tree.inorder();
                    break;
                case 7:
                    System.out.print("Pre Order => ");
                    Tree.preorder();
                    break;
                case 8:
                    System.out.print("The Number of Nodes are => "+Tree.countNodes());
                    break;

            }
            if(flag_to_exit)
                break;
        }

        System.out.print("Ending........\n");

    }
}



























