package smartcoffee.smartcoffee;
/* khach vip: giam 15% tien mon va mien phi phi giao hang */

public class VipCustomer extends Customer {
    private static final double DISCOUNT_RATE = 0.15;

    private final String memberCode;

    public VipCustomer(String id, String name, String phone, String memberCode) {
        super(id, name, phone);
        this.memberCode = memberCode;
    }

    public String getMemberCode() {
        return memberCode;
    }

    @Override
    public double getDiscountRate() {
        return DISCOUNT_RATE;
    }
    @Override
    public long applyShippingPolicy (long standardFee) {
        return 0; // mien phi phi giao hang
    }

    @Override
    public String toString() {
        return super.toString() + "Ma hoi vien: " + memberCode;
    }
}
