package tumakha.supermarket.discount;

/**
 * Buy 2 (equals) items for a special price.
 *
 * @author Yuriy Tumakha
 */
class Buy2 implements Discount {

    private double discountPrice;

    Buy2(double discountPrice) {
        this.discountPrice = discountPrice;
    }

}
