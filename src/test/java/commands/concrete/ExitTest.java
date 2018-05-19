package commands.concrete;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

/**
 * Test for command exit
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
public class ExitTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executeTest() throws Exception {
        when(request.getSession(false)).thenReturn(session);

        Exit.getInstance().execute(request, response);

        verify(session, atLeast(1)).invalidate();
    }

    @Test()
    public void executeFailTest() throws Exception {
        when(request.getSession(false)).thenReturn(null);

        Exit.getInstance().execute(request, response);

        verify(session, never()).invalidate();
    }


}