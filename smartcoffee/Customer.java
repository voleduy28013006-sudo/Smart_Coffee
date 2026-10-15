package smartcoffee.smartcoffee;

/** khách hàng của chuỗi cửa hàng. */
public abstract class Customer {
    private final String id;
    private final String name;
    private final String phone;

    protected Customer(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public abstract double getDiscountRate();

    /** chính sách phí giao hàng so với mức phí tiêu chuẩn. */
    public abstract long applyShippingPolicy(long standardFee);

    /** Phân biệt khách VIP và vãng lai */
    public String getType() {
        return getClass().getSimpleName();   // getClass() -> in ra chữ class kèm đường dẫn gói -> VD: class smartcoffee.VIPCustomer
    }                                        // getSimplName -> cắt gọn lại thành VIPCustomer
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s – %s (%s) | Giảm giá: %.0f%%",
                id, name, phone, getType(), getDiscountRate() * 100);
    }
}
