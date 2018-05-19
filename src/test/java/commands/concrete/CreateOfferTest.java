package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.OfferDAOImpl;
import entity.Offer;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Mock testing of command create new offer
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class CreateOfferTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    OfferDAOImpl offerDAO;
    @Mock
    HttpSession session;
    @Mock
    User user;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("routeId")).thenReturn("0");
        when(request.getParameter("carParams")).thenReturn("10");
        when(DAOFactory.getOfferDao(DataBase.MYSQL)).thenReturn(offerDAO);
    }

    @Test
    public void executeTest() throws Exception {
        Offer offer = new Offer();
        Route route = new Route();
        offer.setUser(user);
        route.setId(0);
        offer.setRoute(route);
        offer.setCarParams("10");

        CreateOffer.getInstance().execute(request, response);

        verify(offerDAO).createOffer(offer);
    }

}