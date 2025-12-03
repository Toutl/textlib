package textlib.vectorizers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import textlib.models.Corpus;
import textlib.models.Document;

public class TfidfVectorizer extends BaseVectorizer {

    private Map<String, Double> idf;

    @Override
    protected void prepareVectorizer(Corpus corpus) {
        super.prepareVectorizer(corpus);
        this.computeIdf(corpus);
    }

    protected void computeIdf(Corpus corpus) {
        idf = new HashMap<>();
        Double numberDocuments = (double) corpus.size();

        for (String word : corpus.getVocabulary()) {
            Double documentFrequency = 0.0;

            for (Document document : corpus.getDocuments()) {
                if (document.getUniqueTerms().contains(word))
                    documentFrequency++;
            }

            double idfValue = Math.log(numberDocuments / (documentFrequency + 1.0)) + 1.0;
            idf.put(word, idfValue);
        }
    }

    protected Map<String, Double> computeTf(Document document, Set<String> vocabulary) {
        Map<String, Double> tf = new HashMap<>();

        String text = document.isCleaned() ? document.getCleaned() : document.getRaw();
        String[] words = text.split("\\s+");
        Double numberWords = (double) words.length;

        if (numberWords == 0.0) {
            for (String word : vocabulary)
                tf.put(word, 0.0);
            return tf;
        }

        Map<String, Double> rawCounts = new HashMap<>();
        for (String word : words) {
            rawCounts.put(word, rawCounts.getOrDefault(word, 0.0) + 1.0);
        }

        for (String word : vocabulary) {
            tf.put(word, rawCounts.getOrDefault(word, 0.0) / numberWords);
        }

        return tf;
    }

    protected void vectorizeDocument(Document document, Set<String> vocabulary) {
        Map<String, Double> vectorized = new HashMap<>();

        Map<String, Double> tf = this.computeTf(document, vocabulary);

        for (String word : vocabulary) {
            vectorized.put(word, tf.get(word) * idf.getOrDefault(word, 0.0));
        }

        document.setVectorized(vectorized);
    }

}