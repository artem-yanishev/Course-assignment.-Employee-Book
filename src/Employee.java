public class Employee {

    private String firstName;
    private String lastName;
    private String patronymic;
    private int department;
    private int salary;
    private static int nextId = 1;
    private int id;

    public Employee(String firstName, String lastName, String patronymic, int department, int salary, int id) {
        this.id = nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }
}
