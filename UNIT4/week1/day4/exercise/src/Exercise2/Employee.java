package Exercise2;

public abstract class Employee {
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
}
