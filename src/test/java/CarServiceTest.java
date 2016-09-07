import com.cdk.finaltest.dao.CarServiceDAO;
import com.cdk.finaltest.dto.CarServiceDTO;
import com.cdk.finaltest.dto.Vehicle;
import com.cdk.finaltest.service.CarService;
import com.cdk.finaltest.util.DateUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by agrawaay on 9/6/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
    @InjectMocks
    private CarService carService;
    @Mock
    private CarServiceDAO mockedDAO;

    @Test
    public void checkAvailibity() {
        Vehicle vehicle1 = new Vehicle(1,1,"m","c",new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime()));
        Vehicle vehicle2 = new Vehicle(2,1,"m","c",new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime()));
        Date date = new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime());
        BDDMockito.given(mockedDAO.checkAvailablity(date)).willReturn(2);
        assertEquals(true,carService.checkAvailability(date));
    }

    @Test
    public void addForRepair() {
       /* Date date = new Date(DateUtility.stringToDate("2016-09-09","yyyy-MM-dd").getTime());
        CarServiceDTO carServiceDTO = new CarServiceDTO(1,1,date,"reapir","NotStart");
        //BDDMockito.given(mockedDAO.addForRepair(carServiceDTO)).willReturn(carServiceDTO);
        assertEquals(carServiceDTO,carService.addForRepair(carServiceDTO));*/
    }
}
