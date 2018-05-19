package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.AcceptedOfferDAOImpl;
import DB.MySQL.DAOImpl.CarDAOImpl;
import DB.MySQL.DAOImpl.OfferDAOImpl;
import entity.Car;
import entity.Offer;
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
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Mock testing of command Choose car
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class ChooseCarTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    OfferDAOImpl offerDAO;
    @Mock
    ArrayList<Car> cars;
    @Mock
    Offer offer;
    @Mock
    CarDAOImpl carDAO;
    @Mock
    AcceptedOfferDAOImpl acceptedOfferDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getOfferDao(DataBase.MYSQL)).thenReturn(offerDAO);
        when(DAOFactory.getCarDao(DataBase.MYSQL)).thenReturn(carDAO);
        when(DAOFactory.getAcceptedOfferDao(DataBase.MYSQL)).thenReturn(acceptedOfferDAO);
        when(request.getParameter("offerID")).thenReturn("0");
        when(offerDAO.getOfferById("0")).thenReturn(offer);
        when(offer.getCarParams()).thenReturn("10");
    }


    @Test
    public void executeTest() throws Exception {

        ChooseCar.getInstance().execute(request, response);

        verify(acceptedOfferDAO).findAllAcceptedOffers();
        verify(carDAO).findAllOperativeCars("10");
    }

}