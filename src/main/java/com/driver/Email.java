package com.driver;
class Email {
    private String emailId;
    private String password;

    public Email(String emailId) {
        this.emailId = emailId;
        this.password = "Accio@123"; // Default password
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (!oldPassword.equals(this.password)) {
            return false;
        }

        // Check if newPassword meets all conditions
        if (newPassword.length() >= 8 &&
                newPassword.matches(".*[A-Z].*") &&
                newPassword.matches(".*[a-z].*") &&
                newPassword.matches(".*\\d.*") &&
                newPassword.matches(".*[^a-zA-Z\\d].*")) {
            this.password = newPassword;
            return true;
        }

        return false;
    }
}
