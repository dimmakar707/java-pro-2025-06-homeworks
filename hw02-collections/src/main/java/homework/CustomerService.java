package homework;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    TreeMap<Customer, String> customers = new TreeMap<>(new ScoresComparator());

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = customers.firstEntry();
        if (entry == null) return null;
        Customer key = entry.getKey();
        Customer keyCopy = new Customer(key.getId(), key.getName(), key.getScores());
        return new AbstractMap.SimpleEntry<>(keyCopy, entry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = customers.higherEntry(customer);
        if (entry == null) return null;
        Customer key = entry.getKey();
        Customer keyCopy = new Customer(key.getId(), key.getName(), key.getScores());
        return new AbstractMap.SimpleEntry<>(keyCopy, entry.getValue());
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}

class ScoresComparator implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return (int) c1.getScores() - (int) c2.getScores();
    }
}
