package translator;

import java.util.Map;

public class Dictionary {

    private Integer id;
    private Map<String, String> content;
    private String language;

    public Dictionary() {

    }

    public Dictionary(Integer id, Map<String, String> content, String language) {
        this.id = id;
        this.content = content;
        this.language = language;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
