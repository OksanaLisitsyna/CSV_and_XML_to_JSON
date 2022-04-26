public class Employee {
    public long id;
    public String firstName;
    public String lastName;
    public String country;
    public int age;

    public Employee() {
        // Пустой конструктор
    }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    public static Employee makeEmployeeFromString(String string) {
        String[] parts = string.trim().replaceAll(" ", "").split("\n");
        Employee employee = new Employee();
        employee.id = Long.parseLong(parts[0]);
        employee.firstName = parts[1];
        employee.lastName = parts[2];
        employee.country = parts[3];
        employee.age = Integer.parseInt(parts[4]);
        return employee;
    }
}