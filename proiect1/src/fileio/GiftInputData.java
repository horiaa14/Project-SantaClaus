package fileio;

import enums.Category;

public final class GiftInputData {
    private final String productName;
    private final Double price;
    private final Category category;

    public GiftInputData(final String productName, final Double price,
                         final Category category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "GiftInputData{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
