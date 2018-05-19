package DB.DAO;

import entity.AcceptedOffer;
import entity.Offer;
import entity.Route;
import entity.User;

import java.util.List;

/**
 * DAO for interacting with accepted offers table.
 * Only the required DAO methods are defined.
 *
 * @author D. Kuliha
 */
public interface AcceptedOfferDAO {


    List<AcceptedOffer> findAllAcceptedOffers();

    boolean deleteAcceptedOffer(String routeID);

     boolean createAcceptedOffer(Offer offer, String carId);

    /**
     * Method selects from DB only the accepted offers which refer to
     */
    List<AcceptedOffer> findAllAcceptedOffersByUser(User user);


    List<Route> findAllRoutesByCarId(int id);
}
