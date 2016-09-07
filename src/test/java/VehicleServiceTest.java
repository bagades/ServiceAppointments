import com.cdk.finaltest.dao.VehicleDAO;
import com.cdk.finaltest.dto.Vehicle;
import com.cdk.finaltest.service.VehicleService;
import com.cdk.finaltest.util.DateUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by agrawaay on 9/6/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {
    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleDAO mockedDAO;

    @Test
    public void getVehicleList() {
        Vehicle vehicle1 = new Vehicle(1,1,"m","c",new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime()));
        Vehicle vehicle2 = new Vehicle(2,1,"m","c",new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime()));
        System.out.println(mockedDAO);
        BDDMockito.given(mockedDAO.list()).willReturn(Arrays.asList(vehicle1,vehicle2));
        List<Vehicle> vehicleList = vehicleService.list();
        assertEquals(2,vehicleList.size());
    }

    @Test
    public void getVehicle() {
        Vehicle vehicle1 = new Vehicle(1,1,"m","c",new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime()));
        Vehicle vehicle2 = new Vehicle(2,1,"m","c",new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime()));
        BDDMockito.given(mockedDAO.get(1)).willReturn(vehicle1);
        Vehicle vehicle = vehicleService.get(1);
        assertEquals(vehicle1,vehicle);
    }

    @Test
    public void saveVehicle() {
        Vehicle vehicle1 = new Vehicle(1,1,"m","c",new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime()));
        Vehicle vehicle2 = new Vehicle(2,1,"m","c",new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime()));
        BDDMockito.given(mockedDAO.save(vehicle1)).willReturn(vehicle1);
        Vehicle vehicle = vehicleService.save(vehicle1);
        assertEquals(vehicle1,vehicle);
    }

    @Test
    public void getCustomerVehicleList() {
        Vehicle vehicle1 = new Vehicle(1,1,"m","c",new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime()));
        Vehicle vehicle2 = new Vehicle(2,1,"m","c",new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime()));
        BDDMockito.given(mockedDAO.customerVehicleList(1)).willReturn(Arrays.asList(vehicle1,vehicle2));
        List<Vehicle> vehicleList = vehicleService.customerVehicleList(1);
        assertEquals(2,vehicleList.size());
        assertEquals(vehicle1,vehicleList.get(0));
    }
}
