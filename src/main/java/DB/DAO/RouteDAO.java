package DB.DAO;

import entity.Route;

import java.util.List;

/**
 * DAO for interacting with routes table.
 * Only the required DAO methods are defined.
 *
 * @author D. Kuliha
 */
public interface RouteDAO {


    List<Route> findAllRoutes();

    boolean createRoute(Route route);

    boolean updateRoute(Route route);

    boolean updateRouteStatus(Route route);

    boolean deleteRoute(Route route);

    boolean deleteRouteByID(String routeID);

    List<Route> findAllOpenRoutes();

    boolean createRoutes(List<Route> routes);
}
