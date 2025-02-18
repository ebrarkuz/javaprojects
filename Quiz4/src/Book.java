public class Book extends Items{
    private final String author;

    public String getAuthor() {
        return author;
    }

    public Book(String name, int barcode, double price, String author) {
        super(name, barcode, price);
        this.author = author;
    }

    @Override
    public String toString() {
        return "Author of the "+getName()+" is "+getAuthor()+". Its barcode is "+getBarcode()+" and its price is "+getPrice();
    }
}
