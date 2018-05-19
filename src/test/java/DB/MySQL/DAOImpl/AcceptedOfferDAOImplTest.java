package DB.MySQL.DAOImpl;

import DB.MySQL.ConnectionPool;
import DB.SQLQuery;
import entity.AcceptedOffer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Testing of Accepted offers DAO
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectionPool.class)
public class AcceptedOfferDAOImplTest {
    @Mock
    Connection con;
    @Mock
    ResultSet resultSet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(ConnectionPool.class);
        when(ConnectionPool.getConnection()).thenReturn(con);

    }

    @Test
    public void findAllAcceptedOffers() throws Exception {
        Statement stmt = mock(Statement.class);
        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(SQLQuery.FIND_ALL_ACCEPTED_OFFERS)).thenReturn(resultSet);
        when(resultSet.next(), times(2)).thenReturn(true);

        List<AcceptedOffer> acceptedOffers =AcceptedOfferDAOImpl.getInstance().findAllAcceptedOffers();

        verify(con).close();
        Assert.assertEquals(2, acceptedOffers.size());
    }

    @Test
    public void deleteAcceptedOffer() throws Exception {
    }

    @Test
    public void createAcceptedOffer() throws Exception {
    }

    @Test
    public void findAllAcceptedOffersByUser() throws Exception {
    }

}