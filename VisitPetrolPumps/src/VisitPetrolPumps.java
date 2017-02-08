import java.util.Scanner;

/**
 * Created by Chandan Suri on 2/9/2017.
 */
class PetrolPump
{
    private int petrol;
    private int distance;

    public PetrolPump(int p, int d)
    {
        petrol = p;
        distance = d;
    }

    public int getPetrol()
    {
        return petrol;
    }

    public int getDistance()
    {
        return distance;
    }
}

public class VisitPetrolPumps {

    public static void main(String[] args) {

        Scanner d = new Scanner(System.in);
        System.out.print("Enter the number of Petrol Pumps => ");
        int num = d.nextInt();
        PetrolPump arr[] = new PetrolPump[num];

        for (int i=0;i<num;i++)
        {
            arr[i] = new PetrolPump(d.nextInt(), d.nextInt());
        }

        int start = printTour(arr, num);
        if (start==-1)
            System.out.print("There's no solution..\n");
        else
            System.out.print("Start => "+start+"\n");
    }

    public static int printTour(PetrolPump arr[], int num)
    {
        int start = 0;
        int end = 1;

        int curr_petrol = arr[start].getPetrol()-arr[start].getDistance();
        while (end!=start || curr_petrol<0)
        {
            while (curr_petrol<0 && start!=end)
            {
                curr_petrol -= arr[start].getPetrol()-arr[start].getDistance();
                start = (start+1)%num;
                if (start==0)
                    return -1;
            }
            curr_petrol += arr[end].getPetrol()-arr[end].getDistance();
            end = (end+1)%num;
        }
        return start;
    }
}
