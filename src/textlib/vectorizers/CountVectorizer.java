package textlib.vectorizers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import textlib.models.Document;

public class CountVectorizer extends BaseVectorizer {

    protected void vectorizeDocument(Document document, Set<String> vocabulary) {
        Map<String, Double> vectorized = new HashMap<>();

        for (String word : vocabulary) {
            vectorized.put(word, 0.0);
        }

        String text = document.isCleaned() ? document.getCleaned() : document.getRaw();

        for (String word : text.split("\\s+")) {
            if (vectorized.containsKey(word)) {
                vectorized.put(word, vectorized.get(word) + 1.0);
            }
        }

        document.setVectorized(vectorized);
    }

}