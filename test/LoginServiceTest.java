/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import googleviewer.menuItems.MenuItemGmail;
import googleviewer.services.LoginService;
import java.awt.TrayIcon;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luis
 */
public class LoginServiceTest {

    public LoginServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

  
    @Test
    public void naoDeveriaRealizarLogin() {
        final LoginService instance = LoginService.getInstance();
        instance.doLogout();
        instance.doLogin("teste", "teste");
        boolean connected = instance.isConnected();
        assertFalse(connected);


    }
}
