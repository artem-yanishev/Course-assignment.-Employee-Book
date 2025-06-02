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
        printMinSalaryEmpInDept(1);
        printMaxSalaryEmpInDept(2);
        calcSumMonthSalariesInDept(1);
        getAverageSalaryByDepartment(1);
        conductSalaryIndexationByDepartment(1, 7);
        printAllEmpByDepartment(1);
        printAllEmpWithSalLessThanNumber(15000);
        printAllEmpWithSalGreaterThanNumber(15000);
        getAverageSalaryByDepartment(3);
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
        System.out.println("Список всех сотрудников: ");
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

    public static Employee findMinSalaryInDepartment(int department) {
            Employee minEmp = null;
        for (int i = 0; i < employeeCount ; i++) {
            Employee emp = employees[i];
            if (emp.getDepartment() == department) {
                if (minEmp == null || emp.getSalary() < minEmp.getSalary()) {
                    minEmp = emp;
                }
            }
        }
        return minEmp;
    }

    public static void printMinSalaryEmpInDept(int department) {
        Employee emp = findMinSalaryInDepartment(department);
        if (emp != null) {
            System.out.println("Сотрудник с мин. ЗП в отделе " + department + ": " + emp);
        } else {
            System.out.println("В отделе " + department + " нет сотрудников");
        }
    }

    public static Employee findMaxSalaryInDepartment(int department) {
        Employee maxEmp = null;
        for (int i = 0; i < employeeCount ; i++) {
            Employee emp = employees[i];
            if (emp.getDepartment() == department) {
                if (maxEmp == null || emp.getSalary() > maxEmp.getSalary()) {
                    maxEmp = emp;
                }
            }
        }
        return maxEmp;
    }

    public static void printMaxSalaryEmpInDept(int department) {
        Employee emp = findMaxSalaryInDepartment(department);
        if (emp != null) {
            System.out.println("Сотрудник с макс. ЗП в отделе " + department + ": " + emp);
        } else {
            System.out.println("В отделе " + department + " нет сотрудников");
        }
    }

    public static void calcSumMonthSalariesInDept(int department) {
        double totalSum = 0;
        for (int i = 0; i < employeeCount; i++) {
            Employee emp = employees[i];
            if (emp.getDepartment() == department) {
                totalSum += employees[i].getSalary();
            }
        }
        totalSum = Math.round(totalSum * 100) / 100.0;
        System.out.println("Сумма затрат на ЗП в месяц по отделу " + department + ": " + totalSum);
    }

    public static void getAverageSalaryByDepartment(int department) {
        double sumSalary = 0;
        int countEmployees = 0;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getDepartment() == department) {
                sumSalary += employees[i].getSalary();
                countEmployees++;
            }
        }
        double average = 0;
        if (countEmployees > 0) {
            average = sumSalary / countEmployees;
            average = Math.round(average * 100) / 100.0;
        } else {
            System.out.println("В отделе нет сотрудников");
            return;
        }
        System.out.println("В отделе " + department + " средняя зарплата: " + average);
    }

    public static void conductSalaryIndexationByDepartment(int department, double percent) {
        if (percent <= 0) {
            throw new IllegalArgumentException("Процент индексации должен быть положительным");
        }
        int countEmployees = 0;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getDepartment() == department) {
                countEmployees++;
            }
        }
        double indexationFactor = 1 + percent/100.0;
        for (int i = 0; i < countEmployees; i++) {
            double currentSalary = employees[i].getSalary();
            double newSalary = currentSalary * indexationFactor;
            newSalary = Math.round(newSalary * 100) / 100.0;
            employees[i].setSalary(newSalary);
        }
        System.out.printf("Зарплаты в отделе " + department + " проиндексированы на %.2f%%\n", percent);
    }

    public static void printAllEmpByDepartment(int department) {
        System.out.println("Сотрудники отдела " + department + ":");
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getDepartment() == department) {
                System.out.println(employees[i].toStringWithoutDepartment());
            }
        }
    }

    public static void printAllEmpWithSalLessThanNumber(int number) {
        System.out.println("Сотрудники с Зарплатой меньше " + number + ":");
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getSalary() < number) {
                System.out.println(employees[i].toStringWithoutDepartment());
            }
        }
    }

    public static void printAllEmpWithSalGreaterThanNumber(int number) {
        System.out.println("Сотрудники с Зарплатой больше " + number + ":");
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getSalary() > number) {
                System.out.println(employees[i].toStringWithoutDepartment());
            }
        }
    }


}