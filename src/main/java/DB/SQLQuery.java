package DB;

/**
 * Holds application MySQL queries.
 *
 */
public final class SQLQuery {

    //Queries of UserDAO
    public static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    public static final String FIND_ALL_USERS = "SELECT id, login, first_name, last_name, role " +
            "FROM users WHERE role <> 'admin'";
    public static final String UPDATE_USER = "UPDATE users SET login=?, password=?,first_name=?, last_name=?, role=? WHERE id=?";
    public static final String CREATE_USER = "INSERT INTO users VALUES (DEFAULT,?,?,?,?,?)";
    public static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    public static final String FIND_USER_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login=?";

    //Queries of CarDAO
    public static final String FIND_ALL_CARS = "SELECT * FROM cars";
    public static final String UPDATE_CAR = "UPDATE cars " +
            "SET model=?, plate_number=?, load_capacity=?, status=? WHERE car_id=?";
    public static final String UPDATE_CAR_CONDITION = "UPDATE cars SET status=? WHERE car_id=?";
    public static final String CREATE_CAR = "INSERT INTO cars VALUES (DEFAULT,?,?,?,?)";
    public static final String DELETE_CAR = "DELETE FROM cars WHERE car_id=?";
    public static final String FIND_ALL_OPERATIVE_CARS = "SELECT * FROM cars WHERE status<>'need repair'" +
            " AND load_capacity >= ?";
    public static final String FIND_PLATE_NUMBER = "SELECT * FROM cars WHERE plate_number =?";

    //Queries of RouteDAO
    public static final String FIND_ALL_OPEN_ROUTES = "SELECT * FROM routes WHERE status='Open'";
    public static final String FIND_ALL_ROUTE = "SELECT * FROM routes";
    public static final String CREATE_ROUTE = "INSERT INTO routes VALUES(DEFAULT,?,?,?,?,?,?,?)";
    public static final String UPDATE_ROUTE = "UPDATE routes " +
            "SET departure_date=?, arrival_date=?, departure_point=?, " +
            "destination_point=?, description=?, status=?" +
            "WHERE route_id=?";
    public static final String UPDATE_ROUTE_STATUS = "UPDATE routes SET status=? WHERE route_id=?";
    public static final String DELETE_ROUTE_BY_ID = "DELETE FROM routes WHERE route_id=?";

    //Queries of OfferDAO
    public static final String FIND_ALL_OFFERS = "SELECT users.*, routes.*, offers.offer_id, offers.car_params " +
            "FROM ((offers " +
            "INNER JOIN routes ON offers.route_id = routes.route_id) " +
            "INNER JOIN  users ON offers.user_id=users.id)";
    public static final String FIND_OFFER_BY_ID = "SELECT users.*, routes.*, offers.offer_id, offers.car_params " +
            "FROM ((offers " +
            "INNER JOIN routes ON offers.route_id = routes.route_id) " +
            "INNER JOIN  users ON offers.user_id=users.id) " +
            "WHERE offer_id=?";
    public static final String DELETE_OFFER_BY_ID = "DELETE FROM offers WHERE offer_id=? ";
    public static final String FIND_OFFERS_BY_USER_ID = "SELECT routes.*, offers.* FROM offers" +
            " INNER JOIN routes ON offers.route_id=routes.route_id " +
            "WHERE offers.user_id=? AND routes.status<>'In progress'";
    public static final String CREATE_OFFER = "INSERT INTO offers VALUES(DEFAULT, ?,?,?)";
    public static final String DELETE_OFFERS_BY_ROUTE_ID = "DELETE FROM offers WHERE route_id=? ";

    //Queries of AcceptedOfferDAO
    public static final String FIND_ALL_ACCEPTED_OFFERS = "SELECT users.*, routes.*, cars.* " +
            "FROM (((accepted_offers " +
            "INNER JOIN routes ON accepted_offers.route_id = routes.route_id) " +
            "INNER JOIN cars ON accepted_offers.car_id = cars.car_id) " +
            "INNER JOIN  users ON accepted_offers.user_id=users.id)";
    public static final String DELETE_ACCEPTED_OFFER_BY_ROUTE_ID = "DELETE FROM accepted_offers WHERE route_id=?";
    public static final String CREATE_NEW_ACCEPTED_OFFER = "INSERT INTO accepted_offers VALUES(?,?,?)";
    public static final String FIND_ALL_ACCEPTED_OFFERS_BY_USER_ID = "SELECT users.*, routes.*, cars.* " +
            "FROM (((accepted_offers " +
            "INNER JOIN routes ON accepted_offers.route_id = routes.route_id) " +
            "INNER JOIN cars ON accepted_offers.car_id = cars.car_id) " +
            "INNER JOIN  users ON accepted_offers.user_id=users.id) WHERE accepted_offers.user_id = ?";
    public static final String FIND_ALL_ROUTES_BY_CAR_ID = "SELECT routes.* FROM accepted_offers " +
            "INNER JOIN routes ON accepted_offers.route_id = routes.route_id WHERE car_id=?";
    public static final String FIND_CAR_BY_ID = "SELECT * FROM cars WHERE car_id = ?";
}

