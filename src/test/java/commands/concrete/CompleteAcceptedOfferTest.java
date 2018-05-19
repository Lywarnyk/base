package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.CarDAOImpl;
import DB.MySQL.DAOImpl.RouteDAOImpl;
import entity.Route;
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
 * Mock testing of command Complete Accepted Offer
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class CompleteAcceptedOfferTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    CarDAOImpl carDAO;
    @Mock
    RouteDAOImpl routeDAO;
    @Mock
    Route route;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getCarDao(DataBase.MYSQL)).thenReturn(carDAO);
        when(DAOFactory.getRouteDao(DataBase.MYSQL)).thenReturn(routeDAO);
        when(request.getParameter("carCondition")).thenReturn("good");
        when(request.getParameter("routeID")).thenReturn("0");
    }

    @Test
    public void executeTest() throws Exception {

        CompleteAcceptedOffer.getInstance().execute(request, response);
        Route expectedRoute = new Route();
        expectedRoute.setId(0);
        expectedRoute.setStatus("Completed");

        verify(routeDAO).updateRouteStatus(expectedRoute);
    }

}