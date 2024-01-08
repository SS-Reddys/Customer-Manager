package Lab3;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandInvoker implements CustomerObserver {
    @SuppressWarnings("unused")
    private List<Customer> customers; // Assuming you need this for some reason
    private Command listCommand;
    private Command addCommand;
    private Command deleteCommand;
    private Command helpCommand;
    private Command exitCommand;
    private Command searchCommand;

    public CommandInvoker(CustomerManager customerManager, Scanner scanner) {
        this.customers = customerManager.getCustomers();
        this.listCommand = new ListCommand(customerManager);
        this.addCommand = new AddCommand(customerManager, scanner);
        this.deleteCommand = new DeleteCommand(customerManager, scanner);
        this.helpCommand = new HelpCommand(this);
        this.exitCommand = new ExitCommand(customerManager, scanner);
        this.searchCommand = new SearchCommand(customerManager, scanner);
        customerManager.addObserver(this);
    }
    
    public void update(Customer newCustomer) {
        System.out.println("CommandInvoker notified: New customer added - " + newCustomer+"\n");
    }
    
    @Override
	public void updateDeletion(Customer deletedCustomer) {
		 System.out.println("CommandInvoker notified: Customer removed - "+ deletedCustomer+"\n");
		
	}
    public void displayHelpMenu() {
        System.out.println("Available Commands:");
        for (Command command : Arrays.asList(listCommand, addCommand, deleteCommand,searchCommand, helpCommand, exitCommand)) {
            System.out.println("- " + command.getDescription());
            
        }
        System.out.println(" ");
    }

    public void executeCommand(String command) {
        switch (command) {
            case "list":
                listCommand.execute();
                break;
            case "add":
                addCommand.execute();
                break;
            case "delete":
                deleteCommand.execute();
                break;
            case "help":
                helpCommand.execute();
                break;
            case "exit":
                exitCommand.execute();
                break;
            case "search":
                searchCommand.execute();
                break;
            default:
                System.out.println("Invalid command. Type 'help' for available commands.");
        }
    }

    public static void main(String[] args) {
        CustomerManager customerManager = CustomerManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        CommandInvoker commandInvoker = new CommandInvoker(customerManager, scanner);

        while (true) {
            System.out.println("Available Commands: list, add, delete, search, help, exit");
            System.out.print("Enter command: ");
            String userInput = scanner.nextLine().trim().toLowerCase();

            switch (userInput) {
                case "list":
                case "add":
                case "delete":
                    commandInvoker.executeCommand(userInput);
                    break;
                case "search":
                    commandInvoker.executeCommand(userInput);
                    break;              
                case "help":
                    commandInvoker.displayHelpMenu();
                    break;
                case "exit":
                    commandInvoker.executeCommand(userInput);
                default:
                    System.err.println("Invalid command. Type 'help' for available commands.");
            }
        }
    }

	
}