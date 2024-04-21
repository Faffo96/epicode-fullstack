package Exercise1;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];

        employees[0] = new Employee(000, Department.PRODUCTION);
        employees[1] = new Employee(001, Department.ADMINISTRATION);
        employees[2] = new Employee(002, Department.SALES);

        for (int i = 0; i < employees.length ; i++) {
            System.out.println(employees[i].getId());
        }
    }
}