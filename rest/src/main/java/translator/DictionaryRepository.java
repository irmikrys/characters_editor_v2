package translator;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class DictionaryRepository {

    private List<Dictionary> dictionaries = new ArrayList<>();

    public DictionaryRepository() {

        Map<String, String> polishContent = new HashMap<>();
        polishContent.put("wood", "las");
        polishContent.put("cave", "jaskinia");
        polishContent.put("tower", "wieza");
        polishContent.put("foreign", "zakazany/a");
        polishContent.put("black", "czarny/a");
        polishContent.put("random", "losowy/a");
        polishContent.put("dark", "mroczny/a");

        Map<String, String> germanContent = new HashMap<>();
        germanContent.put("wood", "Wald");
        germanContent.put("cave", "Felsenhoehle");
        germanContent.put("tower", "Turm");
        germanContent.put("foreign", "verboten");
        germanContent.put("black", "schwarz");
        germanContent.put("random", "schicksals");
        germanContent.put("dark", "finster");

        dictionaries.add(new Dictionary(1, polishContent, "pl_PL"));
        dictionaries.add(new Dictionary(2, germanContent, "de_DE"));

    }

    public Optional<Dictionary> getDictionary(Integer id) {
        return dictionaries
                .stream()
                .filter(dictionary -> dictionary.getId().equals(id))
                .findFirst();
    }
}
