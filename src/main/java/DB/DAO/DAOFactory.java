package DB.DAO;


import DB.DataBase;
import DB.MySQL.DAOImpl.*;

/**
 * Factory pattern for all DAO interfaces.
 * There is an opportunity to switch between different DataBases
 *
 *
 * @author D. Kuliha
 */
public abstract class DAOFactory {

    public static UserDAO getUserDao(DataBase dataBase) {
        switch (dataBase) {
            case MYSQL:
                return UserDAOImpl.getInstance();
            default:
                return UserDAOImpl.getInstance();
        }

    }

    public static CarDAO getCarDao(DataBase dataBase) {
        switch (dataBase) {
            case MYSQL:
                return CarDAOImpl.getInstance();
            default:
                return CarDAOImpl.getInstance();
        }
    }

    public static RouteDAO getRouteDao(DataBase dataBase) {
        switch (dataBase) {
            case MYSQL:
                return RouteDAOImpl.getInstance();
            default:
                return RouteDAOImpl.getInstance();
        }
    }

    public static OfferDAO getOfferDao(DataBase dataBase) {
        switch (dataBase) {
            case MYSQL:
                return OfferDAOImpl.getInstance();
            default:
                return OfferDAOImpl.getInstance();
        }

    }

    public static AcceptedOfferDAO getAcceptedOfferDao(DataBase dataBase) {
        switch (dataBase) {
            case MYSQL:
                return AcceptedOfferDAOImpl.getInstance();
            default:
                return AcceptedOfferDAOImpl.getInstance();
        }
    }
}