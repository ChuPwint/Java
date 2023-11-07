import acm.program.ConsoleProgram;

public class Ex_4 extends ConsoleProgram {
    public void run() {
        setFont("JetBrains Mono NL-26");
        println("This program finds the largest and smallest numbers.");

        final int SENTINEL = 0;
        final String MESSAGE = "No largest or smallest to be chosen since you have entered no values.";
        int largest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        int temp1;

        int input = readInt("? ");
        if(input == SENTINEL){ println(MESSAGE); }
        else{
            largest = input;
            smallest = input;
            input = readInt("? ");

            while(input != SENTINEL){
                if(input > largest){
                    temp1 = largest;
                    largest = input;
                    if(temp1 < smallest) { smallest = temp1; } //for cases such as if the input is not smaller than smallest
                }
                else if(input < smallest) { smallest = input; }
                input = readInt("? ");
            }
            println("smallest: " + smallest);
            println("largest: " + largest);
        }
    }
}