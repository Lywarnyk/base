package commands.concrete;

import entity.Route;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for sorting list of routes by departure date
 *
 * @author D. Kuliha
 */
public class SortRoutesByDepartureDateTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    List<Route> routes;
    Route route1;
    Route route2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        routes = new ArrayList<>();
        route1 = new Route();
        route1.setId(1);
        route1.setDepartureDate(Date.valueOf("2020-01-01"));
        route2 = new Route();
        route2.setId(2);
        route2.setDepartureDate(Date.valueOf("2018-01-01"));
    }

    @Test
    public void executeDirectOrderTest() throws Exception {
        routes.add(route1);
        routes.add(route2);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("routes")).thenReturn(routes);

        SortRoutesByDepartureDate.getInstance().execute(request, response);

        verify(session).setAttribute("sortedByDepartureDate", true);
        assertEquals(route2, routes.get(0));
    }
    @Test
    public void executeReverseOrderTest() throws Exception {
        routes.add(route2);
        routes.add(route1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("routes")).thenReturn(routes);
        when(session.getAttribute("sortedByDepartureDate")).thenReturn(true);

        SortRoutesByDepartureDate.getInstance().execute(request, response);

        verify(session).setAttribute("sortedByDepartureDate", false);
        assertEquals(route1, routes.get(0));
    }

}