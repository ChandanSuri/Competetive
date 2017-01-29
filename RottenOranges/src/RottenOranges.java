/**
 * Created by Chandan Suri on 1/27/2017.
 */

import java.util.*;
class Element
{
    public int x,y;//coordinates of the orange.
    public Element(){
        x = 0;
        y = 0;
    }
}

class Queue {

    Stack<Element> stack1;
    Stack<Element> stack2;

    public Queue(){

        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void insert(Element data){

        //System.out.print(data.x+" "+data.y+"\n");
        if(stack1.size()==0)
            stack1.push(data);
        else {
            int len = stack1.size();
            for(int i=0;i<len;i++){
                stack2.push(stack1.pop());
            }
            stack1.push(data);
            for(int i=0;i<len;i++){
                stack1.push(stack2.pop());
            }
        }
    }

    public Element remove(){
        if(stack1.size()==0)
            throw new NoSuchElementException("Underflow Exception");
        return stack1.pop();
    }

    public Element peek(){
        if(stack1.size()==0)
            throw new NoSuchElementException("Underflow Exception");
        return stack1.peek();
    }

    public boolean isEmpty(){
        return stack1.size()==0;
    }

    public int getSize(){
        return stack1.size();
    }

    public void display(){
        System.out.print("\nQueue => \n");
        int len = getSize();
        if(len==0)
            System.out.print("It's Empty!!\n");
        else
        {
            for(int i=len-1;i>=0;i--){
                Element ele = stack1.get(i);
                System.out.print("["+ele.x+","+ele.y+"] ");
            }
            System.out.println();
        }
    }
}

public class RottenOranges {

    private static int row_num,col_num;

    public static void main(String args[])
    {
        Scanner d = new Scanner(System.in);
        System.out.print("Enter the number of Rows => ");
        row_num = d.nextInt();
        System.out.print("Enter the number of columns => ");
        col_num = d.nextInt();
        int arr[][] = new int[row_num][col_num];
        for(int i = 0;i<row_num;i++){
            System.out.print((i+1)+" Row => \n");
            for(int j = 0;j<col_num;j++){
                arr[i][j] = d.nextInt();
            }
        }
        int can_rot = rotOranges(arr, row_num, col_num);
        if(can_rot == -1){
            System.out.print("All Oranges cannot rot!!\n");
        }else {
            System.out.print("Time required for all oranges to rot is => "+can_rot+"\n");
        }
        System.out.print("Ending...\n");
    }

    private static int rotOranges(int oranges[][], int rows, int cols)
    {
        Queue queue = new Queue();
        Element temp = new Element();
        int time_rot = 0;

        //Store all cells having rotten oranges at first....
        for(int r = 0;r<rows;r++){
            for (int c = 0;c<cols;c++){
                if(oranges[r][c] == 2){
                    temp.x = r;
                    temp.y = c;
                    queue.insert(temp);
                }
            }
        }
        //The delimiter to extract the top,bottom,left,right elements
        Element temp2 = new Element();
        temp2.x = -1;
        temp2.y = -1;
        queue.insert(temp2);
        //Till any rotten oranges in the Queue
        while(!queue.isEmpty()){
            boolean flag = false;
            while (!isDelim(queue.peek())){
                temp = queue.peek();

                if(isValid(temp.x+1, temp.y) && oranges[temp.x+1][temp.y]==1)
                {
                    if(!flag) {
                        time_rot++;
                        flag = true;
                    }
                    oranges[temp.x+1][temp.y] = 2;

                    temp.x++;
                    queue.insert(temp);
                    temp.x--;
                }

                if(isValid(temp.x-1, temp.y) && oranges[temp.x-1][temp.y]==1)
                {
                    if(!flag) {
                        time_rot++;
                        flag = true;
                    }
                        oranges[temp.x-1][temp.y] = 2;
                        temp.x--;
                        queue.insert(temp);
                        temp.x++;
                }

                if(isValid(temp.x, temp.y+1) && oranges[temp.x][temp.y+1]==1){
                    if(!flag){
                        time_rot++;
                        flag = true;
                    }
                    oranges[temp.x][temp.y+1] = 2;
                    temp.y++;
                    queue.insert(temp);
                    temp.y--;
                }

                if(isValid(temp.x, temp.y-1) && oranges[temp.x][temp.y-1]==1)
                {
                    if(!flag){
                        time_rot++;
                        flag = true;
                    }
                    oranges[temp.x][temp.y-1] = 2;
                    temp.y--;
                    queue.insert(temp);
                    temp.y++;
                }
                queue.remove();
            }
            queue.remove();//delimiter removed..

            if(!queue.isEmpty()){
                temp.x = -1;
                temp.y = -1;
                queue.insert(temp);
            }
        }
        return (checkAll(oranges))?-1:time_rot;
    }

    private static boolean isValid(int i, int j){
        return (i>=0 && j>=0 && i<row_num && j<col_num);
    }

    private static boolean isDelim(Element temp)
    {
        return (temp.x==-1 && temp.y==-1);
    }

    private static boolean checkAll(int arr[][]){
        for(int i=0;i<row_num;i++){
            for(int j=0;j<col_num;j++){
                if(arr[i][j] == 1)
                    return true;
            }
        }
        return false;
    }
}






















