import acm.program.ConsoleProgram;

public class Exercise_1 extends ConsoleProgram {
    public void run(){
        println("This program prints values from start to end.");
        int start = readInt("Enter start: ");
        int end = readInt("Enter end: ");
        for(int i = start; i <= end; i++){
            println(i);
        }
    }
}