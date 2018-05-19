package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.RouteDAOImpl;
import entity.Route;
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
import java.sql.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Mock testing of command update route
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class UpdateRouteTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RouteDAOImpl routeDAO;
    Route route = new Route();



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);

    }

    @Test
    public void executeTest() throws Exception {

        when(request.getParameter("routeId")).thenReturn("0");
        route.setId(0);
        when(request.getParameter("departurePoint")).thenReturn("Asdf");
        route.setDeparturePoint("Asdf");
        when(request.getParameter("destinationPoint")).thenReturn("Asdf");
        route.setDestinationPoint("Asdf");
        when(request.getParameter("description")).thenReturn("qwerty");
        route.setDescription("qwerty");
        when(DAOFactory.getRouteDao(DataBase.MYSQL)).thenReturn(routeDAO);
        when(request.getParameter("departureDate")).thenReturn("2020-10-10");
        route.setDepartureDate(Date.valueOf("2020-10-10"));
        when(request.getParameter("arrivalDate")).thenReturn("2020-10-11");
        route.setArrivalDate(Date.valueOf("2020-10-11"));

        UpdateRoute.getInstance().execute(request, response);

        verify(routeDAO).updateRoute(route);
    }

    @Test(expected = MainException.class)
    public void executeWrongDateTest() throws Exception {
        when(request.getParameter("departureDate")).thenReturn("2020-10-10");
        when(request.getParameter("arrivalDate")).thenReturn("2020-10-9");

        UpdateRoute.getInstance().execute(request, response);

        verify(routeDAO, never()).updateRoute(any());
    }

    @Test(expected = MainException.class)
    public void executeWrongDateBeforeTodayTest() throws Exception {

        when(request.getParameter("departureDate")).thenReturn("2016-10-10");
        when(request.getParameter("arrivalDate")).thenReturn("2020-10-9");

        UpdateRoute.getInstance().execute(request, response);

        verify(routeDAO, never()).updateRoute(any());
    }


}