package controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Testing download file servlet
 *
 * @author D. Kuliha
 */
public class DownloadFileServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ServletOutputStream outStream;
    @Mock
    ServletContext context;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(FileInputStream.class);
    }

    @Test
    public void doGet() throws Exception {

        when(request.getAttribute("downloadFile")).thenReturn("D:/EPAM/Practice/my projects/FP/AutobaseMaven/Autobase/src/test/java/testFilePath.pdf");
        when(response.getOutputStream()).thenReturn(outStream);
        when(request.getServletContext()).thenReturn(context);

        new DownloadFileServlet().doGet(request, response);

        verify(response).setContentType(any());
        verify(response).setContentLength(anyInt());
        verify(outStream).close();
    }

}