package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.AcceptedOfferDAOImpl;
import DB.MySQL.DAOImpl.OfferDAOImpl;
import DB.MySQL.DAOImpl.RouteDAOImpl;
import entity.Offer;
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
 * Mock testing of command Accept Offer
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class AcceptOfferTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    OfferDAOImpl offerDAO;
    @Mock
    AcceptedOfferDAOImpl acceptedOfferDAO;
    @Mock
    RouteDAOImpl routeDAO;
    @Mock
    Offer offer;
    @Mock
    Route route;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getOfferDao(DataBase.MYSQL)).thenReturn(offerDAO);
        when(DAOFactory.getAcceptedOfferDao(DataBase.MYSQL)).thenReturn(acceptedOfferDAO);
        when(DAOFactory.getRouteDao(DataBase.MYSQL)).thenReturn(routeDAO);
        when(request.getParameter("offerId")).thenReturn("0");
        when(request.getParameter("offerId")).thenReturn("0");
        when(request.getParameter("carId")).thenReturn("1");
        when(offerDAO.getOfferById("0")).thenReturn(offer);
        when(offer.getRoute()).thenReturn(route);
        when(route.getId()).thenReturn(2);
    }

    @Test
    public void execute() throws Exception {

        AcceptOffer.getInstance().execute(request, response);

        verify(acceptedOfferDAO).createAcceptedOffer(offer, "1");
        verify(offerDAO).deleteOffersByRouteId(2);
        verify(routeDAO).updateRouteStatus(route);
    }

}