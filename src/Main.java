public class Main {

    public static void main(String[] args) {
        EmployeeBook employeeBook = new EmployeeBook();
        employeeBook.addEmployee(new Employee("Иванов", "Иван", "Иванович",
                1, 12345.67));
        employeeBook.addEmployee(new Employee("Сергеев", "Сергей", "Сергеевич",
                2, 23456.78));
        employeeBook.printAllEmployees();
        employeeBook.printSumMonthSalaries();
        employeeBook.searchEmployeeMinSalary();
        employeeBook.searchEmployeeMaxSalary();
        employeeBook.calcAverageValueSalary();
        employeeBook.printFullNameAllEmployees();
        employeeBook.conductSalaryIndexation(7);
        employeeBook.printMinSalaryEmpInDept(1);
        employeeBook.printMaxSalaryEmpInDept(2);
        employeeBook.calcSumMonthSalariesInDept(1);
        employeeBook.getAverageSalaryByDepartment(1);
        employeeBook.conductSalaryIndexationByDepartment(1, 7);
        employeeBook.printAllEmpByDepartment(1);
        employeeBook.printAllEmpWithSalLessThanNumber(15000);
        employeeBook.printAllEmpWithSalGreaterThanNumber(15000);
        employeeBook.getAverageSalaryByDepartment(3);
        employeeBook.removeEmployee(1);
        employeeBook.printEmpById(2);
    }
}