/*
Transactions made by nursery
 */
public class Transaction {
    private double amount;
    private String paymentType;
    private int delay;

   public Transaction(double amount, String paymentType, int delay){
        this.amount = amount;
        this.paymentType = paymentType;
        this.delay = delay;
    }

    public double getAmount() { return this.amount; }

    public String getPaymentType() { return this.paymentType; }

    public int getDelay() { return this.delay; }

    public void setDelay(int d) { this.delay = d; }

    @Override
    public String toString() {
        return ("Transaction" +
                "\n----------------" +
                "\nAmount: \t" + getAmount() +
                "\nPayment Type: \t" + getPaymentType() +
                "\nDelay: \t" + getDelay());
    }
}
