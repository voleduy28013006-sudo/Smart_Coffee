package smartcoffee.smartcoffee;

/** Giao diện cho món có thể thêm topping / shot. */
public interface Customizable {
    void addTopping(Topping topping);
    long getExtraFee();
}
