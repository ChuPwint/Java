import acm.program.ConsoleProgram;

public class Ex_3 extends ConsoleProgram {
    public void run() {
        setFont("JetBrains Mono NL-20");
        int amountOwed = readInt("Enter total amt to pay: ");
        int monthlyPayment = readInt("Enter monthly payment: ");
        double interestRate = readDouble("Enter monthly interest rate in % : ");

        double balance = amountOwed;
        double interestNum = (balance * (interestRate / 100));
        balance += interestNum;

        double paidAmount = 0.0;
        int months = 1;

        while(balance >= monthlyPayment){
            balance -= monthlyPayment;
            paidAmount += monthlyPayment;

            println("Month: " + months + "\tBal: " + balance + "\t\tPaid: " + paidAmount);
            interestNum = (balance * (interestRate / 100));
            balance += interestNum;
            months += 1;
        }

        //if there is remaining amount
        if(balance != 0){
            paidAmount += (balance - interestNum);
            balance = 0;
            println("Month: " + months + "\tBal: " + balance + "\t\tPaid: " + paidAmount);
        }
    }
}