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
 * Test login servlet
 *
 * @author D. Kuliha
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DAOFactory.class)
public class LoginServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher rd;
    @Mock
    UserDAOImpl userDao;
    @Mock
    User user;



    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(DAOFactory.class);
        when(DAOFactory.getUserDao(DataBase.MYSQL)).thenReturn(userDao);
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/jsp/error/errorPage.jsp")).thenReturn(rd);
        when(userDao.getUser("test")).thenReturn(user);
        when(user.getRole()).thenReturn("admin");
    }

    @Test
    public void loginTest() throws ServletException, IOException {

        when(userDao.getPasswordByLogin("test")).thenReturn("A94A8FE5CCB19BA61C4C0873D391E987982FBBD3");

        new LoginServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
        verify(session).setAttribute("user", user);
        verify(session).setAttribute("startPage", "/jsp/startPages/adminPage.jsp");
        verify(response).sendRedirect("/jsp/welcome.jsp");
    }

    @Test
    public void loginFailedTest() throws ServletException, IOException {

        when(userDao.getPasswordByLogin("test")).thenReturn(anyString());

        new LoginServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
        verify(request).setAttribute("errorMessage", "Incorrect login or password");
        verify(rd).forward(request, response);
    }

}