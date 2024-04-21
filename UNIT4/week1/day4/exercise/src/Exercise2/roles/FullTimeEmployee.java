package Exercise2.roles;

import Exercise2.Department;
import Exercise2.Employee;

public class FullTimeEmployee extends Employee {
    private double salary;

    @Override
    public double getSalary() {
        return salary;
    }

    public FullTimeEmployee(int id, Department department) {
        super(id, department);
        if (department == Department.ADMINISTRATION) {
            throw new IllegalArgumentException("ADMINISTRATION department is not allowed for full-time employees.");
        }
        switch (department) {
            case PRODUCTION:
                this.salary = 1960;
                break;
            case SALES:
                this.salary = 2280;
                break;
            default:
                throw new IllegalArgumentException("The department must be PRODUCTION or SALES.");
        }
    }
}
