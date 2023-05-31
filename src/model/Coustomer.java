package model;

import java.util.regex.Pattern;

public class Coustomer {
    private String firstName;
    private String lastName;
    private String email;

        public Coustomer(String firstName, String lastName, String email) {

            this.firstName = firstName;
            this.lastName = lastName;
            String emailPater = "^([a-z]|[A-Z])(.+)@(.+).(.+)$";
            Pattern pater = Pattern.compile(emailPater);
            if(pater.matcher(email).matches())
                this.email = email;
            else
                throw new IllegalArgumentException("Invalid email address");
            }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {

        return email;
    }

    @Override
    public String toString() {
        return
                "First Name:'" + firstName + '\'' +
                ", Last Name:'" + lastName + '\'' +
                ", Email: '" + email + '\'';

    }

    public boolean setEmail(String email) {
        String emailPater = "^(.+)@(.+).(.+)$";
        Pattern pater = Pattern.compile(emailPater);
        if(pater.matcher(email).matches()) {

            this.email = email;
            return true;
        }
        return false;
    }
}
