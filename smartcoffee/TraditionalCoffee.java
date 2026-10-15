package smartcoffee.smartcoffee;

/** Cà phê truyền thống: chọn mức đường/đá, giá cố định. */
public class TraditionalCoffee extends Product {
    private final SugarIceLevel sugar;
    private final SugarIceLevel ice;

    public TraditionalCoffee(String id, String name, long price, int stock, SugarIceLevel sugar, SugarIceLevel ice){
        super(id, name, price, stock);
        this.sugar=sugar;
        this.ice=ice;
    }

    @Override
    public long calculatePrice() {
        return getBasePrice();
    }

    @Override
    public String info() {
        return "TraditionalCoffee " + header() + " | Đường: " + sugar + " | Đá: " + ice
                + " | Giá bán: " + calculatePrice() +" VND";
    }
}
