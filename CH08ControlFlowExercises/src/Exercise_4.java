import acm.program.ConsoleProgram;

public class Exercise_4 extends ConsoleProgram {
    public void run(){
        println("This program computes a sum from the formula.");
        int limit = readInt("Enter N: ");
        double sum = 0;
        for(int i = 1; i <= limit; i++){
            sum += 1.0/i;
        }
        println("The sum is " + sum);
    }
}