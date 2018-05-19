package controllers;

/**
 * Enum of all paths of pages.
 *
 * @author D. Kuliha
 */
public enum Page {
    ADMIN_PAGE("/jsp/startPages/adminPage.jsp"),
    DRIVER_PAGE("/jsp/startPages/driverPage.jsp"),
    DISPATCHER_PAGE("/jsp/startPages/dispatcherPage.jsp"),
    CARS("/jsp/resultPages/cars.jsp"),
    USERS("/jsp/resultPages/users.jsp"),
    ROUTES("/jsp/resultPages/routes.jsp"),
    OFFERS("/jsp/resultPages/offers.jsp"),
    USER_CREATED("/controller?command=getAllUsers"),
    ACCEPTED_OFFERS("/jsp/resultPages/acceptedOffers.jsp"),
    CAR_CREATED("/controller?command=getAllCars"),
    ROUTE_CREATED("/controller?command=getAllRoutes"),
    CHOOSE_CAR("/jsp/resultPages/chooseCar.jsp"),
    ERROR_PAGE("/jsp/error/errorPage.jsp"),
    INDEX_PAGE("/index.jsp"),
    REDIRECT_TO_ACCEPTED_OFFERS("/controller?command=getAllAcceptedOffers"),
    OFFERS_BY_USER_ID("/controller?command=getOffersByUserId"),
    OFFER_CREATED("/controller?command=getAllOffers"),
    REDIRECT_TO_DOWNLOAD_SERVLET("/downloadFileServlet"),
    WELCOME("/jsp/welcome.jsp"),
    REDIRECT_TO_ROUTES("/controller?command=getAllRoutes"),
    TASK("/task.jsp"),
    REDIRECT_TO_ACCEPTED_OFFERS_BY_USER_ID("/controller?command=getAcceptedOffersByUserId");

    String page;

    Page(String page) {
        this.page = page;
    }

    public String page() {
        return page;
    }

 }
