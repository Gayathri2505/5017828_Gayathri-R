package customer_mngt;

public class DependencyMain 
{
	public static void main(String args[])
	{
		CustomerRepoistory obj=new CustomerRepositoryImpl();
		CustomerService service=new CustomerService(obj);
		
		Customer c=service.getCustomerById(1);
		System.out.println(c);
	}
}
