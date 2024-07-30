package customer_mngt;

public class CustomerRepositoryImpl implements CustomerRepoistory
{
	public Customer findCustomerById(int id) {
        return new Customer(id, "abc");
    }
}
