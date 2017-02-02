/**
 * Created by Chandan Suri on 2/2/2017.
 */

import java.util.*;

class Node
{
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

    public void setLeft(Node node)
    {
        left = node;
    }

    public void setRight(Node node)
    {
        right = node;
    }

    public void setData(int val)
    {
        data = val;
    }

    public int getData()
    {
        return data;
    }

    public Node getLeft()
    {
        return left;
    }

    public Node getRight()
    {
        return right;
    }
}

class Item
{
    private Node node;
    private int hor_dist;

    public Item(Node n, int h)
    {
        node = n;
        hor_dist = h;
    }

    public Node getNode()
    {
        return node;
    }

    public int getHor_dist()
    {
        return hor_dist;
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

    public void insert(int data)
    {
        root = insert(root, data);
    }

    private Node insert(Node node, int data)
    {
        if(node == null)
        {
            node = new Node(data);
        }else {
            if (data <= node.getData()) {
                node.setLeft(insert(node.getLeft(), data));
            } else {
                node.setRight(insert(node.getRight(), data));
            }
        }
        return node;
    }

    public void delete(int del_data)
    {
        if(isEmpty())
            System.out.print("The Tree is Empty!!\n");
        else if(!search(del_data))
            System.out.print("Sorry! The element is not present in the Tree.\n");
        else
            root = delete(root, del_data);
    }

    private Node delete(Node node, int del_data)
    {
        if(del_data == node.getData()){
            Node left_node, right_node;
            left_node = node.getLeft();
            right_node = node.getRight();
            if(left_node==null && right_node==null)
                return null;
            else if(left_node==null)
                return right_node;
            else if(right_node==null)
                return left_node;
            else{
                Node rt = right_node;
                while (right_node.getLeft()!=null)
                    right_node = right_node.getLeft();
                right_node.setLeft(left_node);
                return rt;
            }
        }
        else if(del_data < node.getData())
            node.setLeft(delete(node.getLeft(), del_data));
        else
            node.setRight(delete(node.getRight(), del_data));
        return node;
    }

    public boolean search(int val)
    {
        return search(root, val);
    }

    private boolean search(Node node, int data)
    {
        boolean found = false;
        while ((node!=null) && !found)
        {
            if(data < node.getData())
                node = node.getLeft();
            else if(data > node.getData())
                node = node.getRight();
            else{
                found = true;
                break;
            }
            found = search(node, data);
        }
        return found;
    }

    public int diameter()
    {
        return diameter(root);
    }

    private int diameter(Node node)
    {
        if(node==null)
            return 0;

        int left_height, right_height, left_dia, right_dia;

        left_height = height(node.getLeft());
        right_height = height(node.getRight());

        left_dia = diameter(node.getLeft());
        right_dia = diameter(node.getRight());

        return Math.max(Math.max(left_dia, right_dia), left_height + right_height + 1);
    }

    public int height(Node node){
        if(node==null)
            return 0;
        return (1 + Math.max(height(node.getLeft()), height(node.getRight())));
    }

    public void swapKth(int k){
        swapKth(root, k, 1);
    }

    private void swapKth(Node node, int k, int level)
    {
        if(node==null || (node.getLeft()==null && node.getRight()==null))
            return;

        swapKth(node.getLeft(), k, level+1);
        swapKth(node.getRight(), k, level+1);

        if((level+1)%k==0){
            Node temp = node.getLeft();
            node.setLeft(node.getRight());
            node.setRight(temp);
        }
    }

    static int max_level = 0;
    public void leftView()
    {

        leftView(root, 1);
    }

    private void leftView(Node node, int level)
    {
        if (node==null)
            return;
        if (level > max_level){
            System.out.print(node.getData()+" ");
            max_level = level;
        }

        leftView(node.getLeft(), level+1);
        leftView(node.getRight(), level+1);
    }

    public void rightView()
    {
        max_level = 0;
        rightView(root, 1);
    }

    private void rightView(Node node, int level)
    {
        if(node==null)
            return;
        if(level> max_level){
            System.out.print(node.getData()+" ");
            max_level = level;
        }

        rightView(node.getRight(), level+1);
        rightView(node.getLeft(), level+1);
    }

    public void topView()
    {
        if (root==null)
            return;
        HashSet<Integer> set = new HashSet<>();
        Queue<Item> queue = new LinkedList<>();
        queue.add(new Item(root, 0));

        while(!queue.isEmpty())
        {
            Item item = queue.remove();
            int dist = item.getHor_dist();
            Node node = item.getNode();

            if(!set.contains(dist))
            {
                set.add(dist);
                System.out.print(node.getData()+" ");
            }
            if (node.getLeft()!=null)
                queue.add(new Item(node.getLeft(), dist-1));
            if(node.getRight()!=null)
                queue.add(new Item(node.getRight(), dist+1));
        }
    }

    public void bottomView()
    {
        if (root==null)
            return;
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Item> queue = new LinkedList<>();
        queue.add(new Item(root, 0));

        while (!queue.isEmpty())
        {
            Item item = queue.remove();
            int dist = item.getHor_dist();
            Node node = item.getNode();

            map.put(dist, node.getData());
            if(node.getLeft()!=null)
                queue.add(new Item(node.getLeft(), dist-1));
            if (node.getRight()!=null)
                queue.add(new Item(node.getRight(), dist+1));
        }

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry pair = (Map.Entry)iterator.next();
            System.out.print(pair.getValue()+" ");
            iterator.remove();
        }
    }

    public void verticalSums()
    {
        verticalSums(root);
    }

    private void verticalSums(Node node)
    {
        if (node==null)
            return;
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Item> queue = new LinkedList<>();
        queue.add(new Item(node, 0));

        while (!queue.isEmpty())
        {
            Item item = queue.remove();
            int dist = item.getHor_dist();
            node = item.getNode();

            if (!map.containsKey(dist))
                map.put(dist, node.getData());
            else{
                int prev = map.get(dist).intValue();
                map.put(dist, prev+node.getData());
            }
            if(node.getLeft()!=null)
                queue.add(new Item(node.getLeft(), dist-1));
            if(node.getRight()!=null)
                queue.add(new Item(node.getRight(), dist+1));
        }

        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry pair = (Map.Entry) iterator.next();
            System.out.println(pair.getKey()+" -> "+pair.getValue());
            iterator.remove();
        }
    }

    public void zigZag()
    {
        zigZag(root);
    }

    private void zigZag(Node node)
    {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(node);

        while (!stack1.empty() || !stack2.empty())
        {
            while (!stack1.empty())
            {
                node = stack1.peek();
                stack1.pop();
                System.out.print(node.getData()+" ");
                if (node.getLeft()!=null)
                    stack2.push(node.getLeft());
                if (node.getRight()!=null)
                    stack2.push(node.getRight());
            }
            System.out.println();
            while (!stack2.empty())
            {
                node = stack2.peek();
                stack2.pop();
                System.out.print(node.getData()+" ");
                if (node.getRight()!=null)
                    stack1.push(node.getRight());
                if (node.getLeft()!=null)
                    stack1.push(node.getLeft());
            }
            System.out.println();
        }

    }

    public void bfs()
    {
        bfs(root);
    }

    private void bfs(Node node)
    {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty())
        {
            node = queue.peek();
            queue.remove();
            if(node!=null)
            {
                System.out.print(node.getData()+" ");
                if (node.getLeft()!=null)
                    queue.add(node.getLeft());
                if (node.getRight()!=null)
                    queue.add(node.getRight());
            }else {
                System.out.println();
                if (!queue.isEmpty())
                    queue.add(null);
            }
        }
    }

    public void inorder()
    {
        inorder(root);
    }

    private void inorder(Node node)
    {
        if (node!=null){
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
        if(node!=null){
            System.out.print(node.getData()+" ");
            preorder(node.getLeft());
            preorder(node.getRight());
        }
    }
}


public class BSTOperations {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        BST Tree = new BST();
        System.out.print("BST Operations => \n");

        while (true){
            boolean flag_to_exit = false;
            System.out.print("\nPlease Enter a valid choice => \n" +
                    "1. Insert a number.\n" +
                    "2. Delete a number.\n" +
                    "3. Search for a number.\n" +
                    "4. Check if the Tree is Empty.\n" +
                    "5. Get the diameter of the Tree.\n" +
                    "6. Swap Nodes of every Kth Level.\n" +
                    "7. Give the left view of the tree.\n" +
                    "8. Give the right view of the tree.\n" +
                    "9. Give the top view of the tree.\n" +
                    "10. Print the ZIG ZAG Tree.\n" +
                    "11. BFS.\n" +
                    "12. DFS.\n" +
                    "13. Give the bottom view of the tree.\n" +
                    "14. Print the Vertical Sum of the Tree.\n" +
                    "Enter 0 when want to Exit...........\n");
            int choice = d.nextInt();
            switch (choice){
                case 0:
                    flag_to_exit = true;
                    break;
                case 1:
                    System.out.print("Enter a number to insert => ");
                    int data = d.nextInt();
                    Tree.insert(data);
                    break;
                case 2:
                    System.out.print("Enter a value to Delete => ");
                    int del_data = d.nextInt();
                    Tree.delete(del_data);
                    break;
                case 3:
                    System.out.print("Enter a value to search => ");
                    int s_data = d.nextInt();
                    if(Tree.search(s_data))
                        System.out.print("The value is Present..\n");
                    else
                        System.out.print("The value is not Present..\n");
                    break;
                case 4:
                    if(Tree.isEmpty())
                        System.out.print("The Tree is Empty!!\n");
                    else
                        System.out.print("The Tree is Not Empty!!\n");
                    break;
                case 5:
                    System.out.print("The Diameter of the Tree is => "+Tree.diameter()+"\n");
                    break;
                case 6:
                    System.out.print("Enter the value of K => ");
                    int k = d.nextInt();
                    Tree.swapKth(k);
                    System.out.print("The Tree has been swapped....\n");
                    break;
                case 7:
                    System.out.print("The Left View => ");
                    Tree.leftView();
                    System.out.println();
                    break;
                case 8:
                    System.out.print("The Right View => ");
                    Tree.rightView();
                    System.out.println();
                    break;
                case 9:
                    System.out.print("The Top View => ");
                    Tree.topView();
                    System.out.println();
                    break;
                case 10:
                    System.out.print("The ZIG-ZAG Tree => ");
                    Tree.zigZag();
                    System.out.println();
                    break;
                case 11:
                    System.out.print("After BFS => ");
                    Tree.bfs();
                    System.out.println();
                    break;
                case 12:
                    System.out.print("We Take Here DFS to be Inorder => ");
                    Tree.inorder();
                    System.out.println();
                    break;
                case 13:
                    System.out.print("The Bottom View => ");
                    Tree.bottomView();
                    System.out.println();
                    break;
                case 14:
                    System.out.print("The Vertical Sums => \n");
                    Tree.verticalSums();
                    System.out.println();
                    break;
                default:
                    System.out.print("Please Enter a valid Choice...\n");
            }
            System.out.print("The Inorder is => ");
            Tree.inorder();
            System.out.println();
            System.out.print("The Pre Order is => ");
            Tree.preorder();
            System.out.println();
            if(flag_to_exit)
                break;
        }
        System.out.print("\nEnding.....\n");
    }
}























