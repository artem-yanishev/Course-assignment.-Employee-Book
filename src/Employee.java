import java.util.Objects;

/**
 * Класс для хранения информации о сотруднике
 */
public class Employee {
    private final String lastName;
    private final String firstName;
    private final String patronymic;
    private int department;

    /**
     * Минимально допустимый номер отдела
     */
    public static final int MIN_DEPARTMENT = 1;

    /**
     * Максимально допустимый номер отдела
     */
    public static final int MAX_DEPARTMENT = 5;
    private double salary;
    private static int nextId = 1;
    private final int id;

    /**
     * Конструктор сотрудника
     *
     * @param lastName   Фамилия
     * @param firstName  Имя
     * @param patronymic Отчество
     * @param department Номер отдела (1-5)
     * @param salary     Зарплата
     * @throws IllegalArgumentException если отдел или зарплата некорректны
     */
    public Employee(String lastName, String firstName, String patronymic, int department, double salary) {
        this.id = nextId++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        validateDepartment(department);
        validateSalary(salary);
        this.department = department;
        this.salary = salary;
    }

    /**
     * Проверяет отдел
     * @param department Переменная с отделом
     */
    private void validateDepartment(int department) {
        if (department < MIN_DEPARTMENT || department > MAX_DEPARTMENT) {
            throw new IllegalArgumentException("Отдел должен быть от 1 до 5");
        }
    }

    /**
     * Проверяет зарплату
     * @param salary Переменная с зарплатой
     */
    private void validateSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Зарплата не может быть отрицательной");
        }
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

    /**
     * Устанавливает зарплату
     * @param salary Новая зарплата
     */
    public void setSalary(double salary) {
        validateSalary(salary);
        this.salary = salary;
    }

    /**
     * Устанавливает отдел
     * @param department Номер отдела
     */
    public void setDepartment(int department) {
        validateDepartment(department);
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

    public String toStringForRemove() {
        return "Сотрудник " + "ID: " + id +
                ", ФИО: " + lastName + " " + firstName + " " + patronymic;
    }
}
