package smartcoffee.smartcoffee;

/** Giao diện cho sản phẩm có hạn sử dụng / cần kiểm tra độ tươi. */
public interface Perishable {
    boolean isFresh();
    /** @return true nếu sản phẩm ĐÃ HẾT HẠN. */
    boolean checkExpiry();
}
