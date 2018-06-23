package app;

import dao.CategoryDAO;
import dao.ElementDAO;
import dto.CategoryDTO;
import dto.ElementDTO;
import model.Category;
import model.Element;
import org.jboss.ejb3.annotation.SecurityDomain;
import translator.Dictionary;
import translator.DictionaryRepository;
import translator.LanguageTranslator;
import translator.Translator;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SecurityDomain("soaEJBApplicationDomain")
@PermitAll
@Stateless
@Path("/editor")
public class EditorManager {

    @Inject
    private CategoryDAO categoryDAO;

    @Inject
    private ElementDAO elementDAO;

    @Inject
    private DictionaryRepository dictionaryRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categories")
    public Response getAllCategories() {
        List<CategoryDTO> categoryDTOS = categoryDAO.findAll()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
        return Response
                .status(Response.Status.OK)
                .entity(categoryDTOS)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categories/translator/{idTranslator}")
    public Response getAllCategoriesTranslated(@PathParam("idTranslator") Integer idTranslator) {
        Optional<Dictionary> dictionary = dictionaryRepository.getDictionary(idTranslator);
        if(dictionary.isPresent()) {
            Translator translator = new LanguageTranslator(dictionary.get());
            List<CategoryDTO> categoryDTOS = categoryDAO.findAll()
                    .stream()
                    .map(category -> new CategoryDTO(category, translator))
                    .collect(Collectors.toList());
            return Response
                    .status(Response.Status.OK)
                    .entity(categoryDTOS)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categories/{idCategory}")
    public Response getCategoryById(@PathParam("idCategory") Integer idCategory) {
        Optional<Category> category = categoryDAO.findById(idCategory);
        if (category.isPresent()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(new CategoryDTO(category.get()))
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/categories/{idCategory}/translator/{idDictionary}")
    public Response getCategoryByIdTranslated(
            @PathParam("idCategory") Integer idCategory,
            @PathParam("idDictionary") Integer idDictionary) {
        Optional<Category> category = categoryDAO.findById(idCategory);
        Optional<Dictionary> dictionary = dictionaryRepository.getDictionary(idDictionary);
        if(category.isPresent() && dictionary.isPresent()) {
            Translator translator = new LanguageTranslator(dictionary.get());
            return Response
                    .status(Response.Status.OK)
                    .entity(new CategoryDTO(category.get(), translator))
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .header(
                            "Not Found",
                            "category " + idCategory + " or dictionary " + idDictionary
                    )
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/categories/{idCategory}/elements")
    public Response addElement(@PathParam("idCategory") Integer idCategory,
                               ElementDTO elementDTO) {
        Optional<Category> category = categoryDAO.findById(idCategory);
        if (category.isPresent()) {
            Element element = new Element(elementDTO, category.get());
            elementDAO.add(element);
            return Response
                    .ok()
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .header("Not Found", "category by id " + idCategory)
                    .build();
        }
    }
}
