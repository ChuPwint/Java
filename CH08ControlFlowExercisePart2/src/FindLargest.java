import acm.program.ConsoleProgram;

public class FindLargest extends ConsoleProgram {
        @Override
        public void run() {
                //setFont("JetBrains Mono NL-26");
                final int SENTINEL = 0;

                int value = readInt("?");
                int largest = value;
                while (value != SENTINEL) {
                        if ( value > largest) largest = value;
                        value = readInt("?");
                }
                println("The largest: " + largest);
        }
}
