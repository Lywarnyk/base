package controllers;

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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Test for main servlet front controller
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
public class FrontControllerTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher rd;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        when(request.getRequestDispatcher(anyString())).thenReturn(rd);

    }

    @Test
    public void exitDoGetTest() throws ServletException, IOException {

        when(request.getSession(false)).thenReturn(session);
        when(request.getParameter("command")).thenReturn("exit");
        when(request.getRequestDispatcher(anyString())).thenReturn(rd);
        when(request.getRequestDispatcher(anyString()));

        new FrontController().doGet(request, response);

        verify(session, atLeast(1)).invalidate();
        verify(rd, atLeast(1)).forward(request, response);
    }

    @PrepareForTest(DAOFactory.class)
    @Test
    public void deleteUserDoPostTest() throws ServletException, IOException {
        User user = new User();
        user.setId(0);
        UserDAOImpl userDao = mock(UserDAOImpl.class);
        PowerMockito.mockStatic(DAOFactory.class);

        when(DAOFactory.getUserDao(DataBase.MYSQL)).thenReturn(userDao);
        when(request.getSession(false)).thenReturn(session);
        when(request.getParameter("command")).thenReturn("deleteUserById");
        when(request.getParameter("userID")).thenReturn("0");

        new FrontController().doPost(request, response);

        verify(userDao, atLeast(1)).deleteUsers(user);
        verify(response).sendRedirect(anyString());
    }

}