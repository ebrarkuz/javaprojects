public class Stationery extends Items{
    private final String kind;

    public String getKind() {
        return kind;
    }


    public Stationery(String name, int barcode, double price, String kind) {
        super(name, barcode, price);
        this.kind = kind;
    }
    public String toString() {
        return "Kind of the "+getName()+" is "+getKind()+". Its barcode is "+getBarcode()+" and its price is "+getPrice();
    }}



