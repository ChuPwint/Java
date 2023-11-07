/*
Trees which nursery buy and sell
 */
public class Tree {
    private String type;
    private String season;
    private double buyPrice;
    private double sellPrice;
    private int stock;

    public Tree(String type, String season, double buyPrice, double sellPrice, int stock){
        this.type = type;
        this.season = season;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
    }

    public String getType() { return this.type; }

    public String getSeason() { return this.season; }

    public double getBuyPrice() { return this.buyPrice; }

    public double getSellPrice() { return this.sellPrice; }

    public int getStock() { return this.stock; }

    public void updateBuyPrice(double b) { this.buyPrice = b; }

    public void updateStock(int s) { this.stock = s; }

    @Override
    public String toString() {
        return ("Type of tree: \t" + getType() +
                "\nSeason: \t" +  getSeason() +
                "\nBuy Price: \t" + getBuyPrice() +
                "\nSell Price: \t" + getSellPrice() +
                "\nStock: \t" + getStock());
    }
}
