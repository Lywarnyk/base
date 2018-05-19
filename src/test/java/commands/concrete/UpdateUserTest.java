package commands.concrete;

import DB.DAO.DAOFactory;
import DB.DataBase;
import DB.MySQL.DAOImpl.UserDAOImpl;
import entity.User;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Mock testing of command update user
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class UpdateUserTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    UserDAOImpl userDAO;
    User user = new User();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getUserDao(DataBase.MYSQL)).thenReturn(userDAO);
    }

    @Test
    public void executeTest() throws Exception {
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");
        when(request.getParameter("firstName")).thenReturn("test");
        when(request.getParameter("lastName")).thenReturn("test");
        when(request.getParameter("role")).thenReturn("test");
        when(request.getParameter("userID")).thenReturn("0");
        user.setId(0);
        user.setLogin("test");
        user.setPassword(Password.hash("test"));
        user.setFirstName("test");
        user.setLastName("test");
        user.setRole("test");
        when(userDAO.containsLogin("test")).thenReturn(false);

        UpdateUser.getInstance().execute(request, response);

        verify(userDAO).updateUser(user);
    }

}