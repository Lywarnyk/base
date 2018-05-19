package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.AcceptedOfferDAOImpl;
import entity.AcceptedOffer;
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
import static org.mockito.Mockito.when;

/**
 * Test for get all accepted offers by user id command
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class GetAcceptedOffersByUserIdTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    User user;
    @Mock
    AcceptedOfferDAOImpl acceptedOfferDAO;
    @Mock
    ArrayList<AcceptedOffer> acceptedOffers;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
    }

    @Test
    public void executeTest() throws Exception {

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(DAOFactory.getAcceptedOfferDao(DataBase.MYSQL)).thenReturn(acceptedOfferDAO);
        when(acceptedOfferDAO.findAllAcceptedOffersByUser(user)).thenReturn(acceptedOffers);

        GetAcceptedOffersByUserId.getInstance().execute(request, response);

        verify(acceptedOfferDAO).findAllAcceptedOffersByUser(user);
        verify(session).setAttribute("acceptedOffers", acceptedOffers);
    }

}