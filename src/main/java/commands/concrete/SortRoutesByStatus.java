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
 * Sorting list of routes by status
 * in two orders
 *
 * @author D. Kuliha
 */

public class SortRoutesByStatus implements Command {

    private static final Logger LOG = Logger.getLogger(SortRoutesByStatus.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        //obtain a lst to sort
        HttpSession session = request.getSession();
        List<Route> routes = (List<Route>) session.getAttribute("routes");
        Object sortedByStatusValue = session.getAttribute("sortedByStatus");

        //checking if list was sorted before
        boolean sortedByStatus;
        sortedByStatus = sortedByStatusValue != null && (boolean) sortedByStatusValue;

        //choosing order to sort
        if (sortedByStatus) {
            routes.sort((r2, r1) -> r1.getStatus().compareTo(r2.getStatus()));
            sortedByStatus = false;
        } else {
            routes.sort((r1, r2) -> r1.getStatus().compareTo(r2.getStatus()));
            sortedByStatus = true;
        }
        session.setAttribute("sortedByStatus", sortedByStatus);

        LOG.debug("Command finished");
        return Page.ROUTES.page();
    }

    ///////////////////////////////////////////
    //Singleton
    //////////////////////////////////////////
    private SortRoutesByStatus() {
    }

    private static SortRoutesByStatus instance;

    public static synchronized SortRoutesByStatus getInstance() {
        if (instance == null) instance = new SortRoutesByStatus();
        return instance;
    }
}