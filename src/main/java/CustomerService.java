
public class CustomerService {

    public Customer addCustomer(Customer customer) {
        System.out.println("CustomerService addCustomer");
        return new Customer(customer.getEmail(),customer.getName(),customer.getAge());
    }
}
