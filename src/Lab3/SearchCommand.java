
package Lab3;

import java.util.List;
import java.util.Scanner;

public class SearchCommand implements Command {
    private CustomerManager customerManager;
    private Scanner scanner;

    public SearchCommand(CustomerManager customerManager, Scanner scanner) {
        this.customerManager = customerManager;
        this.scanner = scanner;
    }

    public void execute() {
        System.out.print("Enter search criteria (name, email, or phone): ");
        String searchCriteria = scanner.nextLine().trim().toLowerCase();

        System.out.print("Enter search value: ");
        String searchValue = scanner.nextLine().trim();

        List<Customer> searchResults = customerManager.searchCustomers(searchCriteria, searchValue);

        if (searchResults.isEmpty()) {
            System.err.println("No customers found matching the search criteria.\n");
        } else {
            System.out.println("Search Results:");
            System.out.printf("%-5s %-20s %-30s %-15s\n", "ID", "Name", "Email", "Phone");
            System.out.println("-------------------------------------------------------------");
            for (Customer customer : searchResults) {
                System.out.printf("%-5d %-20s %-30s %-15s\n", customer.getId(), customer.getName(), customer.getEmail(), customer.getPhoneNumber());
            }
            System.out.println("-------------------------------------------------------------\n");
        }
    }

    @Override
    public String getDescription() {
        return "Search: Search for customers based on criteria (name, email, or phone)";
    }
}

