package filters;

import controllers.Page;
import entity.User;
import mapping.CommandMapping;
import mapping.PagesMapping;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filters requests by user's role, if user doesn't
 * authorised forwards to error page
 *
 * @author D. Kuliha
 */
@WebFilter(urlPatterns = {"/controller", "/jsp/*", "/downloadFileServlet"})
public class UserRoleFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(UserRoleFilter.class);

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        LOG.debug("Filter starts");

        boolean authorized;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //obtain session
        HttpSession session = request.getSession(false);
        if (session == null) {
            forwardToErrorPage("You need to login first.", request, response);
            return;
        }

        // obtain URI and user
        String uri = request.getRequestURI();
        User user = (User) session.getAttribute("user");
        LOG.trace("user " + user);

        if (user == null) {
            forwardToErrorPage("You need to login first.", request, response);
            return;
        }

        LOG.trace("request URI: " + uri);

        //check authorisation of command or page by role
        if ("/controller".equals(uri)) {
            String command = request.getParameter("command");
            LOG.debug("command " + command);
            authorized = CommandMapping.isCommandAllowed(user, command);
        } else {
            authorized = PagesMapping.isPageAllowed(user, uri);
        }

        if (authorized) {
            chain.doFilter(req, resp);
        } else {
            forwardToErrorPage("Access denied.", request, response);
        }
        LOG.debug("Filter finished");
    }

    private void forwardToErrorPage(String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("errorMessage", message);
        ServletContext servletContext = request.getServletContext();
        request.getRequestDispatcher(servletContext.getContextPath() + Page.ERROR_PAGE.page()).forward(request, response);

    }

    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
