package translator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LanguageTranslator implements Translator {

    private Dictionary dictionary;

    public LanguageTranslator(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public String translate(String expression) {
        List<String> translatedWords = new ArrayList<>();
        List<String> wordList = Arrays.asList(expression.split("\\s+"));

        wordList.forEach(w -> {
            Optional<String> translatedWord = Optional
                    .ofNullable(dictionary.getContent().get(w.toLowerCase()));
            translatedWords.add(translatedWord
                    .orElse(w + "(" + dictionary.getLanguage() + ")"));
        });

        String translatedExpr = translatedWords
                .stream()
                .collect(Collectors.joining(" "));
        return capitalize(translatedExpr);
    }

    private static String capitalize(String expr) {
        if (expr != null && expr.length() != 0) {
            char[] chars = expr.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return new String(chars);
        } else {
            return expr;
        }
    }
}
