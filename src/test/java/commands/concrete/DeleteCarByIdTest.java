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
 * Mock testing of command Delete car by id
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class DeleteCarByIdTest {
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
        when(DAOFactory.getCarDao(DataBase.MYSQL)).thenReturn(carDAO);
    }

    @Test
    public void execute() throws Exception {
        Car car = new Car();
        car.setId(0);
        when(request.getParameter("carId")).thenReturn("0");

        DeleteCarById.getInstance().execute(request, response);

        verify(carDAO).deleteCar(car);
    }

}