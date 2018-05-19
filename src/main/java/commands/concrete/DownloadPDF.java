package commands.concrete;

import commands.Command;
import controllers.Page;
import entity.AcceptedOffer;
import entity.User;
import fileWriter.PDFWriter;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command to generate PDF file and redirect to download servlet.
 *
 * @author D. Kuliha
 */
public class DownloadPDF implements Command {

    private static final Logger LOG = Logger.getLogger(DownloadPDF.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        //obtain data to save to file
        List<AcceptedOffer> acceptedOffers = (List<AcceptedOffer>) request.getSession().getAttribute("acceptedOffers");
        User user = (User)request.getSession().getAttribute("user");

        //creating file name and path
        String relativePath = "/WEB-INF/reports/report_" + user.getLogin()+ ".pdf";
        String filePath = request.getServletContext().getRealPath(relativePath);
        LOG.trace("file Path: " + filePath);

        //creating a file
        PDFWriter.createFile(acceptedOffers, user.getRole(), filePath);
        request.setAttribute("downloadFile", filePath);

        LOG.debug("Command finished");
        return Page.REDIRECT_TO_DOWNLOAD_SERVLET.page();
    }


    //////////////////////////
    //Singleton
    //////////////////////////
    private DownloadPDF() {
    }
    private static DownloadPDF instance;
    public static synchronized DownloadPDF getInstance() {
        if (instance == null) instance = new DownloadPDF();
        return instance;
    }
}
