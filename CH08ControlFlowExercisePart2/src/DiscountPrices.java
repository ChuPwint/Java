import acm.program.ConsoleProgram;

public class DiscountPrices extends ConsoleProgram {
        public void run() {
                //setFont("JetBrains Mono NL-26");
                final int CUT_OFF_AMT = 1000;
                int amtPurchased = readInt("Enter amount of purchase (in cents):");

                int discount = 0;
                if (amtPurchased > 1000) {
                        discount  = 1 * (amtPurchased/10);
                } else {
                        discount  = (amtPurchased/20);
                }
                int discountedPrice = amtPurchased - discount;
                println("Discounted price: " + discountedPrice);

        }
}
