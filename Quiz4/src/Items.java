
public class Items {
    private final String name;
    private final int barcode;
    private final double price;

    public String getName() {
        return name;
    }

    public int getBarcode() {
        return barcode;
    }


    public double getPrice() {
        return price;
    }

    public Items(String name, int barcode, double price) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }
}
