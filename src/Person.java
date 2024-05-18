public class Person {
    // Initializing 3 instant variables
    private String name;
    private String surname;
    private String email;

    // Creating constructor with 3 parameters
    public Person(String nameInput, String surnameInput, String emailInput) {
        name = nameInput;
        surname = surnameInput;
        email = emailInput;
    }

    // Creating 3 getters
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
}
