package Exercise3;

import Exercise3.roles.Executive;
import Exercise3.roles.FullTimeEmployee;
import Exercise3.roles.PartTimeEmployee;
import Exercise3.roles.Volunteer;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[6];

        employees[0] = new PartTimeEmployee(000, Department.PRODUCTION, 83);
        employees[1] = new PartTimeEmployee(001, Department.SALES, 83);
        employees[2] = new FullTimeEmployee(002, Department.PRODUCTION);
        employees[3] = new FullTimeEmployee(003, Department.SALES);
        employees[4] = new Executive(004);
        employees[5] = new Volunteer(005,"Mario Rossi", 24, "MarioRossi.cv");

        for (int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];
            switch (employee.getClass().getSimpleName()) {
                case "PartTimeEmployee":
                    PartTimeEmployee partTimeEmployee = (PartTimeEmployee) employee;
                    System.out.println("Part-time " + partTimeEmployee.getDepartment() + " employee salary: " + partTimeEmployee.calculateSalary() + "$");
                    break;
                case "FullTimeEmployee":
                    FullTimeEmployee fullTimeEmployee = (FullTimeEmployee) employee;
                    System.out.println("Full-time " + fullTimeEmployee.getDepartment() + " employee salary: " + fullTimeEmployee.getSalary() + "$");
                    break;
                case "Executive":
                    System.out.println("Executive salary: " + employee.getSalary() + "$");
                    break;
                case "Volunteer":
                    Volunteer volunteer = (Volunteer) employee;
                    System.out.println("Volunteer: " + volunteer.getName());
                    volunteer.checkIn();
                    volunteer.checkOut();
                    break;
            }
        }
    }
}