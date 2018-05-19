package tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Date;

/**
 * Custom tag for print signed in user data
 *
 * @author D. Kuliha
 */
public class CurrentDateTag  extends TagSupport{

    private static final long serialVersionUID = -4855470011757968855L;

    private static final Logger LOG = Logger.getLogger(CurrentDateTag.class);

    public int doStartTag() {
        long currentTime = System.currentTimeMillis();
        Date currentDate = new Date(currentTime);
        JspWriter out = pageContext.getOut();
        try {
            out.write(String.valueOf(currentDate));
        } catch (IOException e) {
            LOG.error("Cannot write Signed in info", e);
        }

        return SKIP_BODY;
    }

}
