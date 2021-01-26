import java.util.Scanner;
public class Driver{
    public static void main(String args[]){
        long startTime = System.nanoTime();
        int numTestCases;
        Scanner sc = new Scanner(System.in);
        numTestCases = sc.nextInt();
        while(numTestCases-->0){
            int size;
            size = sc.nextInt();
            A2DynamicMem obj = new A2DynamicMem(size);
            int numCommands = sc.nextInt();
            while(numCommands-->0) {
                String command;
                command = sc.next();
                int argument;
                argument = sc.nextInt();
                int result = -5;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        System.out.println(result);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                       System.out.println(result);
                        break;
                    case "Defragment":
                         obj.Defragment();
                        break;
                    default:
                        break;
                }
               
            }
            
        }
        sc.close();
        
long stopTime = System.nanoTime();
//System.out.println((stopTime - startTime)/1000000000.0);

    }
}