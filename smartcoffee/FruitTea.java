package smartcoffee.smartcoffee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Trà trái cây: có thể thêm topping. */
public class FruitTea extends Product implements Customizable {
    private final List<Topping> toppings = new ArrayList<>();

    public FruitTea(String id, String name, long price, int stock) {
        super(id, name, price, stock);
    }

    @Override
    public void addTopping(Topping topping) {
        if (topping == Topping.EXTRA_SHOT) {
            throw new IllegalArgumentException("FruitTea không nhận EXTRA_SHOT");
        }
        toppings.add(topping);
    }

    @Override
    public long getExtraFee() {
        long sum = 0;
        for (Topping t : toppings) sum += t.getPrice();
        return sum;
    }
    /** trả về danh sách topping như không được phép chỉnh sửa */
    public List<Topping> getToppings() {
        return Collections.unmodifiableList(toppings);
    }

    @Override
    public long calculatePrice() {
        return getBasePrice() + getExtraFee();
    }

    @Override
    public String info() {
        StringBuilder tp = new StringBuilder();
        for (Topping t : toppings) {
            if (tp.length() > 0) tp.append(", ");
            tp.append(t.getLabel());
        }
        return "FruitTea " + header() + " | Topping: " + (tp.length() == 0 ? "(không)" : tp)
                + " | Giá bán: " + calculatePrice()+ " VND";
    }
}
