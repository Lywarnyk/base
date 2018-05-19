package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Test for get all routes command
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class GetAllRoutesTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RouteDAOImpl routeDAO;
    @Mock
    ArrayList<Route> routes;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
    }

    @Test
    public void execute() throws Exception {
        when(DAOFactory.getRouteDao(DataBase.MYSQL)).thenReturn(routeDAO);
        when(routeDAO.findAllRoutes()).thenReturn(routes);
        when(request.getSession()).thenReturn(session);

        GetAllRoutes.getInstance().execute(request, response);

        verify(session).setAttribute("routes", routes);
    }

}