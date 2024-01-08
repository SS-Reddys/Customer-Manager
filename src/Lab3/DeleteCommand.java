package Lab3;

import java.util.Scanner;

public class DeleteCommand implements Command {
    private CustomerManager customerManager;
    private Scanner scanner;

    public DeleteCommand(CustomerManager customerManager, Scanner scanner) {
        this.customerManager = customerManager;
        this.scanner = scanner;
    }

    public void execute() {
        System.out.print("Enter customer ID to delete: ");
        int customerId;
        try {
            customerId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid customer ID format. Please enter a valid number.");
            return;
        }

        customerManager.deleteCustomer(customerId);
    }

    @Override
    public String getDescription() {
        return "Delete: Delete a customer by ID";
    }
}
