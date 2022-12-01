package com.br.educacional.utils;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Piaia
 */
public class JsfUtil implements Serializable {

    private static PrimeFaces pf = PrimeFaces.current();

    public static void info(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }

    public static void warn(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null));
    }

    public static void error(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    public static String getPaginaAtual() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String uri = request.getRequestURI().replace("/educacional", "");
        if (uri.isEmpty() || uri.equals("/")) {
            return "index";
        }
        return uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf(".xhtml"));
    }

    public static boolean isPage(String pagina) {
        return getPaginaAtual().equals(pagina);
    }

    public static void pfShowDialog(String widgetVar) {
        pf.executeScript("PF('" + widgetVar + "').show();");
    }

    public static void pfHideDialog(String widgetVar) {
        pf.executeScript("PF('" + widgetVar + "').hide();");
    }

}
