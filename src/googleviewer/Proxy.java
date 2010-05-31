/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleviewer;

/**
 *
 * @author luis
 */
public class Proxy {

    private String proxy;
    private Integer porta;

    public Integer getPorta() {
        return porta;
    }

    public String getProxy() {
        return proxy;
    }

    public Proxy(String proxy, Integer porta) {
        this.proxy = proxy;
        this.porta = porta;
    }
}
