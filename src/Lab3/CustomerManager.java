package Lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomerManager implements CustomerObserver {

    private static CustomerManager instance;
    private List<Customer> customers;
    private String dataFileName = "src/customer_data.txt";
    private boolean dataModified = false;
    private List<CustomerObserver> observers = new ArrayList<>();

    private CustomerManager() {
        customers = loadFromFile();
    }

    public static CustomerManager getInstance() {
        if (instance == null) {
            instance = new CustomerManager();
        }
        return instance;
    }
    public void setModified(boolean modified) {
        this.dataModified = modified;
    }

    public boolean isDataModified() {
        return dataModified;
    }
    
    private void notifyObserversAddition(Customer newCustomer) {
        for (CustomerObserver observer : observers) {
            observer.update(newCustomer);
        }
    }

    // Notify all observers when a customer is deleted
    private void notifyObserversDeletion(Customer deletedCustomer) {
        for (CustomerObserver observer : observers) {
            observer.updateDeletion(deletedCustomer);
        }
    }
    
    private List<Customer> loadFromFile() {
        List<Customer> loadedCustomers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String email = parts[2].trim();
                String phoneNumber = parts[3].trim();
                loadedCustomers.add(new Customer(id, name, email, phoneNumber));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Data file not found. Creating a new one.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
        return loadedCustomers;
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFileName))) {
            for (Customer customer : customers) {
                writer.write(customer.getId() + ", " + customer.getName() + ", " + customer.getEmail() + ", " + customer.getPhoneNumber());
                writer.newLine();
            }
            System.out.println("Data saved to file successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    public void addCustomer(String name, String email, String phoneNumber) {
        Customer newCustomer = new Customer(customers.size() + 1, name, email, phoneNumber);
        customers.add(newCustomer);
        System.out.println("Customer added Successfully");
        saveToFile();
        setModified(true);
        notifyObserversAddition(newCustomer); // Notify observers of the new customer
    }
    public void deleteCustomer(int customerId) {
        Customer customerToRemove = null;
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                customerToRemove = customer;
                break;
            }
        }

        if (customerToRemove != null) {
            customers.remove(customerToRemove);        
            System.out.println("Customer removed Succesfully");
            saveToFile();
            setModified(true);// Save the updated data to file
            notifyObserversDeletion(customerToRemove);
            
        } else {
            System.out.println("Customer not found with ID: " + customerId);
        }
    }

    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.\n");
        } else {
            System.out.println("List of Customers:");
            System.out.printf("%-5s %-20s %-30s %-15s\n", "ID", "Name", "Email", "Phone");
            System.out.println("-------------------------------------------------------------");
            for (Customer customer : customers) {
                System.out.printf("%-5d %-20s %-30s %-15s\n", customer.getId(), customer.getName(), customer.getEmail(), customer.getPhoneNumber());
            }
            System.out.println("-------------------------------------------------------------\n");
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }
    
    public List<Customer> searchCustomers(String searchCriteria, String searchValue) {
        List<Customer> searchResults = new ArrayList<>();

        for (Customer customer : customers) {
            switch (searchCriteria) {
                case "name":
                    if (customer.getName().toLowerCase().contains(searchValue.toLowerCase())) {
                        searchResults.add(customer);
                    }
                    break;
                case "email":
                    if (customer.getEmail().toLowerCase().contains(searchValue.toLowerCase())) {
                        searchResults.add(customer);
                    }
                    break;
                case "phone":
                    if (customer.getPhoneNumber().contains(searchValue)) {
                        searchResults.add(customer);
                    }
                    break;
                default:
                    System.err.println("Invalid search criteria. Available criteria: name, email, or phone.");
            }
        }

        return searchResults;
    }
    
    
    public List<Customer> sortCustomers(String sortBy, boolean descending) {
        List<Customer> sortedCustomers = new ArrayList<>(customers);

        Comparator<Customer> comparator = (c1, c2) -> {
            switch (sortBy.toLowerCase()) {
                case "name":
                    return c1.getName().compareTo(c2.getName());
                case "email":
                    return c1.getEmail().compareTo(c2.getEmail());
                case "id":
                    return Integer.compare(c1.getId(), c2.getId());
                default:
                    System.err.println("Invalid sort criteria. Available criteria: name, email, or id.");
                    return 0;
            }
        };

        if (descending) {
            comparator = comparator.reversed();
        }

        sortedCustomers.sort(comparator);
        return sortedCustomers;
    }
    
    public void addObserver(CustomerObserver observer) {
        observers.add(observer);
    }

    // Remove observer from the list
    public void removeObserver(CustomerObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void update(Customer newCustomer) {
        System.out.println("Observer notified: New customer added - " + newCustomer);
    }
    @Override
    public void updateDeletion(Customer deletedCustomer) {
        System.out.println("Observer notified: Customer deleted - " + deletedCustomer);
    }

    
 
    
}