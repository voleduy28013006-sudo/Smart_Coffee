package smartcoffee.smartcoffee;

/** Chi nhánh trong chuỗi cửa hàng. */
public class Branch {
    private final String id;
    private final String name;
    private final String address;

    public Branch(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    @Override
    public String toString() {
        return "[" + id + "] " + name + " – " + address;
    }
}
