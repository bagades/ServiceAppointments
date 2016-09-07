import com.cdk.finaltest.dao.CarServiceDAO;
import com.cdk.finaltest.domain.CarService;
import com.cdk.finaltest.dto.CarServiceDTO;
import com.cdk.finaltest.util.DateUtility;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by agrawaay on 9/3/2016.
 */

public class CarServiceDAOTests {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
    CarServiceDAO carServiceDAO = (CarServiceDAO) applicationContext.getBean("carServiceDAO");

    private CarServiceDAO mockedDAO;


    @Test
    public void checkAvailability() {
        Date date = new Date(DateUtility.stringToDate("2016-09-08","yyyy-MM-dd").getTime());
        assertEquals(5,carServiceDAO.checkAvailablity(date));
    }

    @Test
    public void addForRepair() {
        CarServiceDTO carServiceDTO = new CarServiceDTO();
        carServiceDTO.setVin(1);
        Date date = new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime());
        carServiceDTO.setDateOfServicing(date);
        carServiceDTO.setServiceId(20);
        carServiceDTO.setServiceType("repair");
        carServiceDTO.setStatus("not start");
        assertNotNull(carServiceDAO.addForRepair(carServiceDTO));
    }
}
