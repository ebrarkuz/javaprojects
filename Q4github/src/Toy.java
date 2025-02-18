public class Toy extends Items {
    private final String color;

    public String getColor() {
        return color;
    }

    public Toy(String name, int barcode, double price, String color) {
        super(name, barcode, price);
        this.color = color;
    }
    public String toString() {
        return "Color of the "+getName()+" is "+getColor()+". Its barcode is "+getBarcode()+" and its price is "+getPrice();
}}
