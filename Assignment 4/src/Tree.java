public class Tree {
    private String type;
    private String season;
    private double buyPrice;
    private double sellPrice;
    private int stock;

    public Tree(){
        this("", "", 0.0, 0.0, 0);
    }

    public Tree(String type, String season, double buyPrice, double sellPrice, int stock){
        this.type = type;
        this.season = season;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
    }

    public String getType() { return type; }

    public String getSeason() { return season; }

    public double getBuyPrice() { return buyPrice; }

    public double getSellPrice() { return sellPrice; }

    public int getStock() { return stock; }

    public void updateTreeType (String p) { this.type = p; }

    public void updateSeason (String p) { this.season = p; }

    public void updateBuyPrice(double p) { this.buyPrice = p; }

    public void updateSellPrice(double p) { this.sellPrice = p; }

    public void updateStock(int s) { this.stock += s; }

    public void displayTree(){
        System.out.println("Type of tree: " + type);
        System.out.println("Season: " + season);
        System.out.println("Buy Price: " + buyPrice);
        System.out.println("Sell Price: " + sellPrice);
        System.out.println("Stock: " + stock);
    }
}