package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.UserDAOImpl;
import entity.User;
import exception.MainException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import security.Password;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


/**
 * Mock testing of command create new user
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class CreateNewUserTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    UserDAOImpl userDAO;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getUserDao(DataBase.MYSQL)).thenReturn(userDAO);
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");

    }

    @Test
    public void executeTest() throws Exception {
        User user = new User();
        user.setLogin("test");
        user.setPassword(Password.hash("test"));
        when(userDAO.containsLogin("test")).thenReturn(false);

        CreateNewUser.getInstance().execute(request, response);

        verify(userDAO).createUser(user);
    }

    @Test(expected = MainException.class)
    public void executeFailTest() throws Exception {
        when(userDAO.containsLogin("test")).thenReturn(true);

        CreateNewUser.getInstance().execute(request, response);

        verify(userDAO, never()).createUser(any());
    }

}