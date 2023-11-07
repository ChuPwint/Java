import acm.program.ConsoleProgram;

public class InterestSecondProblem extends ConsoleProgram {
    public void run() {
        setFont("JetBrains Mono NL-26");
        println("This program shows balance after two year with interest.");
        double balance = readDouble("Enter balance: ");
        double interestRate = readDouble("Enter interest rate: ");
        double interestAfterAYr = balance * interestRate/100.0;
        balance = balance + interestAfterAYr;
        println("Balance after one year: " + balance);
        double interestAfterTwoYrs = balance * interestRate/100.0;
        balance = balance + interestAfterTwoYrs;
        println("Balance after two year: " + balance);
    }
}