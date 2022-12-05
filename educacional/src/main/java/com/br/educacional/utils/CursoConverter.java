package com.br.educacional.utils;

import com.br.educacional.domains.Curso;
import com.br.educacional.services.CursoService;
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
@FacesConverter(value = "cursoConverter", managed = true)
public class CursoConverter implements Converter<Curso> {

    @EJB
    private CursoService cursoService;

    @Override
    public Curso getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return cursoService.getCursoPeloCodigo(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de conversão", "Não é um curso válido."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Curso value) {
        if (value != null) {
            return String.valueOf(value.getId());
        } else {
            return null;
        }
    }
}
