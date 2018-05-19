package tag;

import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Custom tag for print signed in user data
 *
 * @author D. Kuliha
 */
public class SignedInTag extends TagSupport {

    private static final long serialVersionUID = 7450295798386149400L;

    private static final Logger LOG = Logger.getLogger(SignedInTag.class);


    public int doStartTag() {
        LOG.debug("Tag starts");

        String localeVal = "en";
        HttpSession session = pageContext.getSession();
        if (session.getAttribute("locale") != null)
            localeVal = (String) session.getAttribute("locale");
        LOG.trace("locale " + session.getAttribute("locale"));

        Locale locale = new Locale(localeVal);
        ResourceBundle bundle = ResourceBundle.getBundle("resource", locale);
        StringBuilder result = new StringBuilder();
        User user = (User) session.getAttribute("user");
        result.append(bundle.getString("signedInMessage")).append(" ")
                .append(user.getRole()).append(" ")
                .append(user.getFirstName()).append(" ")
                .append(user.getLastName());

        JspWriter out = pageContext.getOut();
        try {
            out.write(result.toString());
        } catch (IOException e) {
            LOG.error("Cannot write Signed in info", e);
        }

        LOG.debug("Tag finished");
        return SKIP_BODY;
    }
}
