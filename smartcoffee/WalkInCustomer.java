package smartcoffee.smartcoffee;

public class WalkInCustomer extends Customer {
   /*khach vang lai: khong giam gia, tra du phi giao hang*/
    public WalkInCustomer(String id, String name, String phone){
        super(id, name , phone);
    }
    @Override
    public double getDiscountRate(){
        return 0.0;
    }
    @Override
    public long applyShippingPolicy(long standardFee){
        return standardFee; //tra du phi chuan
    }
}
