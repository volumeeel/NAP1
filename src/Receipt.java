import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<Item> items = new ArrayList<>();
    private double discountPercent;

    public Receipt(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() { return items; }

    public double getSubtotal() {
        double sum = 0;
        for (Item item : items) {
            sum += item.getTotal();
        }
        return sum;
    }

    public double getDiscount() {
        return getSubtotal() * discountPercent / 100;
    }

    public double getTotal() {
        return getSubtotal() - getDiscount();
    }

    public double getDiscountPercent() { return discountPercent; }
}