package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.OfferDAO;
import commands.Command;
import controllers.Page;
import entity.Offer;
import entity.Route;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Creates new offer
 *
 * @author D. Kuliha
 */
public class CreateOffer implements Command {

    private static final Logger LOG = Logger.getLogger(CreateOffer.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        OfferDAO offerDAO = DAOFactory.getOfferDao(dataBase);
        Offer offer = new Offer();
        Route route = new Route();

        //setting offer
        offer.setUser((User)request.getSession().getAttribute("user"));
        route.setId(Integer.parseInt(request.getParameter("routeId")));
        offer.setRoute(route);
        offer.setCarParams(request.getParameter("carParams"));
        offerDAO.createOffer(offer);

        LOG.debug("Command finished");
        return Page.OFFERS_BY_USER_ID.page();
    }

    ////////////////////////////////
    //Singleton
    ///////////////////////////////
    private CreateOffer() {}
    private static CreateOffer instance;
    public static synchronized CreateOffer getInstance() {
        if (instance == null) instance = new CreateOffer();
        return instance;
    }
}
