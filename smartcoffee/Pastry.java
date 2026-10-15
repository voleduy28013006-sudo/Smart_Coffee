package smartcoffee.smartcoffee;

import java.time.LocalDate;             /** lấy ngày hiện tại */

/** Bánh ngọt / đồ ăn kèm: có hạn sử dụng trong ngày, có thể cần bảo quản mát. */
public class Pastry extends Product implements Perishable {
    private final LocalDate expiryDate;
    private final boolean refrigerated;

    public Pastry(String id, String name, long price, int stock, LocalDate expiryDate, boolean refrigerated) {
        super(id, name, price, stock);
        this.expiryDate = expiryDate;
        this.refrigerated = refrigerated;
    }

    @Override
    public boolean checkExpiry() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public boolean isFresh() {
        return !checkExpiry();
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isRefrigerated() {
        return refrigerated;
    }

    @Override
    public long calculatePrice() {
        return getBasePrice();
    }

    @Override
    public String info() {
        return "Pastry " + header() + " | HSD: " + expiryDate
                + " | Bảo quản mát: " + (refrigerated ? "có" : "không")
                + " | " + (isFresh() ? "CÒN TƯƠI" : "HẾT HẠN")
                + " | Giá bán: " + calculatePrice() + " VND";
    }
}
