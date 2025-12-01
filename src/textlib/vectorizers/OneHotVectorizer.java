package textlib.vectorizers;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import textlib.models.Corpus;
import textlib.models.Document;

public class OneHotVectorizer implements Vectorizer {

    public void vectorizeCorpus(Corpus corpus) {

        if (!corpus.hasVocabulary())
            this.constructVocabulary(corpus);

        Set<String> vocabulary = corpus.getVocabulary();

        for (Document doc : corpus.getDocuments()) {
            this.vectorizeDocument(doc, vocabulary);
        }
    }

    protected void vectorizeDocument(Document document, Set<String> vocabulary) {
        Map<String, Double> vectorized = new HashMap<>();
        String text = document.isCleaned() ? document.getCleaned() : document.getRaw();

        for (String word : vocabulary) {
            vectorized.put(word, text.contains(word) ? 1.0 : 0.0);
        }

        document.setVectorized(vectorized);
    }

    protected void constructVocabulary(Corpus corpus) {
        Set<String> vocabulary = new HashSet<>();

        for (Document doc : corpus.getDocuments()) {
            String text = doc.isCleaned() ? doc.getCleaned() : doc.getRaw();

            Collections.addAll(vocabulary, text.split("\\s+"));
        }

        corpus.setVocabulary(vocabulary);
    }

}
