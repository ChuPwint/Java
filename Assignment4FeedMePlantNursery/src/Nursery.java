/*
Nursery
 */
import java.util.ArrayList;

public class Nursery {
    private String name;
    private double balance;
    private ArrayList<Tree> trees;
    private ArrayList<Transaction> sellTrans;
    private ArrayList<Transaction> buyTrans;

    public Nursery(String name, double balance){
        this(name, balance, null, null, null);
    }

    public Nursery(String name, double balance, ArrayList<Tree> a, ArrayList<Transaction> b, ArrayList<Transaction> c) {
        this.name = name;
        this.balance = balance;
        this.trees = new ArrayList<Tree> ();
        this.sellTrans = new ArrayList<Transaction> ();
        this.buyTrans = new ArrayList<Transaction> ();
    }

    public double getBalance() { return this.balance; }

    public void addTree(Tree b) {
        this.trees.add(b);
    }

    public void addSellTrans(Transaction b) {
        this.sellTrans.add(b);
    }

    public void addBuyTrans(Transaction b) {
        this.buyTrans.add(b);
    }

    public void setBalance(double b) { this.balance = b; }

    public void setUpTree() {
        Tree a = new Tree("apple", "summer", 150, 170, 10);
        Tree b = new Tree("orange", "winter", 170, 190, 8);
        Tree c = new Tree("plum", "spring", 350, 380, 7);
        Tree d = new Tree("apricot", "summer", 500, 550, 6);

        this.trees = new ArrayList<>();
        addTree(a);
        addTree(b);
        addTree(c);
        addTree(d);
    }

    public int getTreeSize() {
        return this.trees.size();
    }

    public ArrayList<Tree> getTrees() {
        return this.trees;
    }

    public ArrayList<Transaction> getSellTrans() { return this.sellTrans; }

    public int getSellTransListSize() {
        return this.sellTrans.size();
    }

    public ArrayList<Transaction> getBuyTrans() { return this.buyTrans; }

    public int getBuyTransListSize() {
        return this.buyTrans.size();
    }

    public double getSellCreditTotalAmount() {
        double sum1 = 0;

        for (int i = 0; i < sellTrans.size(); i++) {
            String p1 = sellTrans.get(i).getPaymentType();
            if(p1.equals("Credit") || p1.equals("credit")){
                if(sellTrans.get(i).getDelay() == 0) { // got payment back
                    double s = sellTrans.get(i).getAmount();
                    sum1 += s;
                }
            }
        }
        return sum1;
    }

    public double getSellCreditTotalAmountIncoming() {
        double sum1 = 0;

        for (int i = 0; i < sellTrans.size(); i++) {
            String p1 = sellTrans.get(i).getPaymentType();
            if(p1.equals("Credit") || p1.equals("credit")){
                if(sellTrans.get(i).getDelay() > 0) { //incoming payment
                    double s = sellTrans.get(i).getAmount();
                    sum1 += s;
                }
            }
        }
        return sum1;
    }

    public double getBuyCreditTotalAmount() {
        double sum2 = 0;
        for (int j = 0; j < buyTrans.size(); j++) {
            String p2 = buyTrans.get(j).getPaymentType();
            if(p2.equals("Credit") || p2.equals("credit")){
                if(buyTrans.get(j).getDelay() == 0) { //transfer money to the supplier has been done when delay = 0
                    double b = buyTrans.get(j).getAmount();
                    sum2 += b;
                }
            }
        }
        return sum2;
    }

    public double getBuyCreditTotalAmountOut() {
        double sum2 = 0;
        for (int j = 0; j < buyTrans.size(); j++) {
            String p2 = buyTrans.get(j).getPaymentType();
            if(p2.equals("Credit") || p2.equals("credit")){
                if(buyTrans.get(j).getDelay() > 0) {
                    double b = buyTrans.get(j).getAmount();
                    sum2 += b;
                }
            }
        }
        return sum2;
    }

    public void changeDelayOnSaleCredits() {
        Transaction s;
        for (int i = 0; i < sellTrans.size(); i++) {
            String p1 = sellTrans.get(i).getPaymentType();
            if(p1.equals("Credit") || p1.equals("credit")){
                s = sellTrans.get(i);
                int d1 = s.getDelay();
                if(d1 > 0) {
                    s.setDelay(d1 - 1);
                }
            }
        }
    }

    public void changeDelayOnBuyCredits() {
        Transaction b;
        for (int j = 0; j < buyTrans.size(); j++) {
            String p2 = buyTrans.get(j).getPaymentType();
            if(p2.equals("Credit") || p2.equals("credit")){
                b = buyTrans.get(j);
                int d2 = b.getDelay();
                if(d2 > 0) {
                    b.setDelay(d2 - 1);
                }
            }
        }
    }
}
