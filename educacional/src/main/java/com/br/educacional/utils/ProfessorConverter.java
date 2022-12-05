package com.br.educacional.utils;

import com.br.educacional.domains.Professor;
import com.br.educacional.services.ProfessorService;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Piaia
 */
@Named
@FacesConverter(value = "professorConverter", managed = true)
public class ProfessorConverter implements Converter<Professor> {

    @EJB
    private ProfessorService professorService;

    @Override
    public Professor getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return professorService.getProfessorPeloCodigo(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é um professor válido."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Professor value) {
        if (value != null) {
            return String.valueOf(value.getId());
        } else {
            return null;
        }
    }
}
