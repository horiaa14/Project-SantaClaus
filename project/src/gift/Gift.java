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

    public final String getProductName() {
        return productName;
    }

    public Gift(final Gift gift) {
        this.productName = gift.productName;
        this.price = gift.price;
        this.category = gift.category;
    }

    public final Category getCategory() {
        return category;
    }

    public final Double getPrice() {
        return price;
    }

    public Gift(final GiftInputData currGift) {
        this.productName = currGift.getProductName();
        this.price = currGift.getPrice();
        this.category = currGift.getCategory();
    }
}
