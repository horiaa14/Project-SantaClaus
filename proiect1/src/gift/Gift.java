package gift;

import enums.Category;
import fileio.GiftInputData;

public class Gift {
    private final String productName;
    private final Double price;
    private final Category category;

    public Gift(final String productName, final Double price,
                final Category category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    public Gift(final GiftInputData currGift) {
        this.productName = currGift.getProductName();
        this.price = currGift.getPrice();
        this.category = currGift.getCategory();
    }
}
