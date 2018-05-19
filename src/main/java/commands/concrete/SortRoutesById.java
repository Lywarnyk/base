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
 * Sorting list of routes by id
 * in two orders
 *
 * @author D. Kuliha
 */
public class SortRoutesById implements Command {

    private static final Logger LOG = Logger.getLogger(SortRoutesById.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        //obtain a lst to sort
        HttpSession session = request.getSession();
        List<Route> routes = (List<Route>) session.getAttribute("routes");
        Object sortedByIdValue = session.getAttribute("sortedById");

        //checking if list was sorted before
        boolean sortedById;
        if (sortedByIdValue != null) {
            sortedById = (boolean) sortedByIdValue;
        }else
            sortedById = false;

        //choosing order to sort
        if (sortedById) {
            //reverse order
            routes.sort((r2, r1) -> r1.getId() - r2.getId());
            sortedById = false;
        } else {
            //direct order
            routes.sort((r1, r2) -> r1.getId() - r2.getId());
            sortedById = true;
        }
        session.setAttribute("sortedById", sortedById);

        LOG.debug("Command finished");
        return Page.ROUTES.page();
    }
    ////////////////////////////////////
    //Singleton
    /////////////////////////////////
    private SortRoutesById() {
    }

    private static SortRoutesById instance;

    public static synchronized SortRoutesById getInstance() {
        if (instance == null) instance = new SortRoutesById();
        return instance;
    }
}
