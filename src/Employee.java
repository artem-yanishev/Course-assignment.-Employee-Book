import java.util.Objects;

public class Employee {
    private final String lastName;
    private final String firstName;
    private final String patronymic;
    private int department;
    private static final int MIN_DEPARTMENT = 1;
    private static final int MAX_DEPARTMENT = 5;
    private double salary;
    private static int nextId = 1;
    private final int id;

    public Employee(String lastName, String firstName, String patronymic, int department, double salary) {
        this.id = nextId++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.department = department;
        this.salary = salary;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Зарплата не может быть отрицательной");
        }
        this.salary = salary;
    }

    public void setDepartment(int department) {
        if (department < MIN_DEPARTMENT || department > MAX_DEPARTMENT) {
            throw new IllegalArgumentException("Отдел должен быть от 1 до 5");
        }
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && salary == employee.salary && id == employee.id && Objects.equals(lastName, employee.lastName) && Objects.equals(firstName, employee.firstName) && Objects.equals(patronymic, employee.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, patronymic, department, salary, id);
    }

    @Override
    public String toString() {
        return "Сотрудник " + "ID: " + id +
                ", ФИО: " + lastName + " " + firstName + " " + patronymic +
                ", Отдел: " + department +
                ", Зарплата: " + salary;
    }

    public String toStringWithoutDepartment() {
        return "Сотрудник " + "ID: " + id +
                ", ФИО: " + lastName + " " + firstName + " " + patronymic +
                ", Зарплата: " + salary;
    }
}
