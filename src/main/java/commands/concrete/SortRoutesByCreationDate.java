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
 * Sorting list of routes by creation date
 * in two orders
 *
 * @author D. Kuliha
 */
public class SortRoutesByCreationDate implements Command {

    private static final Logger LOG = Logger.getLogger(SortRoutesByCreationDate.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        List<Route> routes = (List<Route>) session.getAttribute("routes");
        Object sortedByCreationDateValue = session.getAttribute("sortedByCreationDate");
        boolean sortedByCreationDate;
        //checking if list was sorted before
        if (sortedByCreationDateValue != null) {
            sortedByCreationDate = (boolean) sortedByCreationDateValue;
        }else {
            sortedByCreationDate = false;
        }

        //choosing order of sorting
        if (sortedByCreationDate) {
            //reverse order
            routes.sort((r2, r1) -> r1.getCreationDate().compareTo(r2.getCreationDate()));
            sortedByCreationDate = false;
        } else {
            //direct order
            routes.sort((r1, r2) -> r1.getCreationDate().compareTo(r2.getCreationDate()));
            sortedByCreationDate = true;
        }
        session.setAttribute("sortedByCreationDate", sortedByCreationDate);

        LOG.debug("Command finished");
        return Page.ROUTES.page();
    }

    /////////////////////////////////////////////
    //Singleton
    ///////////////////////////////////////////
    private SortRoutesByCreationDate() {
    }
    private static SortRoutesByCreationDate instance;
    public static synchronized SortRoutesByCreationDate getInstance() {
        if (instance == null) instance = new SortRoutesByCreationDate();
        return instance;
    }
}
