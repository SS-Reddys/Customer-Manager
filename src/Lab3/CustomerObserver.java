package Lab3;

public interface CustomerObserver {
    void update(Customer newCustomer);
    void updateDeletion(Customer deletedCustomer);
}