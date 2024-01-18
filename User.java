package testcode;

class User {
    String email;
    String password;

    User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    boolean authenticate(String enteredEmail, String enteredPassword) {
        return email.equals(enteredEmail) && password.equals(enteredPassword);
    }
}