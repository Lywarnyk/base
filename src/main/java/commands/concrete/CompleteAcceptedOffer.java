package commands.concrete;

import DB.DAO.CarDAO;
import DB.DAO.DAOFactory;
import DB.DAO.RouteDAO;
import commands.Command;
import controllers.Page;
import entity.Route;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Updating status of route on 'Completed',
 * updating car condition
 *
 * @author D. Kuliha
 */
public class CompleteAcceptedOffer implements Command{

    private static final Logger LOG = Logger.getLogger(CompleteAcceptedOffer.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        //obtain DAOs
        CarDAO carDAO = DAOFactory.getCarDao(dataBase);
        RouteDAO routeDAO = DAOFactory.getRouteDao(dataBase);

        //update car condition
        carDAO.updateCarCondition(request.getParameter("carCondition"), Integer.parseInt(request.getParameter("routeID")));

        //update route status on 'Completed'
        Route route = new Route();
        route.setStatus("Completed");
        route.setId(Integer.parseInt(request.getParameter("routeID")));
        routeDAO.updateRouteStatus(route);

        LOG.debug("Command finished");
        return Page.REDIRECT_TO_ACCEPTED_OFFERS_BY_USER_ID.page();
    }

    ////////////////////////
    //Singleton
    ////////////////////////
    private CompleteAcceptedOffer() {
    }
    private static CompleteAcceptedOffer instance;
    public static synchronized CompleteAcceptedOffer getInstance() {
        if (instance == null) instance = new CompleteAcceptedOffer();
        return instance;
    }
}
