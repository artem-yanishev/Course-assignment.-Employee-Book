public class EmployeeBook {

    private final Employee[] employees = new Employee[10];
    private int employeeCount = 0;

    public boolean addEmployee(Employee employee) {
        if (employee == null) {
            return false;
        }
        if (employeeCount < employees.length) {
            employees[employeeCount++] = employee;
            return true;
        }
        return false;
    }

    public void removeEmployee(int id) {
        if (employeeCount == 0) {
            System.out.println("Нет сотрудников!");
            return;
        }
        boolean found = false;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getId() == id) {
                String employeeInfo = employees[i].toStringForRemove();
                System.arraycopy(employees, i + 1, employees, i, employeeCount - i - 1);
                employees[employeeCount - 1] = null;
                employeeCount--;
                System.out.println(employeeInfo + " - удалён!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Сотрудник c ID: " + id + " - не найден.");
        }
    }

    public void printAllEmployees() {
        System.out.println("Список всех сотрудников: ");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(employees[i].toString());
        }
    }

    public double calcSumMonthSalaries() {
        double totalSum = 0;
        for (int i = 0; i < employeeCount; i++) {
            totalSum += employees[i].getSalary();
        }
        return Math.round(totalSum * 100) / 100.0;
    }

    public void printSumMonthSalaries() {
        System.out.println("Сумма затрат на ЗП в месяц: " + calcSumMonthSalaries());
    }

    public void searchEmployeeMinSalary() {
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

    public void searchEmployeeMaxSalary() {
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

    public void calcAverageValueSalary() {
        if (employeeCount == 0) {
            System.out.println("Нет сотрудников для расчета средней ЗП.");
            return;
        }
        double totalSum = calcSumMonthSalaries();
        double result = totalSum / employeeCount;
        result = Math.round(result * 100) / 100.0;
        System.out.println("Среднее значение ЗП: " + result);
    }

    public void printFullNameAllEmployees() {
        System.out.println("ФИО Всех сотрудников:");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(employees[i].getLastName() + " " +
                    employees[i].getFirstName() + " " + employees[i].getPatronymic());
        }
    }

    public void conductSalaryIndexation(double percent) {
        if (percent <= 0) {
            throw new IllegalArgumentException("Процент индексации должен быть положительным");
        }
        double indexationFactor = 1 + percent / 100.0;
        for (int i = 0; i < employeeCount; i++) {
            double currentSalary = employees[i].getSalary();
            double newSalary = currentSalary * indexationFactor;
            newSalary = Math.round(newSalary * 100) / 100.0;
            employees[i].setSalary(newSalary);
        }
        System.out.printf("Зарплаты проиндексированы на %.2f%%\n", percent);
    }

    public Employee findMinSalaryInDepartment(int department) {
        Employee minEmp = null;
        for (int i = 0; i < employeeCount; i++) {
            Employee emp = employees[i];
            if (emp.getDepartment() == department) {
                if (minEmp == null || emp.getSalary() < minEmp.getSalary()) {
                    minEmp = emp;
                }
            }
        }
        return minEmp;
    }

    public void printMinSalaryEmpInDept(int department) {
        Employee emp = findMinSalaryInDepartment(department);
        if (emp != null) {
            System.out.println("Сотрудник с мин. ЗП в отделе " + department + ": " + emp);
        } else {
            System.out.println("В отделе " + department + " нет сотрудников");
        }
    }

    public Employee findMaxSalaryInDepartment(int department) {
        Employee maxEmp = null;
        for (int i = 0; i < employeeCount; i++) {
            Employee emp = employees[i];
            if (emp.getDepartment() == department) {
                if (maxEmp == null || emp.getSalary() > maxEmp.getSalary()) {
                    maxEmp = emp;
                }
            }
        }
        return maxEmp;
    }

    public void printMaxSalaryEmpInDept(int department) {
        Employee emp = findMaxSalaryInDepartment(department);
        if (emp != null) {
            System.out.println("Сотрудник с макс. ЗП в отделе " + department + ": " + emp);
        } else {
            System.out.println("В отделе " + department + " нет сотрудников");
        }
    }

    public void calcSumMonthSalariesInDept(int department) {
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

    public void getAverageSalaryByDepartment(int department) {
        double sumSalary = 0;
        int countEmployees = 0;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getDepartment() == department) {
                sumSalary += employees[i].getSalary();
                countEmployees++;
            }
        }
        double average;
        if (countEmployees > 0) {
            average = sumSalary / countEmployees;
            average = Math.round(average * 100) / 100.0;
        } else {
            System.out.println("В отделе нет сотрудников");
            return;
        }
        System.out.println("В отделе " + department + " средняя зарплата: " + average);
    }

    public void conductSalaryIndexationByDepartment(int department, double percent) {
        if (percent <= 0) {
            throw new IllegalArgumentException("Процент индексации должен быть положительным");
        }
        boolean departmentExists = false;
        double indexationFactor = 1 + percent / 100.0;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getDepartment() == department) {
                departmentExists = true;
                double newSalary = employees[i].getSalary() * indexationFactor;
                employees[i].setSalary(Math.round(newSalary * 100) / 100.0);
            }
        }
        if (departmentExists) {
            System.out.printf("Зарплаты в отделе %d проиндексированы на %.2f%%\n", department, percent);
        } else {
            System.out.println("В отделе " + department + " нет сотрудников.");
        }
    }

    public void printAllEmpByDepartment(int department) {
        System.out.println("Сотрудники отдела " + department + ":");
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getDepartment() == department) {
                System.out.println(employees[i].toStringWithoutDepartment());
            }
        }
    }

    public void printAllEmpWithSalLessThanNumber(int number) {
        System.out.println("Сотрудники с Зарплатой меньше " + number + ":");
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getSalary() < number) {
                System.out.println(employees[i].toStringWithoutDepartment());
            }
        }
    }

    public void printAllEmpWithSalGreaterThanNumber(int number) {
        System.out.println("Сотрудники с Зарплатой больше " + number + ":");
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getSalary() > number) {
                System.out.println(employees[i].toStringWithoutDepartment());
            }
        }
    }

    public void printEmpById(int id) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getId() == id) {
                System.out.println(employees[i].toString());
                return;
            }
        }
        System.out.println("Сотрудник c ID: " + id + " - не найден.");
    }
}
