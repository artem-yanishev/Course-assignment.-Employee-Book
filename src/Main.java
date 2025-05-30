public class Main {

    private static final Employee[] employees = new Employee[10];
    private static int employeeCount = 0;

    public static void main(String[] args) {
        addEmployee(new Employee("Иванов", "Иван", "Иванович",
                1, 12345.67));
        addEmployee(new Employee("Сергеев", "Сергей", "Сергеевич",
                2, 23456.78));
        printAllEmployees();
        printSumMonthSalaries();
        searchEmployeeMinSalary();
        searchEmployeeMaxSalary();
        calcAverageValueSalary();
        printFullNameAllEmployees();
        conductSalaryIndexation(7);
    }

    public static void addEmployee(Employee employee) {
        if (employeeCount < employees.length) {
            employees[employeeCount] = employee;
            employeeCount++;
        } else {
            System.out.println("Места для нового сотрудника нет!");
        }
    }

    public static void printAllEmployees() {
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(employees[i].toString());
        }
    }

    public static double calcSumMonthSalaries() {
        double totalSum = 0;
        for (int i = 0; i < employeeCount; i++) {
            totalSum += employees[i].getSalary();
        }
        return Math.round(totalSum * 100) / 100.0;
    }

    public static void printSumMonthSalaries() {
        System.out.println("Сумма затрат на ЗП в месяц: " + calcSumMonthSalaries());
    }

    public static void searchEmployeeMinSalary() {
        if (employeeCount == 0) {
            System.out.println("Нет сотрудников!");
            return;
        }
        double minSalary = employees[0].getSalary();
        int employeeIndex = 0;
        for (int i = 1; i < employeeCount; i++) {
            if (employees[i].getSalary() < minSalary) {
                minSalary = employees[i].getSalary();
                employeeIndex = i;
            }
        }
        System.out.println("Сотрудник с минимальной ЗП: " + employees[employeeIndex].toString());
    }

    public static void searchEmployeeMaxSalary() {
        if (employeeCount == 0) {
            System.out.println("Нет сотрудников!");
            return;
        }
        double maxSalary = employees[0].getSalary();
        int employeeIndex = 0;
        for (int i = 1; i < employeeCount; i++) {
            if (employees[i].getSalary() > maxSalary) {
                maxSalary = employees[i].getSalary();
                employeeIndex = i;
            }
        }
        System.out.println("Сотрудник с максимальной ЗП: " + employees[employeeIndex].toString());
    }

    public static void calcAverageValueSalary() {
        double totalSum = calcSumMonthSalaries();
        double result = totalSum / employeeCount;
        result = Math.round(result * 100) / 100.0;
        System.out.println("Среднее значение ЗП: " + result);
    }

    public static void printFullNameAllEmployees() {
        System.out.println("ФИО Всех сотрудников:");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(employees[i].getLastName() + " " +
                    employees[i].getFirstName() + " " + employees[i].getPatronymic());
        }
    }

    public static void conductSalaryIndexation(double percent) {
        if (percent <= 0) {
            throw new IllegalArgumentException("Процент индексации должен быть положительным");
        }
        double indexationFactor = 1 + percent/100.0;
        for (int i = 0; i < employeeCount; i++) {
            double currentSalary = employees[i].getSalary();
            double newSalary = currentSalary * indexationFactor;
            newSalary = Math.round(newSalary * 100) / 100.0;
            employees[i].setSalary(newSalary);
        }
        System.out.printf("Зарплаты проиндексированы на %.2f%%\n", percent);
    }
}