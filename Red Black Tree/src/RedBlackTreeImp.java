import com.sun.org.apache.regexp.internal.RE;

import java.util.Scanner;

/**
 * Created by Chandan Suri on 2/9/2017.
 */

class RBNode
{
    private RBNode left, right, parent;
    private int key;
    private int color;

    public RBNode(int k)
    {
        parent = null;
        left = null;
        right = null;
        key = k;
        color = 1;
    }

    public RBNode(int k, RBNode l, RBNode r)
    {
        parent = null;
        left = l;
        right = r;
        key = k;
        color = 1;
    }

    public RBNode getLeft()
    {
        return left;
    }

    public RBNode getRight()
    {
        return right;
    }

    public int getKey()
    {
        return key;
    }

    public int getColor()
    {
        return color;
    }

    public void setLeft(RBNode l)
    {
        left = l;
    }

    public void setRight(RBNode r)
    {
        right = r;
    }

    public void setColor(int col)
    {
        color = col;
    }

    public void setKey(int k)
    {
        key = k;
    }

    public void setParent(RBNode node)
    {
        parent = node;
    }

    public RBNode getParent()
    {
        return parent;
    }
}

class RBTree
{
    private RBNode root;
    private static RBNode null_node;

    private static final int BLACK = 1;
    private static final int RED = 0;

    public RBTree()
    {
        null_node = new RBNode(-1);
        null_node.setLeft(null_node);
        null_node.setRight(null_node);
        root = null_node;
    }

    private RBNode findNode(RBNode f_node, RBNode node)
    {
        if (root==null_node)
            return null;
        if (f_node.getKey()<node.getKey())
            if (node.getLeft()!=null_node)
                return findNode(f_node, node.getLeft());
        else if (f_node.getKey()>node.getKey())
            if (node.getRight()!=null_node)
                return findNode(f_node, node.getRight());
        else if (f_node.getKey()==node.getKey())
            return node;
        return null;
    }

    public void insert(int data)
    {
        RBNode node = new RBNode(data);
        RBNode ptr = root;
        if (root==null_node)
        {
            root = node;
            node.setColor(BLACK);
            node.setParent(null_node);
        }else{
            node.setColor(RED);
            while (true)
            {
                if (data<ptr.getKey())
                {
                    if (ptr.getLeft()==null_node){
                        ptr.setLeft(node);
                        node.setParent(ptr);
                        break;
                    }else {
                        ptr = ptr.getLeft();
                    }
                }
                else if (data>= ptr.getKey())
                {
                    if (ptr.getRight()==null_node){
                        ptr.setRight(node);
                        node.setParent(ptr);
                        break;
                    }else {
                        ptr = ptr.getRight();
                    }
                }
            }
            fixTree(node);
        }
    }

    private void fixTree(RBNode node)
    {
        while (node.getParent().getColor()==RED)
        {
            RBNode uncle = null_node;
            if (node.getParent()==node.getParent().getParent().getLeft()){
                uncle = node.getParent().getParent().getRight();

                if (uncle!=null_node && uncle.getColor()==RED){
                    //Recolouring required...
                    node.getParent().setColor(BLACK);
                    uncle.setColor(BLACK);
                    node.getParent().getParent().setColor(RED);
                    node = node.getParent().getParent();
                    continue;
                }
                if (node == node.getParent().getRight()){
                    //Double Rotation is required...
                    node = node.getParent();
                    rotateLeft(node);
                }
                node.getParent().setColor(BLACK);
                node.getParent().getParent().setColor(RED);
                rotateRight(node.getParent().getParent());
            }else {
                uncle = node.getParent().getParent().getLeft();

                if (uncle!=null_node && uncle.getColor()== RED){
                    //Recolouring required...
                    node.getParent().setColor(BLACK);
                    uncle.setColor(BLACK);
                    node.getParent().getParent().setColor(RED);
                    node = node.getParent().getParent();
                    continue;
                }
                if (node == node.getParent().getLeft()){
                    //Double Rotation is required...
                    node = node.getParent();
                    rotateRight(node);
                }
                node.getParent().setColor(BLACK);
                node.getParent().getParent().setColor(RED);
                rotateLeft(node.getParent().getParent());
            }
        }
        root.setColor(BLACK);
    }

    private void rotateLeft(RBNode node)
    {
        if (node.getParent()!=null_node){
            if (node==node.getParent().getLeft()){
                node.getParent().setLeft(node.getRight());
            }else {
                node.getParent().setRight(node.getRight());
            }
            node.getRight().setParent(node.getParent());
            node.setParent(node.getRight());
            if (node.getRight().getLeft()!=null_node)
                node.getRight().getLeft().setParent(node);
            node.setRight(node.getRight().getLeft());
            node.getParent().setLeft(node);
        }else {
            //to rotate root
            RBNode right = node.getRight();
            root.setRight(right.getLeft());
            right.getLeft().setParent(root);
            root.setParent(right);
            right.setLeft(root);
            right.setParent(null_node);
            root = right;
        }
    }

    private void rotateRight(RBNode node)
    {
        if (node.getParent()!=null_node){
            if (node == node.getParent().getLeft()){
                node.getParent().setLeft(node.getLeft());
            }else {
                node.getParent().setRight(node.getLeft());
            }
            node.getLeft().setParent(node.getParent());
            node.setParent(node.getLeft());
            if (node.getLeft().getRight()!=null_node)
                node.getLeft().getRight().setParent(node);
            node.setLeft(node.getLeft().getRight());
            node.getParent().setRight(node);
        }else {
            //to rotate root
            RBNode left = root.getLeft();
            root.setLeft(root.getLeft().getRight());
            left.getRight().setParent(root);
            root.setParent(left);
            left.setRight(root);
            left.setParent(null_node);
            root = left;
        }
    }

    public void deleteTree()
    {
        root = null_node;
    }

    private void transplant(RBNode target, RBNode with)
    {
        if (target.getParent()==null_node)
            root = with;
        else if (target == target.getParent().getLeft())
            target.getParent().setLeft(with);
        else
            target.getParent().setRight(with);

        with.setParent(target.getParent());
    }

    public boolean RBDelete(int data)
    {
        RBNode z = new RBNode(data);
        if ((z=findNode(z,root))==null)
            return false;
        RBNode x;
        RBNode y = z; //temporary
        int y_original_color = y.getColor();
        if (z.getLeft()==null_node) {
            x = z.getRight();
            transplant(z,z.getRight());
        }else if (z.getRight()==null_node){
            x = z.getLeft();
            transplant(z, z.getLeft());
        }else {
            y = treeMin(z.getRight());
            y_original_color = y.getColor();
            x = y.getRight();
            if (y.getParent()==z)
                x.setParent(y);
            else {
                transplant(y,y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }
        if (y_original_color==BLACK)
            RBDeleteFix(x);
        return true;
    }

    private void RBDeleteFix(RBNode x)
    {
        while (x!=root && x.getColor()==BLACK)
        {
            if (x==x.getParent().getLeft()){
                RBNode w = x.getParent().getRight();
                if (w.getColor()==RED){
                    w.setColor(BLACK);
                    x.getParent().setColor(RED);
                    rotateLeft(x.getParent());
                    w = x.getParent().getRight();
                }
                if (w.getLeft().getColor()==BLACK && w.getRight().getColor()==BLACK){
                    w.setColor(RED);
                    x = x.getParent();
                    continue;
                }else if (w.getRight().getColor()==BLACK){
                    w.getLeft().setColor(BLACK);
                    w.setColor(RED);
                    rotateRight(w);
                    w = x.getParent().getRight();
                }
                if (w.getRight().getColor()==RED)
                {
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(BLACK);
                    w.getRight().setColor(BLACK);
                    rotateLeft(x.getParent());
                    x = root;
                }
            }else {
                RBNode w = x.getParent().getLeft();
                if (w.getColor()==RED)
                {
                    w.setColor(BLACK);
                    x.getParent().setColor(RED);
                    rotateRight(x.getParent());
                    w = x.getParent().getLeft();
                }
                if (w.getRight().getColor()==BLACK && w.getLeft().getColor()==BLACK){
                    w.setColor(RED);
                    x = x.getParent();
                    continue;
                }else if (w.getLeft().getColor()==BLACK){
                    w.getRight().setColor(BLACK);
                    w.setColor(RED);
                    rotateLeft(w);
                    w = x.getParent().getLeft();
                }
                if (w.getLeft().getColor()==RED)
                {
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(BLACK);
                    w.getLeft().setColor(BLACK);
                    rotateRight(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(BLACK);
    }

    private RBNode treeMin(RBNode node){
       while (node.getLeft()!=null_node){
           node = node.getLeft();
       }
       return node;
    }

    public void printTree()
    {
        printTree(root);
    }

    private void printTree(RBNode node) {
        if (node == null_node) {
            return;
        }
        printTree(node.getLeft());
        System.out.print(((node.getColor()==RED)?"Color: Red ":"Color: Black ")+"Key: "
                +node.getKey()+" Parent: "
                +node.getParent().getKey()+"\n");
        printTree(node.getRight());
    }

}

public class RedBlackTreeImp {//Driver Program

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);

        RBTree tree = new RBTree();
        while (true){
            System.out.print("Enter your choice => \n" +
                    "1. INSERT.\n" +
                    "2. DELETE.\n" +
                    "3. PRINT ITEMS.\n" +
                    "4. DELETE TREE.\n" +
                    "Enter 0 to exit.......\n");
            int choice = d.nextInt();
            boolean flag_to_exit = false;
            switch (choice)
            {
                case 0:
                    flag_to_exit = true;
                    break;
                case 1:
                    System.out.print("Enter a no. to insert => ");
                    int i_no = d.nextInt();
                    tree.insert(i_no);
                    break;
                case 2:
                    System.out.print("Enter the no. to delete => ");
                    int d_no = d.nextInt();
                    if (tree.RBDelete(d_no))
                        System.out.print("The no. has been deleted!!\n");
                    else
                        System.out.print("The no. could not be deleted!!\n");
                    break;
                case 3:
                    tree.printTree();
                    break;
                case 4:
                    tree.deleteTree();
                    System.out.print("The Tree has been deleted!!\n");
                    break;
                default:
                    System.out.print("Please Enter a valid choice...\n");
            }
            tree.printTree();
            if (flag_to_exit)
                break;
        }
        System.out.print("Ending....\n");
    }
}





















