package homework;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {

    Deque<Customer> customers = new ArrayDeque<>();

    public void add(Customer customer) {
        customers.offerLast(customer);
    }

    public Customer take() {
        return customers.pollLast();
    }
}
