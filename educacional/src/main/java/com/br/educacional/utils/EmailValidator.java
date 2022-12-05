package com.br.educacional.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("EmailValidator")
public class EmailValidator implements Validator<Object> {

    /*
     * E-Mail válido: Palavras que comecem de a ate z maiúsculo ou minusculo,
     * de 0 a 9, de a ate z e alguns caracteres especiais como . _ e -
     * Aceita um único @ e no fim tem que ter no mínimo 1 caracter, podendo ser
     * número ou letra.
     */
    private static final String EMAIL_PATTERN = "[a-zA-Z0-9]+[a-zA-Z0-9_.-]+@{1}[a-zA-Z0-9_.-]*\\.+[a-zA-Z0-9]{1,}";
    private final Pattern pattern;
    private Matcher matcher;

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!value.toString().trim().isEmpty()) {
            String email = value.toString();
            String[] listaEmails = null;

            if (email.split(";").length > 1) {
                listaEmails = email.split(";");
            } else if (email.split(",").length > 1) {
                listaEmails = email.split(",");
            }

            if (listaEmails != null) { // Possui email separados por ; ou ,
                for (int i = 0; i < listaEmails.length; i++) {
                    matcher = pattern.matcher(listaEmails[i].trim());
                    if (!matcher.matches()) {
                        FacesMessage msg = new FacesMessage("E-mail inválido");
                        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                        throw new ValidatorException(msg);
                    }
                }
            } else { // Email único
                matcher = pattern.matcher(email);
                if (!matcher.matches()) {
                    FacesMessage msg = new FacesMessage("E-mail inválido");
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(msg);
                }
            }
        }
    }
}
