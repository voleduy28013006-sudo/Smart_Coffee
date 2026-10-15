package smartcoffee.smartcoffee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/* hoa don chi tao don hang : COMPLETED, luu lai so tien va phuong thuc thanh toan tai thoi diem xuat hoa don*/

public class Invoice {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm");

    private final String id;
    private final CoffeeOrder order;
    private LocalDateTime issuedAt;
    private final long subtotal;
    private final long shippingFee;
    private final long customerDiscount;
    private final long total;
    private final PaymentMethod Paymentmethod;

    public Invoice(String id, CoffeeOrder order, PaymentMethod paymentMethod, localDateTime issuedAt){
        if (order.getStatus() != OrderStatus.COMPLETED) {
            throw new OrderException ("chi xuat hoa don cho don COMPLETED");
        }
        if (paymentMethod == null) {
            throw new OrderException ("phai chon phuong thuc thanh toan");
        }
        this.id = id;
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.issuedAt = issuedAt;
        this.subtotal = subtotal();
        this.shippingFee = shippingFee();
        this.CustomerDiscount = CustomerDiscount();
        this.total = order.total();
    }
    /* xuat hoa don */
    public Invoice(String id, CoffeeOrder order, PaymentMethod paymentMethod){
        this(id,order,paymentMethod, LocalDateTime.now());
    }

    public String getId() {
        return id;
    }

    public CoffeeOrder getOrder() {
        return order;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }
    public long getSubtotal(){
        return subtotal;
    }

    public long getShippingFee(){
        return shippingFee;
    }
    public long getCustomerDiscount(){
        return customerDiscount;
    }
    public long getTotal(){
        return total;
    }

    public PaymentMethod getPaymentmethod() {
        return Paymentmethod;
    }

    /* in hoa don ra man hinh*/
    public void print(){
        System.out.println("========== HÓA ĐƠN " + id + " ==========");
        System.out.println("Xuất lúc      : " + issuedAt.format(FMT));
        System.out.println("Tiền món      : " + Money.fmt(subtotal));
        System.out.println("Phí giao hàng : " + Money.fmt(shippingFee));
        System.out.println("Giảm giá      : -" + Money.fmt(customerDiscount));
        System.out.println("TỔNG CỘNG     : " + Money.fmt(total));
        System.out.println("Thanh toán    : " + paymentMethod);
    }
    @Override
    public String toString() {
        return "[" + id + "] " + issuedAt.format(FMT) + " | Tổng: " + Money.fmt(total) + " | " + paymentMethod;
    }
}
