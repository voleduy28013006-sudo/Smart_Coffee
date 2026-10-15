package smartcoffee.smartcoffee;

/** Cà phê pha máy: có thể thêm shot Espresso (cộng phí/shot). */
public class EspressoDrink extends Product implements Customizable {
    private int extraShots;
    public EspressoDrink(String id, String name, long price, int stock) {
        super(id, name, price, stock);
    }

    @Override
    public void addTopping(Topping topping) {
        if (topping != Topping.EXTRA_SHOT) {
            throw new IllegalArgumentException("EspressoDrink chỉ nhận EXTRA_SHOT");
        }
        extraShots++;
    }

    @Override
    public long getExtraFee() {
        return extraShots * Topping.EXTRA_SHOT.getPrice();
    }

    public int getExtraShots() {
        return extraShots;
    }

    @Override
    public long calculatePrice() {
        return getBasePrice() + getExtraFee();
    }

    @Override
    public String info() {
        return "EspressoDrink " + header() + " | Shot thêm: " + extraShots
                + " | Giá bán: " + calculatePrice() +" VND";
    }
}
