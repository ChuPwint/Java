import acm.program.ConsoleProgram;

public class FindTwoLargest extends ConsoleProgram {
        public void run() {
                //setFont("JetBrains Mono NL-26");
                final int SENTINEL = 0;

                int value = readInt("?");
                int largest = Integer.MIN_VALUE;
                int secLargest = Integer.MIN_VALUE;
                while (value != SENTINEL) {
                        if (value > largest) {
                                int temp = largest;
                                largest = value;
                                secLargest = temp;
                        } else {
                                if (value > secLargest) {
                                        secLargest = value;
                                }
                        }
                        value = readInt("?");
                }
                println("The largest: " + largest);
                println("The 2nd largest: " + secLargest);
        }
}
