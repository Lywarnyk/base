package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.RouteDAO;
import commands.Command;
import controllers.Page;
import entity.Route;
import exception.MainException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 * Creating new Route
 *
 * @author D. Kuliha
 *
 * throws MainException if deprture date is before today
 * or if arrival date is before departure date
 */
public class CreateNewRoute implements Command {

    private static final Logger LOG = Logger.getLogger(CreateNewRoute.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        RouteDAO routeDAO = DAOFactory.getRouteDao((dataBase));
        Route route = getRouteFromRequest(request);
        routeDAO.createRoute(route);

        LOG.debug("Command finished");
        return Page.ROUTE_CREATED.page();
    }

    private Route getRouteFromRequest(HttpServletRequest request) {
        Route route = new Route();

        //obtain creation date
        long currTime = System.currentTimeMillis();
        Date creationDate = new Date(currTime);
        LOG.debug(creationDate + " creation date");
        Date currentDate = Date.valueOf(creationDate.toString());

        //obtain departure date
        Date depDate = Date.valueOf(request.getParameter("departureDate"));
        if(depDate.before(creationDate)&&(!depDate.equals(currentDate))) {
            LOG.error(creationDate + " creation date");
            LOG.error(depDate + " departure date");
            throw new MainException("Departure date cannot be before today.");
        }
        //obtain arrival date
        Date arrDate = Date.valueOf(request.getParameter("arrivalDate"));
        if(depDate.after(arrDate)&&(!depDate.equals(arrDate))) {
            LOG.error(depDate + " departure date");
            LOG.error(arrDate + " arrival date");
            throw new MainException("Departure date cannot be after arrival date.");
        }

        //setting route
        route.setCreationDate(creationDate);
        route.setDepartureDate(depDate);
        route.setArrivalDate(arrDate);
        route.setDeparturePoint(request.getParameter("departurePoint"));
        route.setDestinationPoint(request.getParameter("destinationPoint"));
        route.setDescription(request.getParameter("description"));
        route.setStatus("Open");
        return route;
    }


    ////////////////////////////////
    //Singleton
    ////////////////////////////////

    private CreateNewRoute() {
    }

    private static CreateNewRoute instance;

    public static synchronized CreateNewRoute getInstance() {
        if (instance == null) instance = new CreateNewRoute();
        return instance;
    }
}
