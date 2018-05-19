package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.RouteDAO;
import commands.Command;
import controllers.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Deleting route by id
 *
 * @author D. Kuliha
 */
public class DeleteRoute implements Command{

    private static final Logger LOG = Logger.getLogger(DeleteRoute.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");
        LOG.trace("routeId to delete " + request.getParameter("routeID"));

        RouteDAO routeDAO = DAOFactory.getRouteDao(dataBase);

        boolean deleted =routeDAO.deleteRouteByID(request.getParameter("routeID"));
        LOG.trace("route deleted " + deleted);
        LOG.debug("Command finished");
        return Page.ROUTE_CREATED.page();
    }

    //////////////////////////
    //Singleton
    //////////////////////////
    private DeleteRoute() {
    }
    private static DeleteRoute instance;
    public static synchronized DeleteRoute getInstance() {
        if (instance == null) instance = new DeleteRoute();
        return instance;
    }
}
