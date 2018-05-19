package commands.concrete;

import commands.Command;
import controllers.Page;
import entity.Route;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Sorting list of routes by departure date
 * in two orders
 *
 * @author D. Kuliha
 */
public class SortRoutesByDepartureDate implements Command {

    private static final Logger LOG = Logger.getLogger(SortRoutesByDepartureDate.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        //obtain a lst to sort
        HttpSession session = request.getSession();
        List<Route> routes = (List<Route>) session.getAttribute("routes");
        Object sortedByDepartureDateValue = session.getAttribute("sortedByDepartureDate");
        boolean sortedByDepartureDate;

        //checking if lis was sorted before
        if (sortedByDepartureDateValue != null) {
            sortedByDepartureDate = (boolean) sortedByDepartureDateValue;
        }else {
            sortedByDepartureDate = false;
        }

        //choosing order to sort
        if (sortedByDepartureDate) {
            //reverse order
            routes.sort((r2, r1) -> r1.getDepartureDate().compareTo(r2.getDepartureDate()));
            sortedByDepartureDate = false;
        } else {
            //direct order
            routes.sort((r1, r2) -> r1.getDepartureDate().compareTo(r2.getDepartureDate()));
            sortedByDepartureDate = true;
        }
        session.setAttribute("sortedByDepartureDate", sortedByDepartureDate);

        LOG.debug("Command finished");
        return Page.ROUTES.page();
    }

    ////////////////////////
    //Singleton
    ////////////////////////
    private SortRoutesByDepartureDate() {
    }

    private static SortRoutesByDepartureDate instance;

    public static synchronized SortRoutesByDepartureDate getInstance() {
        if (instance == null) instance = new SortRoutesByDepartureDate();
        return instance;
    }
}
