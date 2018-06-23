package bean.converter;

import boundary.CharactersServiceRemote;
import model.Category;
import util.EJBUtility;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.naming.NamingException;

@FacesConverter(forClass = Category.class)
@Named("categoryConverter")
public class CategoryConverter implements Converter {

    private CharactersServiceRemote charactersServiceRemote;

    public CategoryConverter() throws NamingException {
        charactersServiceRemote = EJBUtility.lookupCharactersService();
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            return charactersServiceRemote.getCategoryByIdCategory(Integer.valueOf(submittedValue));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid Category ID", submittedValue)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Category) {
            return String.valueOf(((Category) modelValue).getIdCategory());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is not a valid Category", modelValue)));
        }
    }
}
