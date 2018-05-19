package mapping;

import entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static commands.Commands.*;


/**
 * Mapping commands by roles
 *
 * @author D. Kuliha
 */
public class CommandMapping {
    private static Map<String, List<String>> commandsMapping = new HashMap<>();

    static {
        List<String> driver = new ArrayList<>();
        List<String> dispatcher = new ArrayList<>();
        List<String> admin = new ArrayList<>();

        //commands for drivers
        driver.add(EXIT.value());
        driver.add(DOWNLOAD_ACCEPTED_OFFERS_PDF.value());
        driver.add(GET_ALL_FREE_ROUTES.value());
        driver.add(COMPLETE_ACCEPTED_OFFER.value());
        driver.add(SORT_ROUTES_BY_CREATION_DATE.value());
        driver.add(SORT_ROUTES_BY_ID.value());
        driver.add(SORT_ROUTES_BY_DEPARTURE_DATE.value());
        driver.add(SORT_ROUTES_BY_STATUS.value());
        driver.add(GET_OFFERS_BY_USER_ID.value());
        driver.add(CREATE_OFFER.value());
        driver.add(GET_ACCEPTED_OFFERS_BY_USER_ID.value());

        //commands for dispatchers
        dispatcher.add(EXIT.value());
        dispatcher.add(CREATE_NEW_ROUTE.value());
        dispatcher.add(CHOOSE_CAR.value());
        dispatcher.add(SORT_ROUTES_BY_CREATION_DATE.value());
        dispatcher.add(SORT_ROUTES_BY_ID.value());
        dispatcher.add(SORT_ROUTES_BY_DEPARTURE_DATE.value());
        dispatcher.add(SORT_ROUTES_BY_STATUS.value());
        dispatcher.add(GET_ALL_OFFERS.value());
        dispatcher.add(GET_ALL_ROUTES.value());
        dispatcher.add(ACCEPT_OFFER.value());
        dispatcher.add(GET_ALL_ACCEPTED_OFFERS.value());
        dispatcher.add(DOWNLOAD_ACCEPTED_OFFERS_PDF.value());
        dispatcher.add(UPLOAD_FILE_OF_ROUTES.value());

        //commands for admins
        admin.add(UPDATE_USER.value());
        admin.add(CREATE_USER.value());
        admin.add(GET_ALL_USERS.value());
        admin.add(DELETE_USER_BY_ID.value());
        admin.add(EXIT.value());
        admin.add(GET_ALL_CARS.value());
        admin.add(GET_ALL_ROUTES.value());
        admin.add(CREATE_CAR.value());
        admin.add(UPDATE_CAR.value());
        admin.add(DELETE_CAR_BY_ID.value());
        admin.add(CHOOSE_CAR.value());
        admin.add(DELETE_ROUTE.value());
        admin.add(CREATE_NEW_ROUTE.value());
        admin.add(UPDATE_ROUTE.value());
        admin.add(SORT_ROUTES_BY_CREATION_DATE.value());
        admin.add(SORT_ROUTES_BY_ID.value());
        admin.add(SORT_ROUTES_BY_DEPARTURE_DATE.value());
        admin.add(SORT_ROUTES_BY_STATUS.value());
        admin.add(GET_ALL_OFFERS.value());
        admin.add(ACCEPT_OFFER.value());
        admin.add(DELETE_OFFER.value());
        admin.add(GET_ALL_ACCEPTED_OFFERS.value());
        admin.add(DELETE_ACCEPTED_OFFER.value());
        admin.add(DOWNLOAD_ACCEPTED_OFFERS_PDF.value());
        admin.add(UPLOAD_FILE_OF_ROUTES.value());
        admin.add(ROUTES_BY_CAR_ID.value());

        commandsMapping.put("driver", driver);
        commandsMapping.put("dispatcher", dispatcher);
        commandsMapping.put("admin", admin);
    }


    public static boolean isCommandAllowed(User user, String command) {
        String role = user.getRole();
        return commandsMapping.get(role).contains(command);
    }
}
