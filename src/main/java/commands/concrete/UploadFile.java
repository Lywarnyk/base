package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DAO.RouteDAO;
import commands.Command;
import controllers.Page;
import entity.Route;
import exception.MainException;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Uploading file of routes, and creating transaction
 * to add list of routes to DataBase.
 *
 * @author D. Kuliha
 */

public class UploadFile implements Command {

    private static final Logger LOG = Logger.getLogger(UploadFile.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        RouteDAO routeDAO = DAOFactory.getRouteDao(dataBase);

        try {
            //obtain a file from request
            Part filePart = request.getPart("file");

            //obtain a list of routes from file
            List<Route> routes = readTableFromFile(filePart);

            for (Route route : routes)
            LOG.debug(route);

            //creating routes using transaction
            routeDAO.createRoutes(routes);

        } catch (IOException | ServletException e) {
            LOG.error("Cannot upload the file", e);
            throw new MainException("Cannot upload the file", e);
        }

        LOG.debug("Command starts");
        return Page.REDIRECT_TO_ROUTES.page();
    }

    private List<Route> readTableFromFile(Part filePart) throws IOException {
        List<Route> routes = new ArrayList<>();
        InputStream fileContent = filePart.getInputStream();
        Workbook wb = new XSSFWorkbook(fileContent);
        Sheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {
            Route route = getRouteFromRow(row);
            routes.add(route);
        }
        return routes;
    }

    private Route getRouteFromRow(Row row) {
        Route route = new Route();

        //obtain creation date
        long currTime = System.currentTimeMillis();
        Date creationDate = new Date(currTime);
        LOG.debug(creationDate + " creation date");
        Date currentDate = Date.valueOf(creationDate.toString());
        Iterator<Cell> iterator = row.cellIterator();

        //obtain departure date
        Date depDate = new Date(iterator.next().getDateCellValue().getTime());
        if (depDate.before(creationDate) && (!depDate.equals(currentDate))) {
            LOG.error(creationDate + " creation date");
            LOG.error(depDate + " departure date");
            throw new MainException("Departure date cannot be before today.");
        }

        //obtain arrival date
        Date arrDate =  new Date(iterator.next().getDateCellValue().getTime());
        if (depDate.after(arrDate) && (!depDate.equals(arrDate))) {
            LOG.error(depDate + " departure date");
            LOG.error(arrDate + " arrival date");
            throw new MainException("Departure date cannot be after arrival date.");
        }

        //setting route
        route.setCreationDate(creationDate);
        route.setDepartureDate(depDate);
        route.setArrivalDate(arrDate);
        route.setDeparturePoint(iterator.next().getStringCellValue());
        route.setDestinationPoint(iterator.next().getStringCellValue());
        route.setDescription(iterator.next().getStringCellValue());
        route.setStatus("Open");
        return route;

    }

    ///////////////////////////////
    //Singleton
    ///////////////////////////////
    private UploadFile() {
    }

    private static UploadFile instance;

    public static synchronized UploadFile getInstance() {
        if (instance == null) instance = new UploadFile();
        return instance;
    }
}
