import acm.program.ConsoleProgram;

public class LeapYearExample extends ConsoleProgram {

        public void run() {
                setFont("JetBrains Mono NL-26");
                println("This program shows leap year or not.");
                int year = readInt("Enter the year: ");
                boolean isLeapYr = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                println("Is " + year + " leap year? " + isLeapYr);
        }
}
