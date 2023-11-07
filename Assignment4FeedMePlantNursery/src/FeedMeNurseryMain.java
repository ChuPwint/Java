/*
Main part of the application handling menus and sub menus loop
 */
import acm.program.ConsoleProgram;

public class FeedMeNurseryMain extends ConsoleProgram {

        Nursery n;
        Transaction buyT;

        public int sIndex;
        public int detailIndex;
        public int bIndex;
        public static final String CHARACTER_START = "->";
        public static final String CONFIRM_PURCHASE = "confirm";
        public static final String CANCEL_PURCHASE = "cancel";

        public void run() {
                initialState();
                println("******** Welcome to the App! ********");
                menu();
        }

        public void initialState(){
                setFont("JetBrains Mono NL-20");

                n = new Nursery("FeedMePlant", 10000);
                n.setUpTree();
        }

        public void menu(){
                while(true) {
                        String choice = readLine("Enter (S/B/D/T/C/BD/SD/N/E) or Enter '?' to see menu explanations: ");
                        if(choice.equalsIgnoreCase("S")) {
                                sellMenu();
                        }else if(choice.equalsIgnoreCase("B")) {
                                buyMenu();
                        }else if(choice.equalsIgnoreCase("D")) {
                                displayAllStockMenu();
                        }else if(choice.equalsIgnoreCase("T")) {
                                displaySingleTreeMenu();
                        }else if(choice.equalsIgnoreCase("C")) {
                                displayCapitalMenu();
                        }else if(choice.equalsIgnoreCase("N")) {
                                changeToNewDayMenu();
                        }else if(choice.equalsIgnoreCase("BD")) {
                                displayAllBuyTrans();
                        }else if(choice.equalsIgnoreCase("SD")) {
                                displayAllSellTrans();
                        }else if(choice.equalsIgnoreCase("E")) {
                                exitMenu();
                                break;
                        }else if(choice.equals("?")){
                                explainMenuChoices();
                        }else {
                                String redo = readLine("No such choice. Do u want to redo? (y to redo/ exit app if any other char): ");
                                if(redo.equalsIgnoreCase("Y")) {
                                        println();
                                        menu();
                                }else {
                                        exitMenu();
                                        break;
                                }
                        }
                }
        }

        public void sellMenu() {
                println("Sell Menu");
                println("---------------");
                String tree = readLine("Enter type of tree: ");
                while(tree.isBlank() || tree.length() <= 2) {
                        println("Please enter possible input!");
                        tree = readLine("Enter type of tree: ");
                }
                boolean hasTree = checkTree(tree);
                if(hasTree){
                        println(CHARACTER_START + "One " + tree + " tree costs " + n.getTrees().get(sIndex).getSellPrice());
                        sellTree(sIndex);
                }else {
                        println("!!!!No such tree in our stock!");
                }

        }

        public boolean checkTree(String t) {
                boolean hasT = false;
                for (int i = 0; i < n.getTreeSize(); i++) {
                        String existingT = n.getTrees().get(i).getType();
                        if(existingT.equals(t)){
                                sIndex = i;
                                detailIndex = i;
                                bIndex = i;
                                hasT = true;
                        }
                }
                return hasT;
        }

        public void sellTree(int index) {
                int qty = readInt("How many?: ");
                boolean isNegative = checkNegatives(qty);
                if(isNegative || qty == 0) {
                        println("Please enter a valid value more than 0.");
                        sellTree(index);
                }else {
                        int existingStock = n.getTrees().get(index).getStock();
                        if(qty <= existingStock) {
                                println(CHARACTER_START + "We have enough stock.");
                                confirmOrCancelPurchase(existingStock, qty, index);
                        }else {
                                println(CHARACTER_START + "Sorry, we only have " + existingStock + " left.");
                                continuePurchaseOrNot();
                        }
                }
        }

        public boolean checkNegatives(int s){
                boolean isN = false;
                if(s < 0) { isN = true; }
                return isN;
        }

        public void continuePurchaseOrNot() {
                String c = readLine("Continue to purchase more? If yes, enter(y/Y): ");
                if(c.equalsIgnoreCase("y")) {
                        sellMenu();
                }else {
                        println("Exiting Sell-Menu and Returning to Main Menu.");
                        println("-------------------------------------------");
                }
        }

        public void confirmOrCancelPurchase(int existingS, int buy, int index) {
                String ans = readLine("Enter confirm/cancel for purchase: ");
                if(ans.equals(CONFIRM_PURCHASE)) {
                        sellCheckOut(existingS, buy, index);
                }else if(ans.equals(CANCEL_PURCHASE)) {
                        println("Canceling the purchase. Returning to Main Menu.");
                        println("----------------------------------------------");
                        menu();
                } else {
                        println("Unknown input. Returning to Main Menu.");
                        println("----------------------------------------------");
                }
        }

        public void sellCheckOut(int existingS, int buy, int index) {
                double total = buy * n.getTrees().get(index).getSellPrice();
                println(CHARACTER_START + "Total: " + total);
                String pt = readLine("Type of payment(cash/credit): ");
                Transaction sellT;
                if(pt.equalsIgnoreCase("Cash")) {
                        sellT = new Transaction(total, pt, 0);
                        n.setBalance(n.getBalance() + total);
                        n.addSellTrans(sellT);

                        println(sellT);
                        int remainingStock = existingS - buy;
                        n.getTrees().get(index).updateStock(remainingStock);
                        println(CHARACTER_START + "Purchase finished.");

                }else if(pt.equalsIgnoreCase("Credit")) {
                        sellT = new Transaction(total, pt, 4);
                        n.addSellTrans(sellT);

                        println(sellT);
                        int remainingStock = existingS - buy;
                        n.getTrees().get(index).updateStock(remainingStock);
                        println(CHARACTER_START + "Purchase finished.");
                }else {
                        println(CHARACTER_START + "This type of payment is not valid.");
                }
                continuePurchaseOrNot();
        }

        public void buyMenu() {
                println("Buy Menu");
                println("--------------");
                String buyTree = readLine("Enter type of tree: ");
                while(buyTree.isBlank()) {
                        println("Field cannot be empty. Please re-enter.");
                        buyTree = readLine("Enter type of tree: ");
                }
                buyTreeProcess(buyTree);
        }

        public void buyTreeProcess(String b) {
                boolean alrHasTree = checkTree(b);
                if(alrHasTree){
                        Tree z = n.getTrees().get(bIndex);
                        int item = readInt("Enter quantity: ");
                        boolean isNegative1 = checkNegatives(item);
                        if(isNegative1 || item == 0) {
                                println("Please enter valid value more than 0!");
                                buyTreeProcess(b);
                        }else {
                                double buyPrice = readDouble("Enter buy price: ");
                                double totalCost = item * buyPrice;
                                buyCheckOut(totalCost, buyPrice, item, z);
                        }
                }else {
                        String seasonT = readLine("Enter season of tree: ");
                        double buyPrice = readDouble("Enter buy price: ");
                        double sellPrice = readDouble("Enter sell price: ");
                        int item = readInt("Enter quantity: ");
                        boolean isNegative2 = checkNegatives(item);
                        if(isNegative2 || item == 0) {
                                println("Please enter a valid value more than 0!");
                                buyTreeProcess(b);
                        }else {
                                double totalCost = item * buyPrice;
                                buyCheckOut1(totalCost, b, seasonT, buyPrice, sellPrice, item);
                        }
                }
        }

        public void buyCheckOut1(double cost, String b, String s, double bp, double sp, int item) {
                println(CHARACTER_START + "Total: " + cost);
                String pt = readLine("Type of payment(cash/credit): ");
                if(pt.equalsIgnoreCase("Cash")) {
                        buyT = new Transaction(cost, pt, 0);
                        if(cost > n.getBalance()) {
                                println("Sorry, not enough balance.");
                        }else {
                                n.setBalance(n.getBalance() - cost);
                                n.addBuyTrans(buyT);

                                println(buyT);
                                println(CHARACTER_START + "Purchase finished.");
                                Tree bt = new Tree(b, s, bp, sp, item);
                                n.addTree(bt);
                                println("Tree has been added.");
                        }
                }else if(pt.equalsIgnoreCase("Credit")) {
                        buyT = new Transaction(cost, pt, 4);
                        n.addBuyTrans(buyT);

                        println(buyT);
                        println(CHARACTER_START + "Purchase finished.");
                        Tree bt = new Tree(b, s, bp, sp, item);
                        n.addTree(bt);
                        println("Tree has been added.");
                }else {
                        println(CHARACTER_START + "This type of payment is not valid.");
                }
        }

        public void buyCheckOut (double cost, double bp, int item, Tree z){
                println(CHARACTER_START + "Total: " + cost);
                String pt = readLine("Type of payment(cash/credit): ");
                if(pt.equalsIgnoreCase("Cash")) {
                        buyT = new Transaction(cost, pt, 0);
                        n.setBalance(n.getBalance() - cost);
                        n.addBuyTrans(buyT);
                        println(buyT);
                        println(CHARACTER_START + "Purchase finished.");
                        z.updateStock(z.getStock() + item);
                        double p = z.getBuyPrice();
                        if(bp == p) {
                                println("Tree stock added.");
                        } else {
                                z.updateBuyPrice(bp);
                                println("Tree stock added and updated buy price.");
                        }
                }else if(pt.equalsIgnoreCase("Credit")) {
                        buyT = new Transaction(cost, pt, 4);
                        n.addBuyTrans(buyT);
                        println(buyT);
                        println(CHARACTER_START + "Purchase finished.");
                        z.updateStock(z.getStock() + item);
                        double p = z.getBuyPrice();
                        if(bp == p) {
                                println("Tree stock added.");
                        } else {
                                z.updateBuyPrice(bp);
                                println("Tree stock added and updated buy price.");
                        }
                }else {
                        println(CHARACTER_START + "This type of payment is not valid.");
                }
        }

        public void displayAllStockMenu() {
                println("List of all the stocks on  hand");
                println("---------------------------------------------");
                for (int i = 0; i < n.getTreeSize(); i++) {
                        println((i+1) + ". " + n.getTrees().get(i).getType() + ": \t" + n.getTrees().get(i).getStock() +
                                "\t(Sell Price: " + n.getTrees().get(i).getSellPrice() + ") ");
                        println();
                }
                println("---------------------------------------------");
        }

        public void displaySingleTreeMenu() {
                String t = readLine("Enter tree type to display: ");
                boolean hasTree1 = checkTree(t);
                if(hasTree1) {
                        println("Details of " + t + " tree");
                        println("---------------------------");
                        Tree a = n.getTrees().get(detailIndex);
                        println(a);
                }else {
                        println("Display cannot be done because tree not exist in stock.");
                }
        }

        public void displayCapitalMenu() {
                double capital = n.getBalance() + n.getSellCreditTotalAmount() - n.getBuyCreditTotalAmount();
                double expense = n.getBuyCreditTotalAmountOut();
                double income = n.getSellCreditTotalAmountIncoming();
                println("Current capital amount: " + capital);
                println("Expense credits: " + expense);
                println("Income credits: " + income);

        }

        public void displayAllBuyTrans() {
                println("All buying Transaction objects: ");
                for (int i = 0; i < n.getBuyTransListSize(); i++) {
                        Transaction buyingT = n.getBuyTrans().get(i);
                        println(buyingT);
                        println("-----------");
                }
        }

        public void displayAllSellTrans() {
                println("All sold Transaction objects: ");
                for (int i = 0; i < n.getSellTransListSize(); i++) {
                        Transaction soldT = n.getSellTrans().get(i);
                        println(soldT);
                        println("-----------");
                }
        }

        public void changeToNewDayMenu() {
                println("----> A new day! <-----");
                n.changeDelayOnSaleCredits();
                n.changeDelayOnBuyCredits();
        }

        public void exitMenu() {
                println("******** Exiting the app! See you next time! ********");
        }

        public void explainMenuChoices() {
                println("Menu explanation");
                println("-----------------------");
                println("S - sell any tree(s)");
                println("B - buy any tree(s)");
                println("D - display list of all the stocks on hand");
                println("T - display details of a single tree");
                println("C - show capital of the nursery at this instance in time");
                println("BD - display all bought transactions");
                println("SD - display all sold transactions");
                println("N - change to a new day");
                println("E - exit the app");
                println("---------------------------------");
        }
}
