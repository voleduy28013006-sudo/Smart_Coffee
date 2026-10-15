package smartcoffee.smartcoffee;

public enum PaymentMethod {
    CASH (" Tiền mặt "),
    BANK_TRANSFER ("Chuyển khoản"),
    CARD("Thẻ");

    private final String label;
    PaymentMethod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString(){
        return label;
    }
}
