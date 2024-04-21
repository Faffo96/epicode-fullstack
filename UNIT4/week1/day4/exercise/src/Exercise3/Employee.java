package Exercise3;

public abstract class Employee implements EmployeesJoinLog {
    private int id;
    private double salary;
    private Department department;

    public Employee(int id, Department department) {
        this.id = id;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void checkIn() {
        System.out.println(this.getDepartment() + "#" + this.getId() + " checked in.");
    }

    public void checkOut() {
        System.out.println(this.getDepartment() + "#" + this.getId() + " checked out.");
    }
}
