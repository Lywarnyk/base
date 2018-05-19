package commands;

import DB.DataBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Interface for commands
 * @author D. Kuliha
 */

public interface Command {

    /**
     * Choice of DB to use
     */
    DataBase dataBase = DataBase.MYSQL;

    /**
     * Execute command, update request and response if necessary
     * @param request HttpRequest
     * @param response HttpResponse
     * @return String page to see result after executing
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
