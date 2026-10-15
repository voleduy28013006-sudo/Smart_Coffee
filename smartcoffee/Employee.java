package smartcoffee.smartcoffee;

/** Nhân viên thuộc một chi nhánh. */
public class Employee {
    private final String id;
    private final String name;
    private final Role role;
    private final Branch branch;

    public Employee(String id, String name, Role role, Branch branch) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.branch = branch;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public Branch getBranch() {
        return branch;
    }

    @Override
    public String toString() { return "[" + id + "] " + name + " (" + role + ", " + branch.getName() + ")"; }
}
