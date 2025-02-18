import java.util.ArrayList;
import java.util.List;

public class CreateSystem {
    private static List<Items> system;

    /**
     * find the command and create the system according to command
     * @param inputLines the lines from input file as a String array
     */

    public static void readCommend(String[] inputLines) {
        system = new ArrayList<>();
        for (String line : inputLines) {
            String[] tokens = line.split("\t");
            String command = tokens[0];

            switch (command) {
                case "ADD":
                    addMethod(tokens);
                    break;

                case "DISPLAY":
                    displayMethod();
                    break;

                case "REMOVE":
                    int removeBarcode = Integer.parseInt(tokens[1]);
                    removeMethod(removeBarcode);
                    break;

                case "SEARCHBYBARCODE":
                    int searchBarcode = Integer.parseInt(tokens[1]);
                    searchMethod("barcode", searchBarcode);
                    break;

                case "SEARCHBYNAME":
                    String searchName = tokens[1];
                    searchMethod("name", searchName);
                    break;

                default:
                    System.out.println("Invalid command: " + command);
                    break;
            }
        }
    }

    /**
     * a method for add command it adds the item to the system
     * @param tokens elements of the input line it includes the command and characteristic of the item
     */
    private static void addMethod(String[] tokens) {
        String itemType = tokens[1];
        String name = tokens[2];
        String attribute = tokens[3];
        int barcode = Integer.parseInt(tokens[4]);
        double price = Double.parseDouble(tokens[5]);
        Items item = createObject(itemType, name, attribute, barcode, price);

        if (item != null) {
            system.add(item);

        } else {
            System.out.println("Invalid item type: " + itemType);
        }
    }

    /**
     *create a item according to it types
     * @param itemType type of the item
     * @param name name of the item
     * @param changeble the private characteristic of the item it can  author ,color and kind
     * @param barcode the barcode number of the item
     * @param price the price of the item
     * @return
     */
    private static Items createObject(String itemType, String name, String changeble, int barcode, double price) {
        switch (itemType) {
            case "Book":
                return new Book(name, barcode, price, changeble);
            case "Toy":
                return new Toy(name, barcode, price, changeble);
            case "Stationery":
                return new Stationery(name, barcode, price, changeble);
            default:
                return null;
        }
    }

    /**
     * a method for display command
     */
    private static void displayMethod() {
        System.out.println("INVENTORY:");
        displayItemsOfType(Book.class);
        displayItemsOfType(Toy.class);
        displayItemsOfType(Stationery.class);
        System.out.println("------------------------------");
    }

    /**
     * it is a generic method to use toString methods of different types of items
     * @param type type of the item
     * @param <E> class of the item
     */
    private static <E extends Items> void displayItemsOfType(Class<E> type) {
        for (Items item : system) {
            if (type.isInstance(item)) {
                System.out.println(item);
            }
        }
    }

    /**
     * remove the item from system according to barcode number
     * @param barcode barcode number of the item
     */
    private static void removeMethod(int barcode) {
        System.out.println(
                "REMOVE RESULTS:");
        boolean removed = system.removeIf(i -> i.getBarcode() == barcode);
        if (removed) {
            System.out.println("Item is removed.");
        } else {
            System.out.println("Item is not found.");
        }
        System.out.println("------------------------------");
    }

    /**
     * a generic method to find the item according to barcode or name
     * @param <E> The type of the criteria value, which can be either an Integer for barcode search or a String for name search.
     * @param searchType The type of criteria to search by. Acceptable values are "barcode" or "name".
     * @param criteriaValue The value of the criteria to search for. Should be an Integer if searching by barcode or a String if searching by name.

     */

    private static <E> void searchMethod(String searchType, E criteriaValue) {
        boolean found = false;
        System.out.println("SEARCH RESULTS:");
        for (Items item : system) {
            if ((searchType.equals("barcode") && criteriaValue instanceof Integer && item.getBarcode() == (Integer) criteriaValue) ||
                    (searchType.equals("name") && criteriaValue instanceof String && item.getName().equalsIgnoreCase((String) criteriaValue))) {
                System.out.println(item);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item is not found.");
        }
        System.out.println("------------------------------");
    }
}