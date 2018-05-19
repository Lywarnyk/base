package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.CarDAOImpl;
import entity.Car;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Mock testing of command create new car
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class CreateNewCarTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    CarDAOImpl carDAO;




    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
    }

    @Test
    public void executeTest() throws Exception {
        Car car = new Car();
        when(DAOFactory.getCarDao(DataBase.MYSQL)).thenReturn(carDAO);
        when(request.getParameter("plateNumber")).thenReturn("AA1111AA");
        car.setPlateNumber("AA1111AA");
        when(request.getParameter("model")).thenReturn("MAN");
        car.setModel("MAN");
        when(request.getParameter("capacity")).thenReturn("10");
        car.setLoadCapacity(10);
        when(request.getParameter("condition")).thenReturn("new");
        car.setCondition("new");

        CreateNewCar.getInstance().execute(request, response);

        verify(carDAO).createCar(car);
    }

}