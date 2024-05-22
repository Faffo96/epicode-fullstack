package Exercise3.roles;

import Exercise3.Department;
import Exercise3.Employee;
import Exercise3.EmployeesJoinLog;

public class Volunteer extends Employee implements EmployeesJoinLog {
    private String name;
    private int age;
    private String cv;

    public Volunteer(int id, String name, int age, String cv) {
        super(id, Department.VOLUNTEER);
        this.name = name;
        this.age = age;
        this.cv = cv;
    }

    public String getName() {
        return name;
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
