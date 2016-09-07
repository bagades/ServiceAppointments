import com.cdk.finaltest.dao.CustomerDAO;
import com.cdk.finaltest.dto.Customer;
import com.cdk.finaltest.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by agrawaay on 9/6/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegisterServiceTest {
    @InjectMocks
    private RegisterService registerService;

    @Mock
    private CustomerDAO mockedDAO;

    @Test
    public void saveCustomerTest() {

        Customer customer1 = new Customer(1,"ayushi","a@a.c","12345",1234567890,"pune","pune","mh",123456);
        BDDMockito.given(mockedDAO.save(customer1)).willReturn(customer1);
        Customer dto =  registerService.save(customer1);
        assertEquals(dto,customer1);
    }
}
