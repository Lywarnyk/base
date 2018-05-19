package DB.util;

import entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extracting entities from result set
 *
 * @author D. Kuliha
 */
public class Extractor {

    public static User extractUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setRole(resultSet.getString("role"));
        return user;
    }

    public static Car extractCar(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("car_id"));
        car.setPlateNumber(resultSet.getString("plate_number"));
        car.setModel(resultSet.getString("model"));
        car.setLoadCapacity(resultSet.getInt("load_capacity"));
        car.setCondition(resultSet.getString("status"));
        return car;
    }

    public static Route extractRoute(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt("route_id"));
        route.setCreationDate(resultSet.getDate("creation_date"));
        route.setDepartureDate(resultSet.getDate("departure_date"));
        route.setArrivalDate(resultSet.getDate("arrival_date"));
        route.setDeparturePoint(resultSet.getString("departure_point"));
        route.setDestinationPoint(resultSet.getString("destination_point"));
        route.setDescription(resultSet.getString("description"));
        route.setStatus(resultSet.getString("status"));
        return route;
    }

    public static Offer extractOffer(ResultSet resultSet) throws SQLException {
        Offer offer = new Offer();
        offer.setOfferID(resultSet.getInt("offer_id"));
        offer.setRoute(extractRoute(resultSet));
        offer.setUser(extractUser(resultSet));
        offer.setCarParams(resultSet.getString("car_params"));
        return offer;
    }

    public static AcceptedOffer extractAcceptedOffer(ResultSet resultSet) throws SQLException {
        AcceptedOffer acceptedOffer = new AcceptedOffer();
        acceptedOffer.setCar(extractCar(resultSet));
        acceptedOffer.setRoute(extractRoute(resultSet));
        acceptedOffer.setUser(extractUser(resultSet));
        return acceptedOffer;
    }

    public static Offer extractOffer(ResultSet resultSet, User user) throws SQLException {
        Offer offer = new Offer();
        offer.setOfferID(resultSet.getInt("offer_id"));
        offer.setRoute(extractRoute(resultSet));
        offer.setUser(user);
        offer.setCarParams(resultSet.getString("car_params"));
        return offer;
    }

}
