package Exercise3.roles;

import Exercise3.Department;
import Exercise3.Employee;

public class FullTimeEmployee extends Employee {
    private double salary;

    @Override
    public double getSalary() {
        return salary;
    }

    public FullTimeEmployee(int id, Department department) {
        super(id, department);
        if (department == Department.ADMINISTRATION) {
            throw new IllegalArgumentException("ADMINISTRATION department is not allowed for part-time employees.");
        }
        switch (department) {
            case PRODUCTION:
                this.salary = 1960;
                break;
            case SALES:
                this.salary = 2280;
                break;
        }
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
