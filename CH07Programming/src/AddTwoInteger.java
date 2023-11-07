import acm.program.ConsoleProgram;

public class AddTwoInteger extends ConsoleProgram {
        
        public void run() {
                setFont("JetBrains Mono NL-26");
                int n1 = readInt("Enter n1: ");
                int n2 = readInt("Enter n2: ");
                int result = n1 + n2;
                println("Result: " + result);

        }
        
}