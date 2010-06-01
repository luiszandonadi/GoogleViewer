/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleviewer.services;

import com.google.gdata.client.calendar.CalendarService;
import com.googlecode.gmail4j.GmailClient;
import com.googlecode.gmail4j.http.HttpGmailConnection;
import com.googlecode.gmail4j.rss.RssGmailClient;
import com.googlecode.gmail4j.util.LoginDialog;
import googleviewer.Proxy;
import java.util.logging.Level;

import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class ConnectionService {

    private static ConnectionService loginService;
    private CalendarService calendarService;
    private GmailClient client;
    private String currentPassword, currentLogin;

    public CalendarService getCalendarService() {
        return calendarService;
    }

    public GmailClient getClient() {
        return client;
    }

    public String getEmail() {
        if (!currentLogin.contains("@")) {
            currentLogin += "@gmail.com";
        }
        return currentLogin;

    }

    public static ConnectionService getInstance() {
        if (loginService == null) {
            loginService = new ConnectionService();
        }
        return loginService;
    }

    public boolean doLogin(String login, String senha, Proxy proxy) {

        try {
            currentLogin = login;
            currentPassword = senha;

            calendarService = new CalendarService("calendar-service-google-viewer");
            calendarService.setUserCredentials(currentLogin, currentPassword);
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





            return true;
        } catch (Exception ex) {
            Logger.getLogger(ConnectionService.class.getName()).log(Level.SEVERE, null, ex);
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
