import acm.program.ConsoleProgram;

public class Ex_1 extends ConsoleProgram {
    public void run() {
        setFont("JetBrains Mono NL-20");
        println("This program prints the possibility of two six-sided dices.");
        for(int i = 1; i <= 6; i++){
            for(int j = 1; j <= 6; j++){
                print("(" + i + ", " + j + ")\t");
            }
            println();
        }
    }
}