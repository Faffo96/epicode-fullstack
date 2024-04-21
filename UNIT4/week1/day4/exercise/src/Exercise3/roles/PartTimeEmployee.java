package Exercise3.roles;

import Exercise3.Department;
import Exercise3.Employee;

public class PartTimeEmployee extends Employee {
    private double salary;
    private int workedHours;

    public PartTimeEmployee(int id, Department department, int workedHours) {
        super(id, department);
        if (department == Department.ADMINISTRATION) {
            throw new IllegalArgumentException("ADMINISTRATION department is not allowed for part-time employees.");
        }
        this.workedHours = workedHours;
        this.salary = calculateSalary();

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

    public double calculateSalary() {
        double hourPay = 0;

        switch (this.getDepartment()) {
            case PRODUCTION:
                hourPay = 9.35;
                break;
            case SALES:
                hourPay = 10.35;
                break;
        }
        return hourPay * this.workedHours;
    }
}
