package com.dev.synapse.validation;

import javax.swing.JOptionPane;

public class Validator {

    public static boolean isEmailValid(String value) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null,
                    "Email input can not be Empty!",
                    "Email Validation",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        } else if (!value.matches(Validation.EMAIL.validate())) {
            JOptionPane.showMessageDialog(null,
                    "Enter a valid Email Address",
                    "Email Validation",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    public static boolean isMobileValid(String value) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null,
                    "Mobile input can not be Empty!",
                    "Mobile Validation",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        } else if (!value.matches(Validation.MOBILE.validate())) {
            JOptionPane.showMessageDialog(null,
                    "Enter a valid Mobile Number",
                    "Mobile Validation",
                    JOptionPane.WARNING_MESSAGE
            );
        }
        return true;
    }

    public static boolean isPasswordValid(String value) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null,
                    "Password field can not be Empty!",
                    "Password Validation",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        } else if (!value.matches(Validation.PASSWORD.validate())) {
            JOptionPane.showMessageDialog(null,
                    "Password must include following characters. \n"
                    + "At least 1 Lowercase \n"
                    + "at least 1 Uppercase \n"
                    + "a special character \n"
                    + "at least 1 digit \n"
                    + "must be greater than 4 and less than 8 characters",
                    "Password Validation",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    public static boolean isInputFieldValid(String value) {
        if (value.isBlank()) {
            JOptionPane.showMessageDialog(null,
                    "Input field can not be Empty!",
                    "Validation Message",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    public static boolean isSelectedItemValid(int value) {
        if (value == 0) {
            JOptionPane.showMessageDialog(null,
                    "Please select a valid option",
                    "Validation Message",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }
}
