package Exercise3.roles;

import Exercise3.Department;
import Exercise3.Employee;

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

    @Override
    public void checkIn() {
        super.checkIn();
    }

    @Override
    public void checkOut() {
        super.checkOut();
    }
}

