package Exercise2.roles;

import Exercise2.Department;
import Exercise2.Employee;

public class Executive extends Employee {
    private double salary;

    public Executive(int id) {
        super(id, Department.ADMINISTRATION);
        this.salary = 3250;
    }

    @Override
    public double getSalary() {
        return salary;
    }
}
