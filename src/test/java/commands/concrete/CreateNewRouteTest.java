package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.RouteDAOImpl;
import exception.MainException;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Mock testing of command create new route
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class CreateNewRouteTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RouteDAOImpl routeDAO;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
        when(request.getParameter("departurePoint")).thenReturn("Asdf");
        when(request.getParameter("destinationPoint")).thenReturn("Asdf");
        when(request.getParameter("description")).thenReturn("qwerty");
        when(DAOFactory.getRouteDao(DataBase.MYSQL)).thenReturn(routeDAO);
    }

    @Test
    public void executeTest() throws Exception {

        when(request.getParameter("departureDate")).thenReturn("2020-10-10");
        when(request.getParameter("arrivalDate")).thenReturn("2020-10-11");

        CreateNewRoute.getInstance().execute(request, response);

        verify(routeDAO).createRoute(any());
    }

    @Test(expected = MainException.class)
    public void executeWrongDateTest() throws Exception {
        when(request.getParameter("departureDate")).thenReturn("2020-10-10");
        when(request.getParameter("arrivalDate")).thenReturn("2020-10-9");

        CreateNewRoute.getInstance().execute(request, response);

        verify(routeDAO, never()).createRoute(any());
    }

    @Test(expected = MainException.class)
    public void executeWrongDateBeforeTodayTest() throws Exception {

        when(request.getParameter("departureDate")).thenReturn("2016-10-10");
        when(request.getParameter("arrivalDate")).thenReturn("2020-10-9");

        CreateNewRoute.getInstance().execute(request, response);

        verify(routeDAO, never()).createRoute(any());
    }

}