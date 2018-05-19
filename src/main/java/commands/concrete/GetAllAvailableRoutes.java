package commands.concrete;

import DB.DAO.AcceptedOfferDAO;
import DB.DAO.DAOFactory;
import DB.DAO.OfferDAO;
import DB.DAO.RouteDAO;
import commands.Command;
import controllers.Page;
import entity.AcceptedOffer;
import entity.Offer;
import entity.Route;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ListIterator;

/**
 * Select all open routes and compare departure date and arrival date with
 * routes from accepted offers which depends to current user, delete route
 * from routes list if they intersects.
 *
 * @author D. Kuliha
 */
public class GetAllAvailableRoutes implements Command {

    private static final Logger LOG = Logger.getLogger(GetAllAvailableRoutes.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        //obtain DAOs
        RouteDAO routeDAO = DAOFactory.getRouteDao((dataBase));
        OfferDAO offerDAO = DAOFactory.getOfferDao(dataBase);
        AcceptedOfferDAO acceptedOfferDAO = DAOFactory.getAcceptedOfferDao(dataBase);

        //obtain user
        User user = (User) request.getSession().getAttribute("user");
        LOG.debug(user.getLogin() + " starts selecting routes");

        //obtain lists of routes, offers and accepted offers
        List<Route> allRoutes = routeDAO.findAllOpenRoutes();
        List<Offer> offers = offerDAO.findOffersByUserId(user);
        List<AcceptedOffer> acceptOffers = acceptedOfferDAO.findAllAcceptedOffersByUser(user);

        //filter routes
        List<Route> routes = selectAvailableRoutes(allRoutes, offers, acceptOffers);
        request.getSession().setAttribute("routes", routes);

        LOG.debug("Command finished");
        return Page.ROUTES.page();
    }

    private List<Route> selectAvailableRoutes(List<Route> allRoutes, List<Offer> offers, List<AcceptedOffer> acceptOffers) {
        LOG.trace(allRoutes);
        LOG.trace(offers);
        deleteAcceptedOffersRoutes(allRoutes, acceptOffers);
        deleteOffersRoutes(allRoutes, offers);
        return allRoutes;
    }

    /**
     * Deleting routes from list of all routes
     * which user has been offer before.
     *
     * @param allRoutes List of all routes with status 'open'
     * @param offers List of user's offers
     */
    private void deleteOffersRoutes(List<Route> allRoutes, List<Offer> offers) {
        ListIterator<Route> iterator = allRoutes.listIterator();
        for (Offer offer : offers) {
            while (iterator.hasNext()) {
                Route route = iterator.next();
                if (route.getId() == offer.getRoute().getId()) {
                    LOG.debug(route.getId() + " was removed route.getId()==offer.getRoute().getId() offers");
                    iterator.remove();
                } else if (offer.getRoute().intersects(route)) {
                    iterator.remove();
                    LOG.debug(route.getId() + " was removed offer.getRoute().intersects(" + offer.getRoute().getId() + ")");
                }
            }
        }
    }

    /**
     * Deleting user's accepted offers routes from list of all routes.
     *
     * @param allRoutes List of all routes with status 'open'.
     * @param acceptOffers List of user's accepted offers
     */
    private void deleteAcceptedOffersRoutes(List<Route> allRoutes, List<AcceptedOffer> acceptOffers) {
        ListIterator<Route> iterator = allRoutes.listIterator();
        Route route;
        for (AcceptedOffer acceptedOffer : acceptOffers) {
            while (iterator.hasNext()) {
                route = iterator.next();
                if (acceptedOffer.getRoute().intersects(route)) {
                    iterator.remove();
                    LOG.debug(route.getId() + " was removed by intersects with" + acceptedOffer.getRoute().getId());
                }
            }
        }
    }

    //Singleton
    private GetAllAvailableRoutes() {
    }

    private static GetAllAvailableRoutes instance;

    public static synchronized GetAllAvailableRoutes getInstance() {
        if (instance == null) instance = new GetAllAvailableRoutes();
        return instance;
    }

}
