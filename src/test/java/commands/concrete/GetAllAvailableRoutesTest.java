package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.AcceptedOfferDAOImpl;
import DB.MySQL.DAOImpl.OfferDAOImpl;
import DB.MySQL.DAOImpl.RouteDAOImpl;
import entity.Route;
import entity.User;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Test for get all accepted offers command
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class GetAllAvailableRoutesTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    OfferDAOImpl offerDAO;
    @Mock
    RouteDAOImpl routeDAO;
    @Mock
    AcceptedOfferDAOImpl acceptedOfferDAO;
    @Mock
    HttpSession session;
    @Mock
    User user;
    @Mock
    ArrayList<Route> allRoutes;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);

    }

    @Test
    public void execute() throws Exception {

        when(DAOFactory.getRouteDao(DataBase.MYSQL)).thenReturn(routeDAO);
        when(DAOFactory.getOfferDao(DataBase.MYSQL)).thenReturn(offerDAO);
        when(DAOFactory.getAcceptedOfferDao(DataBase.MYSQL)).thenReturn(acceptedOfferDAO);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(routeDAO.findAllOpenRoutes()).thenReturn(allRoutes);

        GetAllAvailableRoutes.getInstance().execute(request, response);

        verify(session).setAttribute("routes", allRoutes);

    }

}