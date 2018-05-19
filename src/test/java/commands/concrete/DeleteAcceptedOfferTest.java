package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.AcceptedOfferDAOImpl;
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
 * Mock testing of command Delete Accepted Offer
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class DeleteAcceptedOfferTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    AcceptedOfferDAOImpl acceptedOfferDAO;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getAcceptedOfferDao(DataBase.MYSQL)).thenReturn(acceptedOfferDAO);
    }

    @Test
    public void execute() throws Exception {
       when(request.getParameter("routeID")).thenReturn("0");

       DeleteAcceptedOffer.getInstance().execute(request, response);

       verify(acceptedOfferDAO).deleteAcceptedOffer("0");
    }

}