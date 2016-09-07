import com.cdk.finaltest.dao.VehicleDAO;
import com.cdk.finaltest.dto.Vehicle;
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
public class VehicleTest {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
    VehicleDAO vehicleDAO = (VehicleDAO) applicationContext.getBean("vehicleDAO");
    Vehicle vehicle = new Vehicle();

    @Test
    public void saveVehicle() {
        int vin = 2;
        assertEquals(null,vehicleDAO.get(vin));
        vehicle.setVin(vin);
        vehicle.setModel("v");
        vehicle.setCompany("c");
        vehicle.setDateOfPurchase(new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime()));
        vehicle.setCustomerId(101);
        assertNotNull(vehicleDAO.save(vehicle));
    }

    @Test
    public void getVehicle() {
        int vin = 6557;
        vehicle = vehicleDAO.get(vin);
        assertEquals(0,vehicle.getCustomerId());
    }

    @Test
    public void getCustomerVehicleList() {
        int customer_id = 101;
        int vehicleCount = vehicleDAO.customerVehicleList(101).size();
        assertEquals(2,vehicleCount);

    }

    @Test
    public void getVehicleList() {
        int vehicleCount = vehicleDAO.list().size();
        assertEquals(3,vehicleCount);
    }
}
