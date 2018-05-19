package commands.concrete;

import entity.AcceptedOffer;
import entity.User;
import fileWriter.PDFWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Mock testing of command downloading PDF file
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PDFWriter.class)
public class DownloadPDFTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ArrayList<AcceptedOffer> acceptedOffers;
    @Mock
    HttpSession session;
    @Mock
    ServletContext servletContext;
    User user = new User();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(PDFWriter.class);
        user.setLogin("test");
        user.setRole("test");
    }

    @Test
    public void execute() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getSession().getAttribute("acceptedOffers")).thenReturn(acceptedOffers);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRealPath("/WEB-INF/reports/report_test.pdf")).thenReturn("testString");

        DownloadPDF.getInstance().execute(request,response);

        PowerMockito.verifyStatic(PDFWriter.class, Mockito.times(1));
        PDFWriter.createFile(acceptedOffers,"test","testString" );

        verify(request).setAttribute("downloadFile", "testString");
    }

}