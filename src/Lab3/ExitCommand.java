package Lab3;

import java.util.Scanner;

public class ExitCommand implements Command {
    private CustomerManager customerManager;
    private Scanner scanner;

    public ExitCommand(CustomerManager customerManager, Scanner scanner) {
        this.customerManager = customerManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (customerManager.isDataModified()) {
            System.out.println("Warning: Unsaved changes. Do you really want to exit? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                System.out.println("Exit aborted. Continuing with the application.");
                return;
            }
        }

        System.out.println("Exiting application. Thank you!");
        // Save data to file before exiting
        customerManager.saveToFile();

        scanner.close();
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Exit: Exit the application";
    }
}
