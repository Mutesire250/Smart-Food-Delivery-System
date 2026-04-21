public abstract class User {
    private String name;
    private String email;

    public User(String name, String email) {
        validateName(name);
        validateEmail(email);
        this.name = name;
        this.email = email;
    }

    public abstract void displayInfo();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidUserException("Name cannot be empty.");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new InvalidUserException("Invalid email address: " + email);
        }
    }
}
