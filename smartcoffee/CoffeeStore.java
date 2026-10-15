package smartcoffee.smartcoffee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Lớp quản lý trung tâm: thực đơn, khách hàng, voucher, chi nhánh, nhân viên, đơn hàng và báo cáo. */
public class CoffeeStore {
    private final List<Product> menu = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Voucher> vouchers = new ArrayList<>();
    private final List<Branch> branches = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private final List<CoffeeOrder> orders = new ArrayList<>();

    /** đống hàm thêm dữ liệu */
    public void addProduct(Product p) {
        menu.add(p);
    }
    public void addCustomer(Customer c) {
        customers.add(c);
    }
    public void addBranch(Branch b) {
        branches.add(b);
    }
    public void addEmployee(Employee e) {
        employees.add(e);
    }

    /** Getters */
    public List<Product> getMenu() { return Collections.unmodifiableList(menu); }
    public List<Customer> getCustomers() { return Collections.unmodifiableList(customers); }
    public List<Voucher> getVouchers() { return Collections.unmodifiableList(vouchers); }
    public List<Branch> getBranches() { return Collections.unmodifiableList(branches); }
    public List<Employee> getEmployees() { return Collections.unmodifiableList(employees); }
    public List<CoffeeOrder> getOrders() { return Collections.unmodifiableList(orders); }

    /** Tra cứu*/
    public Product findProduct(String id) {
        return menu.stream()
                    .filter(p -> p.getId().equals(id)).findFirst()
                    .orElseThrow(() -> new OrderException("Không tìm thấy sản phẩm " + id));
    }
    public Customer findCustomer(String id) {
        return customers.stream()
                        .filter(c -> c.getId().equals(id)).findFirst()
                        .orElseThrow(() -> new OrderException("Không tìm thấy khách hàng " + id));
    }
    public Voucher findVoucher(String code) {
        return vouchers.stream()
                        .filter(v -> v.getCode().equals(code)).findFirst()
                        .orElseThrow(() -> new OrderException("Không tìm thấy voucher " + code));
    }
    public Branch findBranch(String id) {
        return branches.stream()
                        .filter(b -> b.getId().equals(id)).findFirst()
                        .orElseThrow(() -> new OrderException("Không tìm thấy chi nhánh " + id));
    }
    public Employee findEmployee(String id) {
        return employees.stream()
                        .filter(e -> e.getId().equals(id)).findFirst()
                         .orElseThrow(() -> new OrderException("Không tìm thấy nhân viên " + id));
    }

    /** tạo đơn hàng */
    public CoffeeOrder createOrder(Customer customer, ServiceType type, Branch branch, Employee cashier) {
        CoffeeOrder order = new CoffeeOrder(customer, type, branch, cashier);
        orders.add(order);
        return order;
    }


    /** phần trong các câu truy vấn */
    // ---------- Tìm kiếm & thống kê ----------
    public List<Product> filterByPrice(long min, long max) {
        return menu.stream()
                .filter(p -> p.calculatePrice() >= min && p.calculatePrice() <= max)
                .collect(Collectors.toList());
    }

    /** Sản phẩm có tồn kho <= threshold, sắp tăng dần theo tồn kho. */
    public List<Product> getLowStockProducts(int threshold) {
        return menu.stream()
                .filter(p -> p.getStockQuantity() <= threshold)
                .sorted(Comparator.comparingInt(Product::getStockQuantity))
                .collect(Collectors.toList());
    }

    /** Top n món bán chạy nhất theo số lượng (chỉ tính đơn COMPLETED). */
    public List<Map.Entry<Product, Integer>> getTopSelling(int n) {
        Map<Product, Integer> sold = new LinkedHashMap<>();
        for (CoffeeOrder o : orders) {
            if (o.getStatus() != OrderStatus.COMPLETED) continue;
            for (OrderItem it : o.getItems()) {
                sold.merge(it.getProduct(), it.getQuantity(), Integer::sum);
            }
        }
        return sold.entrySet().stream()
                .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    /** Doanh thu = tổng tiền các đơn COMPLETED (đơn REFUNDED tự động bị loại). */
    public long totalRevenue() {
        long sum = 0;
        for (CoffeeOrder o : orders) {
            if (o.getStatus() == OrderStatus.COMPLETED) sum += o.total();
        }
        return sum;
    }

    public List<CoffeeOrder> getUnfinishedOrders() {
        return orders.stream()
                .filter(o -> o.getStatus() != OrderStatus.COMPLETED)
                .collect(Collectors.toList());
    }

    public void printReport(int lowStockThreshold) {
        System.out.println("1) Tổng doanh thu (đơn COMPLETED): " + Money.fmt(totalRevenue()));
        System.out.println("2) Top 3 món bán chạy nhất:");
        int rank = 1;
        for (Map.Entry<Product, Integer> e : getTopSelling(3)) {
            System.out.printf("   #%d %s – %d phần%n", rank++, e.getKey().getName(), e.getValue());
        }
        System.out.println("3) Đơn chưa hoàn tất / bị hủy / hoàn tiền:");
        for (CoffeeOrder o : getUnfinishedOrders()) System.out.println("   " + o);
        System.out.println("4) Cảnh báo sắp hết hàng (tồn <= " + lowStockThreshold + "):");
        for (Product p : getLowStockProducts(lowStockThreshold)) {
            System.out.printf("   %s – còn %d%n", p.getName(), p.getStockQuantity());
        }
    }
}
