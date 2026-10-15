package smartcoffee.smartcoffee;

public abstract class Product {
    private final String id;
    private final String name;
    private final long basePrice;
    private int stockQuantity;

    protected Product(String id, String name, long basePrice, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.stockQuantity = stockQuantity;
    }

    /** Đa hình: mỗi loại món có quy tắc tính giá riêng. */
    public abstract long calculatePrice();
    /** Đa hình: mỗi loại món hiển thị thông tin chi tiết riêng. */
    public abstract String info();

    public String getType() {
        return getClass().getSimpleName();
    }

                        /** ham lay thong tin */
    public String getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public long getBasePrice() {
        return basePrice;
    }
    public int getStockQuantity(){
        return stockQuantity;
    }
                        /** kiem tra xem con du de ban khong*/
    public void reduceStock(int quantity){
        if (quantity > stockQuantity) {
            throw new OrderException("Không đủ tồn kho để bán cho món: " +name);
        }
        stockQuantity-= quantity;
    }
    public void restoreStock(int quantity){
        stockQuantity+= quantity;
    }

    protected String header() {
        return String.format("[%s] %s | Giá gốc: %d VND | Tồn: %d",        /** String.format như là tạo một cái mẫu văn bản điền vào */
                id, name, basePrice, stockQuantity);                   /** chỗ trống, với %s là điền vào String, %d là điền vào số */
    }                                                                  /** phải sắp xếp đúng thứ tự xuất hiện */

    @Override
    public String toString() {
        return info();
    }
}
