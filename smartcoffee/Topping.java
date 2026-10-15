package smartcoffee.smartcoffee;

/** Tùy chọn thêm (topping / shot) kèm giá. EXTRA_SHOT dành cho EspressoDrink. */
public enum Topping {
    JELLY("Thạch", 5000),
    POMELO_SEEDS("Hạt bưởi", 7000),       /** các hằng số enum bắt buộc phải code đầu tiên*/
    TAPIOCA("Trân châu", 8000),        /**  dấu ; cuối cùng để báo hiệu là kết thúc hằng số */
    EXTRA_SHOT("Shot Espresso thêm", 10000);

    private final String label;
    private final long price;

    Topping(String label, long price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    public long getPrice() {
        return price;
    }
}
