package com.br.educacional.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Piaia
 */
@Named
@FacesConverter(value = "telefoneConverter", managed = true)
public class TelefoneConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        return string.replace("(", "").replace(")", "").replace("-", "");
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        final String value = (String) t;
        if (value == null || value.length() > 12) {
            return null;
        }
        return "(" + value.substring(0, 3) + ")"
                + value.substring(3, 8) + "-"
                + value.substring(8, 12);
    }

}
