package mapping;

import entity.User;
import filters.UserRoleFilter;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static controllers.Page.*;

/**
 * Mapping all pages by roles.
 *
 * @author D. Kuliha
 */
public class PagesMapping {

    private static final Logger LOG = Logger.getLogger(UserRoleFilter.class);

    private static Map<String, List<String>> pagesMapping = new HashMap<>();

    static {
        List<String> driver = new ArrayList<>();
        List<String> dispatcher = new ArrayList<>();
        List<String> admin = new ArrayList<>();

        //pages for drivers
        driver.add(DRIVER_PAGE.page());
        driver.add(ROUTES.page());
        driver.add(OFFERS.page());
        driver.add(ACCEPTED_OFFERS.page());
        driver.add(ERROR_PAGE.page());
        driver.add(WELCOME.page());

        //pages for dispatchers
        dispatcher.add(DISPATCHER_PAGE.page());
        dispatcher.add(ROUTES.page());
        dispatcher.add(OFFERS.page());
        dispatcher.add(ACCEPTED_OFFERS.page());
        dispatcher.add(CHOOSE_CAR.page());
        dispatcher.add(ERROR_PAGE.page());
        dispatcher.add(WELCOME.page());

        //pages for admins
        admin.add(ADMIN_PAGE.page());
        admin.add(CARS.page());
        admin.add(USERS.page());
        admin.add(ROUTES.page());
        admin.add(OFFERS.page());
        admin.add(ACCEPTED_OFFERS.page());
        admin.add(CHOOSE_CAR.page());
        admin.add(ERROR_PAGE.page());
        admin.add(WELCOME.page());

        pagesMapping.put("driver", driver);
        pagesMapping.put("dispatcher", dispatcher);
        pagesMapping.put("admin", admin);
    }


    public static boolean isPageAllowed(User user, String uri) {
        LOG.debug("isPageAllowed starts, uri:" + uri);
        String role = user.getRole();
        boolean result = pagesMapping.get(role).contains(uri);
        LOG.debug("Result: " + result);
        return result;
    }
}

