package Lab3;

import java.util.Scanner;

public class AddCommand implements Command {
    private CustomerManager customerManager;
    private Scanner scanner;

    public AddCommand(CustomerManager customerManager, Scanner scanner) {
        this.customerManager = customerManager;
        this.scanner = scanner;
    }

    public void execute() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine().trim();

        // Validate email format
        if (!isValidEmail(email)) {
            System.err.println("Invalid email format. Please enter a valid email address.");
            return;
        }

        System.out.print("Enter customer phone number: ");
        String phoneNumber = scanner.nextLine().trim();

        // Validate phone number format
        if (!isValidPhoneNumber(phoneNumber)) {
            System.err.println("Invalid phone number format. Please enter a valid phone number.");
            return;
        }

        customerManager.addCustomer(name, email, phoneNumber);
    }
    

    @Override
    public String getDescription() {
        return "Add: Add a new customer";
    }

    private boolean isValidEmail(String email) {
        // Regular expression for validating an Email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Validate the email using the regex pattern
        return email.matches(emailRegex);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression for validating a phone number (example: US phone number format)
        String phoneRegex = "^[2-9]\\d{2}-\\d{3}-\\d{4}$";

        // Validate the phone number using the regex pattern
        return phoneNumber.matches(phoneRegex);
    }
}
