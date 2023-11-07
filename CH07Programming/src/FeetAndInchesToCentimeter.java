import acm.program.ConsoleProgram;

public class FeetAndInchesToCentimeter extends ConsoleProgram {

        public void run() {
                setFont("JetBrains Mono NL-26");
                final double CENTIMETERS_PER_INCH = 2.54;
                final double INCHES_PER_FOOT = 12.0;
                int ft = readInt("Enter number of feet: ");
                double in = readDouble("Enter number of inches: ");
                double totalInches = ft * INCHES_PER_FOOT + in;
                double totalCenti = totalInches * CENTIMETERS_PER_INCH;

                println(ft + "ft " + in + "in = " + totalCenti + "cm");
        }
}
