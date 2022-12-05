package com.br.educacional.utils;

import com.br.educacional.domains.Disciplina;
import com.br.educacional.services.DisciplinaService;
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
@FacesConverter(value = "disciplinaConverter", managed = true)
public class DisciplinaConverter implements Converter<Disciplina> {

    @EJB
    private DisciplinaService disciplinaService;

    @Override
    public Disciplina getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return disciplinaService.getDisciplinaPorCodigo(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é uma disciplina válida."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Disciplina value) {
        if (value != null) {
            return String.valueOf(value.getId());
        } else {
            return null;
        }
    }
}
