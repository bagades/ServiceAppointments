import com.cdk.finaltest.dao.CustomerDAO;
import com.cdk.finaltest.dto.Customer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


/**
 * Created by agrawaay on 9/3/2016.
 */
public class CustomerTests {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
    CustomerDAO customerDAO = (CustomerDAO) applicationContext.getBean("customerDAO");
    Customer customer = new Customer();


    @Test
    public void getCustomer() {

        String email = "b@s.c";
        String password = "12345";

        customer = customerDAO.getCustomer(email,password);
        assertEquals(456757858,customer.getPhoneNo());
    }

    @Test
    public void saveCustomer() {

        String email = "q@c.c";
        String password = "12345";
        assertEquals(null,customerDAO.getCustomer(email,password));
            customer.setName("xyz");
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setCity("c");
            customer.setState("s");
            customer.setAddress("a");
            customer.setPhoneNo(1);
            customer.setPostalCode(1);
        assertNotNull(customerDAO.save(customer));
    }

    @Test
    public void getCustomerList() {
        int customerCount = customerDAO.list().size();
        assertEquals(7,customerCount);
    }



}
