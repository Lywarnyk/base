package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.RouteDAO;
import commands.Command;
import controllers.Page;
import entity.Route;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Getting a list of all offers from DB
 * and putting it to the session scope
 *
 * @author D. Kuliha
 */
public class GetAllRoutes implements Command {

    private static final Logger LOG = Logger.getLogger(GetAllRoutes.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        RouteDAO routeDAO = DAOFactory.getRouteDao((dataBase));
        List<Route> routes = routeDAO.findAllRoutes();
        request.getSession().setAttribute("routes", routes);

        LOG.debug("Command finished");
        return Page.ROUTES.page();
    }

    /////////////////////////////
    //Singleton
    //////////////////////////////
    private GetAllRoutes() {
    }
    private static GetAllRoutes instance;
    public static synchronized GetAllRoutes getInstance() {
        if (instance == null) instance = new GetAllRoutes();
        return instance;
    }

}
