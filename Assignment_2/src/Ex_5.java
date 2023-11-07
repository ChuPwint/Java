import acm.program.ConsoleProgram;

public class Ex_5 extends ConsoleProgram {
    public void run() {
        setFont("JetBrains Mono NL-20");
        println("Miles Per Gallon Program");

        final int SENTINEL = -1;

        int initMile = readInt("Initial miles: ");
        while(initMile != SENTINEL){
            int finMile = readInt("Final miles: ");
            double gallons = readInt("Gallons: ");
            int totMiles = (finMile - initMile);
            double mpg = totMiles / gallons;

            println("Miles per Gallon: " + mpg);
            println();
            initMile = readInt("Initial miles: ");
        }
        println("bye");
    }
}