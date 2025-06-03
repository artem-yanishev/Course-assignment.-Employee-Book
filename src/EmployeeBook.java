/**
 * Класс для управления списком сотрудников
 */
public class EmployeeBook {

    private final Employee[] employees = new Employee[10];
    private int employeeCount = 0;

    /**
     * Добавляет сотрудника в книгу
     * @param employee Сотрудник для добавления
     * @return true если добавлен успешно, false если нет места
     */
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

    /**
     * Удаляет сотрудника по ID
     * @param id ID сотрудника для удаления
     */
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

    /**
     * Выводит список всех сотрудников
     */
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

    /**
     * Выводит сумму зарплат всех сотрудников
     */
    public void printSumMonthSalaries() {
        System.out.println("Сумма затрат на ЗП в месяц: " + calcSumMonthSalaries());
    }

    /**
     * Находит и выводит сотрудника с минимальной зарплатой
     */
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

    /**
     * Находит и выводит сотрудника с максимальной зарплатой
     */
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

    /**
     * Выводит среднюю зарплату
     */
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

    /**
     * Выводит ФИО всех сотрудников
     */
    public void printFullNameAllEmployees() {
        System.out.println("ФИО Всех сотрудников:");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(employees[i].getLastName() + " " +
                    employees[i].getFirstName() + " " + employees[i].getPatronymic());
        }
    }

    /**
     * Индексирует зарплаты всех сотрудников
     * @param percent Процент индексации
     * @throws IllegalArgumentException если процент ≤ 0
     */
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

    /**
     * Находит сотрудника с минимальной зарплатой в отделе
     * @param department Номер отдела
     * @return Сотрудник или null если отдел пуст
     */
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

    /**
     * Выводит сотрудника с минимальной зарплатой в отделе
     * @param department Номер отдела
     */
    public void printMinSalaryEmpInDept(int department) {
        Employee emp = findMinSalaryInDepartment(department);
        if (emp != null) {
            System.out.println("Сотрудник с мин. ЗП в отделе " + department + ": " + emp);
        } else {
            System.out.println("В отделе " + department + " нет сотрудников");
        }
    }

    /**
     * Находит сотрудника с максимальной зарплатой в отделе
     * @param department Номер отдела
     * @return Сотрудник или null если отдел пуст
     */
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

    /**
     * Выводит сотрудника с максимальной зарплатой в отделе
     * @param department Номер отдела
     */
    public void printMaxSalaryEmpInDept(int department) {
        Employee emp = findMaxSalaryInDepartment(department);
        if (emp != null) {
            System.out.println("Сотрудник с макс. ЗП в отделе " + department + ": " + emp);
        } else {
            System.out.println("В отделе " + department + " нет сотрудников");
        }
    }

    /**
     * Выводит сумму зарплат по отделу
     * @param department Номер отдела
     */
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

    /**
     * Выводит среднюю зарплату по отделу
     * @param department Номер отдела
     */
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

    /**
     * Индексирует зарплаты в отделе
     * @param department Номер отдела
     * @param percent Процент индексации
     * @throws IllegalArgumentException если процент ≤ 0
     */
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


    /**
     * Выводит сотрудников отдела (без номера отдела)
     * @param department Номер отдела
     */
    public void printAllEmpByDepartment(int department) {
        System.out.println("Сотрудники отдела " + department + ":");
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getDepartment() == department) {
                System.out.println(employees[i].toStringWithoutDepartment());
            }
        }
    }

    /**
     * Выводит сотрудников с зарплатой меньше указанной
     * @param number Пороговое значение зарплаты
     */
    public void printAllEmpWithSalLessThanNumber(int number) {
        System.out.println("Сотрудники с Зарплатой меньше " + number + ":");
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getSalary() < number) {
                System.out.println(employees[i].toStringWithoutDepartment());
            }
        }
    }

    /**
     * Выводит сотрудников с зарплатой больше или равной указанной
     * @param number Пороговое значение зарплаты
     */
    public void printAllEmpWithSalGreaterThanNumber(int number) {
        System.out.println("Сотрудники с Зарплатой больше " + number + ":");
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getSalary() > number) {
                System.out.println(employees[i].toStringWithoutDepartment());
            }
        }
    }


    /**
     * Выводит сотрудника по ID
     * @param id ID сотрудника
     */
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
