package commands.concrete;

import DB.DAO.AcceptedOfferDAO;
import DB.DAO.DAOFactory;
import DB.DAO.OfferDAO;
import DB.DAO.RouteDAO;
import commands.Command;
import controllers.Page;
import entity.Offer;
import entity.Route;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Adding offer with chosen car to accepted offers
 * list, and setting 'In progress' status of route.
 * also deleting all offers from offers list with current route id.
 *
 * @author D. Kuliha
 */
public class AcceptOffer implements Command{

    private static final Logger LOG = Logger.getLogger(AcceptOffer.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");
        //obtain DOAs
        OfferDAO offerDAO = DAOFactory.getOfferDao(dataBase);
        AcceptedOfferDAO acceptedOfferDAO = DAOFactory.getAcceptedOfferDao(dataBase);
        RouteDAO routeDAO = DAOFactory.getRouteDao(dataBase);

        //obtain parameters
        Offer offer = offerDAO.getOfferById(request.getParameter("offerId"));
        String carId = request.getParameter("carId");
        LOG.trace(carId + " car id");

        //creating accepted offer
        acceptedOfferDAO.createAcceptedOffer(offer, carId);

        //deleting offers with current route id
        offerDAO.deleteOffersByRouteId(offer.getRoute().getId());

        //setting status
        Route route = offer.getRoute();
        route.setStatus("In progress");
        route.setId(offer.getRoute().getId());
        routeDAO.updateRouteStatus(route);

        LOG.debug("Command finished");
        return Page.REDIRECT_TO_ACCEPTED_OFFERS.page();
    }

    ///////////////////////
    //Singleton
    ///////////////////////
    private AcceptOffer() {
    }

    private static AcceptOffer instance;

    public static synchronized AcceptOffer getInstance() {
        if (instance == null) instance = new AcceptOffer();
        return instance;
    }
}
