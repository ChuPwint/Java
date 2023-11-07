import acm.program.ConsoleProgram;

public class Exercise_3 extends ConsoleProgram {
    public void run(){
        println("This program adds up integers the users enters.");
        int num = readInt("How many integers will be added: ");
        int sum = 0;
        for(int i = 1; i <= num; i++){
            int a = readInt("Enter an integer:");
            sum += a;
        }
        println("The sum is " + sum);
    }
}