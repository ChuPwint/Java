import java.util.ArrayList;
import java.util.Objects;

public class Transaction {
    private double amount;
    private String paymentType;
    private int delay;

    public Transaction(){
        this(0.0, "cash", 0);
    }

    public Transaction(double amount, String paymentType, int delay){
        this.amount = amount;
        this.paymentType = paymentType;
        this.delay = delay;
    }

    public double getAmount() { return amount; }

    public String getPaymentType() { return paymentType; }

    public int getDelay() { return delay; }

    public void setAmount(double a) { this.amount = a; }

    public void setPaymentType(String a) { this.paymentType = a; }

    public void setDelay(int d) {
        if(paymentType.equals("credit")) {
            this.delay = 4;
        }else {
            this.delay = 0;
        }
    }
}
