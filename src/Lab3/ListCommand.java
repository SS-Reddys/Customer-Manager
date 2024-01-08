
package Lab3;

import java.util.Scanner;
import java.util.List;

public class ListCommand implements Command {
    private CustomerManager customerManager;
    Scanner scanner = new Scanner(System.in);
    public ListCommand(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public void execute() {
        System.out.print("Enter sort criteria (name, email, or id): ");
        String sortBy = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter sort order (ascending or descending): ");
        String sortOrderInput = scanner.nextLine().trim().toLowerCase();

        boolean descending = sortOrderInput.equals("descending");

        List<Customer> sortedCustomers = customerManager.sortCustomers(sortBy, descending);

        if (sortedCustomers.isEmpty()) {
            System.out.println("No customers available.\n");
        } else {
            System.out.println("List of Customers:");
            System.out.printf("%-5s %-20s %-30s %-15s\n", "ID", "Name", "Email", "Phone");
            System.out.println("-------------------------------------------------------------");
            for (Customer customer : sortedCustomers) {
                System.out.printf("%-5d %-20s %-30s %-15s\n", customer.getId(), customer.getName(), customer.getEmail(), customer.getPhoneNumber());
            }
            System.out.println("-------------------------------------------------------------\n");
        }
    }

    @Override
    public String getDescription() {
        return "List: List all customers (sorted)";
    }
}

