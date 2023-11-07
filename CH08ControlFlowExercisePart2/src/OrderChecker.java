import acm.program.ConsoleProgram;

public class OrderChecker extends ConsoleProgram {
        public void run() {
                //setFont("JetBrains Mono NL-26");
                final int BOLT_PRICE = 5;
                final int NUT_PRICE = 3;
                final int WASHER_PRICE = 1;

                int boltCnt = readInt("Enter number of bolts: ");
                int nutCnt = readInt("Enter number of nuts: ");
                int washerCnt = readInt("Enter number of washer: ");

                if (!(nutCnt >= boltCnt)) {
                        println("Check the order. Too few nuts!");
                }

                if (!(washerCnt >= (2 * boltCnt))) {
                        println("Check the order. Too few washers!");
                }

                if(nutCnt >= boltCnt && washerCnt >= (2 * boltCnt)) {
                        println("Order is Okay!");
                }

                int totalCost = boltCnt * BOLT_PRICE + nutCnt * NUT_PRICE
                                + washerCnt * WASHER_PRICE;

                println("Total cost: " + totalCost);
        }
}
