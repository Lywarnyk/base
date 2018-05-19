package commands;


import commands.concrete.*;
import exception.MainException;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Container for all commands
 *
 * @author D. Kuliha
 */
public enum Commands {
    //User
    UPDATE_USER("updateUser", UpdateUser.getInstance()),
    CREATE_USER("createNewUser", CreateNewUser.getInstance()),
    GET_ALL_USERS("getAllUsers", GetAllUsers.getInstance()),
    DELETE_USER_BY_ID("deleteUserById", DeleteUserById.getInstance()),
    EXIT("exit", Exit.getInstance()),

    //Car
    GET_ALL_CARS("getAllCars", GetAllCars.getInstance()),
    CREATE_CAR("createCar", CreateNewCar.getInstance()),
    UPDATE_CAR("updateCar", UpdateCar.getInstance()),
    DELETE_CAR_BY_ID("deleteCarById", DeleteCarById.getInstance()),
    CHOOSE_CAR("chooseCar", ChooseCar.getInstance()),
    ROUTES_BY_CAR_ID("getRoutesByCarId", GetRoutesByCarId.getInstance()),

    //Route
    GET_ALL_ROUTES("getAllRoutes", GetAllRoutes.getInstance()),
    GET_ALL_FREE_ROUTES("getAllFreeRoutes", GetAllAvailableRoutes.getInstance()),
    DELETE_ROUTE("deleteRoute", DeleteRoute.getInstance()),
    CREATE_NEW_ROUTE("createNewRoute", CreateNewRoute.getInstance()),
    UPDATE_ROUTE("updateRoute", UpdateRoute.getInstance()),
    SORT_ROUTES_BY_ID("sortRoutesById", SortRoutesById.getInstance()),
    SORT_ROUTES_BY_CREATION_DATE("sortRoutesByCreationDate", SortRoutesByCreationDate.getInstance()),
    SORT_ROUTES_BY_DEPARTURE_DATE("sortRoutesByDepartureDate", SortRoutesByDepartureDate.getInstance()),
    SORT_ROUTES_BY_STATUS("sortRoutesByStatus", SortRoutesByStatus.getInstance()),
    UPLOAD_FILE_OF_ROUTES("uploadFile", UploadFile.getInstance()),

    //Offer
    GET_ALL_OFFERS("getAllOffers", GetAllOffers.getInstance()),
    GET_OFFERS_BY_USER_ID("getOffersByUserId", GetOffersByUserId.getInstance()),
    ACCEPT_OFFER("acceptOffer", AcceptOffer.getInstance()),
    DELETE_OFFER("deleteOffer", DeleteOffer.getInstance()),
    CREATE_OFFER("createNewOfferByRouteId", CreateOffer.getInstance()),

    //Accepted Offer
    GET_ALL_ACCEPTED_OFFERS("getAllAcceptedOffers", GetAllAcceptedOffers.getInstance()),
    DOWNLOAD_ACCEPTED_OFFERS_PDF("downloadPDF", DownloadPDF.getInstance()),
    COMPLETE_ACCEPTED_OFFER("completeAcceptedOffer", CompleteAcceptedOffer.getInstance()),
    GET_ACCEPTED_OFFERS_BY_USER_ID("getAcceptedOffersByUserId", GetAcceptedOffersByUserId.getInstance()),
    DELETE_ACCEPTED_OFFER("deleteAcceptedOffer", DeleteAcceptedOffer.getInstance());


    private static final Logger LOG = Logger.getLogger(Commands.class);

    private final String commandValue;
    private final Command command;

    Commands(String commandValue, Command command) {
        this.commandValue = commandValue;
        this.command = command;

    }

    /**
     * Search for requested command by commandValue.
     *
     * @param commandValue String value of requested command.
     * @return instance of requested command
     *
     * throws MainException if command wasn't found
     */
    public static Command getCommand(String commandValue) {
        Command command = Arrays.stream(values()).filter(c -> c.commandValue.equals(commandValue)).findFirst().get().command;
        if (command == null) {
            LOG.error("Wrong command " + commandValue);
            throw new MainException("Wrong command");
        }
        return command;
    }
    public String value(){
        return commandValue;
    }
}
