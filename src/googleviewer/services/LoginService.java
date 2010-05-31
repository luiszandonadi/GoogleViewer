/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleviewer.services;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.util.AuthenticationException;
import com.googlecode.gmail4j.GmailClient;
import com.googlecode.gmail4j.GmailConnection;
import com.googlecode.gmail4j.auth.Credentials;
import com.googlecode.gmail4j.http.HttpGmailConnection;
import com.googlecode.gmail4j.rss.RssGmailClient;
import com.googlecode.gmail4j.util.LoginDialog;
import googleviewer.Proxy;
import java.net.Proxy.Type;
import java.util.logging.Level;

import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class LoginService {

    private static LoginService loginService;
    private CalendarService calendarService;
    private GmailClient client;
    private String currentPassword, currentLogin;

    public CalendarService getCalendarService() {
        return calendarService;
    }

    public GmailClient getClient() {
        return client;
    }

    public static LoginService getInstance() {
        if (loginService == null) {
            loginService = new LoginService();
        }
        return loginService;
    }

    public boolean doLogin(String login, String senha, Proxy proxy) {

        try {
            currentLogin = login;
            currentPassword = senha;

            HttpGmailConnection gmailConnection = new HttpGmailConnection(currentLogin, currentPassword.toCharArray());

            if (proxy != null) {
                final String proxyHost = proxy.getProxy();
                final Integer proxyPort = proxy.getPorta();

                System.setProperty("http.proxyHost", proxyHost);
                System.setProperty("http.proxyPort", proxyPort.toString());

                gmailConnection.setProxy(proxyHost, proxyPort);
                gmailConnection.setProxyCredentials(LoginDialog.getInstance().show("Enter Proxy Login"));

            }
            client = new RssGmailClient();
            client.setConnection(gmailConnection);

            calendarService = new CalendarService("calendar-service-google-viewer");

            calendarService.setUserCredentials(currentLogin, currentPassword);
            return true;
        } catch (AuthenticationException ex) {
            Logger.getLogger(LoginService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void doLogout() {
        currentLogin = null;
        currentPassword = null;
        client = null;
        calendarService = null;
    }

    public boolean isConnected() {
        if (client == null || calendarService == null) {
            return false;
        }
        return true;

    }
}
