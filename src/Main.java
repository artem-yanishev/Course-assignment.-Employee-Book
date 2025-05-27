public class Main {

    private static Employee[] employees = new Employee[10];
    private static int employeeCount = 0;

    public static void main(String[] args) {
        addEmployee (new Employee("Иванов", "Иван", "Иванович",
                1, 60000));
        addEmployee (new Employee("Сергеев", "Сергей", "Сергеевич",
                2, 70000));
        printAllEmployees();
    }

    public static void addEmployee(Employee employee) {
        if (employeeCount < employees.length) {
            employees[employeeCount] = employee;
            employeeCount++;
        } else {
            System.out.println("Места нет!");
        }
    }

    public static void printAllEmployees() {
        System.out.println("=== Список сотрудников ===");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(employees[i].toString());
        }
        System.out.println("==========================");
    }
}