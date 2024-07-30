package customer_mngt;

public class CustomerService 
{
	private CustomerRepoistory custRepo;

    public CustomerService(CustomerRepoistory custRepo) {
        this.custRepo = custRepo;
    }

    public Customer getCustomerById(int id) {
        return custRepo.findCustomerById(id);
    }
}
