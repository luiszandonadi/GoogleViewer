/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import googleviewer.Proxy;
import googleviewer.services.ConnectionService;
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
        final ConnectionService instance = ConnectionService.getInstance();
        instance.doLogout();
        instance.doLogin("teste", "teste", null);
        boolean connected = instance.isConnected();
        assertFalse(connected);


    }
}
