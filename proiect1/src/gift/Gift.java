package gift;

import enums.Category;

public class Gift {
    private String productName;
    private Double price;
    private Category category;

    public Gift(final String productName, final Double price,
                final Category category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }


}
