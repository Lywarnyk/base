package entity;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing routes intersection
 *
 * @author D. Kuliha
 */
public class RouteTest {
    private Route firstRoute;
    private Route secondRoute;

    @Before
    public void createRoutes(){
        firstRoute = new Route();
        secondRoute = new Route();
    }
    @Test
    public void intersectsOneselfTest() throws Exception {
        firstRoute.setDepartureDate(Date.valueOf("2018-01-20"));
        firstRoute.setArrivalDate(Date.valueOf("2018-01-22"));
        assertTrue(firstRoute.intersects(firstRoute));
    }
    @Test
    public void intersects2RoutesTest() throws Exception {
        firstRoute.setDepartureDate(Date.valueOf("2018-01-20"));
        firstRoute.setArrivalDate(Date.valueOf("2018-01-22"));
        secondRoute.setDepartureDate(Date.valueOf("2018-01-20"));
        secondRoute.setArrivalDate(Date.valueOf("2018-01-21"));
        assertTrue(firstRoute.intersects(secondRoute));
    }
    @Test
    public void intersects2RoutesOneInAnotherTest() throws Exception {
        firstRoute.setDepartureDate(Date.valueOf("2018-01-20"));
        firstRoute.setArrivalDate(Date.valueOf("2018-01-28"));
        secondRoute.setDepartureDate(Date.valueOf("2018-01-22"));
        secondRoute.setArrivalDate(Date.valueOf("2018-01-24"));
        assertTrue(firstRoute.intersects(secondRoute));
    }

    @Test
    public void intersects2RoutesOneInAnotherReverseTest() throws Exception {
        firstRoute.setDepartureDate(Date.valueOf("2018-02-06"));
        firstRoute.setArrivalDate(Date.valueOf("2019-02-06"));
        secondRoute.setDepartureDate(Date.valueOf("2018-03-23"));
        secondRoute.setArrivalDate(Date.valueOf("2018-03-25"));
        assertTrue(firstRoute.intersects(secondRoute));
    }
    @Test
    public void intersects2RoutesWithSameArrAndDepDateTest() throws Exception {
        firstRoute.setDepartureDate(Date.valueOf("2018-01-20"));
        firstRoute.setArrivalDate(Date.valueOf("2018-01-21"));
        secondRoute.setDepartureDate(Date.valueOf("2018-01-21"));
        secondRoute.setArrivalDate(Date.valueOf("2018-01-22"));
        assertFalse(firstRoute.intersects(secondRoute));
    }
    @Test
    public void intersects2DifferentRoutesTest() throws Exception {
        firstRoute.setDepartureDate(Date.valueOf("2019-01-20"));
        firstRoute.setArrivalDate(Date.valueOf("2019-01-21"));
        secondRoute.setDepartureDate(Date.valueOf("2018-01-21"));
        secondRoute.setArrivalDate(Date.valueOf("2018-01-22"));
        assertFalse(firstRoute.intersects(secondRoute));
    }

}