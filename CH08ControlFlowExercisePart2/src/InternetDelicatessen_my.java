import acm.program.ConsoleProgram;

public class InternetDelicatessen_my extends ConsoleProgram {
    public void run(){
        String item = readLine("Enter the item: ");
        double cent_price = readDouble("Enter the price: ");
        int isOvernightDelivery = readInt("Overnight delivery (0==no, 1== yes) : ");
        double deliveryFee = 0.00;
        double total = 0.0;
        if(cent_price < 10 && isOvernightDelivery == 1){
            deliveryFee = 5.00;
        }else if (cent_price < 10 && isOvernightDelivery == 0){
            deliveryFee = 2.00;
        }else if (cent_price >= 10 && isOvernightDelivery == 1){
            deliveryFee = 3.00;
        }else if (cent_price >= 10 && isOvernightDelivery == 0){
            deliveryFee = 0.00;
        }else{
            println("Some input is wrong.");
        }
        total = deliveryFee + cent_price;
        println("Invoice: ");
        println("\t" + item + "\t" + cent_price);
        println("\tDelivery" + "\t" + deliveryFee);
        println("\tTotal" + "\t" + total);
    }
}
