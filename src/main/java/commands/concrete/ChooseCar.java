package commands.concrete;

import DB.DAO.AcceptedOfferDAO;
import DB.DAO.CarDAO;
import DB.DAO.DAOFactory;
import DB.DAO.OfferDAO;
import commands.Command;
import controllers.Page;
import entity.AcceptedOffer;
import entity.Car;
import entity.Offer;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Gets list of cars with new or good condition and selected
 * load capacity in offer from database
 * and filters it.
 *
 * @author D. Kuliha
 */
public class ChooseCar implements Command {

    private static final Logger LOG = Logger.getLogger(ChooseCar.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        OfferDAO offerDAO = DAOFactory.getOfferDao(dataBase);
        Offer offer = offerDAO.getOfferById(request.getParameter("offerID"));
        List<Car> freeCars = selectFreeCars(offer);
        request.setAttribute("freeCars", freeCars);
        request.setAttribute("offerId", offer.getOfferID());
        LOG.debug("Command finished");

        return Page.CHOOSE_CAR.page();
    }

    private List<Car> selectFreeCars(Offer offer) {
        CarDAO carDAO = DAOFactory.getCarDao(dataBase);

        //get all cars
        List<Car> allCars = carDAO.findAllOperativeCars(offer.getCarParams());
        AcceptedOfferDAO acceptedOfferDAO = DAOFactory.getAcceptedOfferDao(dataBase);

        //get all accepted offers
        List<AcceptedOffer> acceptedOffers = acceptedOfferDAO.findAllAcceptedOffers();

        //check if route dates of the offer intersect with dates of accepted offers routes dates
        for (AcceptedOffer acceptedOffer : acceptedOffers) {
            if(acceptedOffer.getRoute().intersects(offer.getRoute())){
                LOG.debug("route "+acceptedOffer.getRoute().getId()+ ".intersects("+ offer.getRoute().getId()+ ")");
                allCars.remove(acceptedOffer.getCar());
                LOG.debug("car was removed " + acceptedOffer.getCar().getId());
            }
        }
        return allCars;
    }
    ///////////////////////////
    //Singleton
    ///////////////////////////
    private ChooseCar() {
    }

    private static ChooseCar instance;

    public static synchronized ChooseCar getInstance() {
        if (instance == null) instance = new ChooseCar();
        return instance;
    }
}
