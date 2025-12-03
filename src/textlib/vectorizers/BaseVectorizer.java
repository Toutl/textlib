package textlib.vectorizers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import textlib.models.Corpus;
import textlib.models.Document;

public abstract class BaseVectorizer {

    public static void constructVocabulary(Corpus corpus) {
        Set<String> vocabulary = new HashSet<>();

        for (Document doc : corpus.getDocuments()) {
            String text = doc.isCleaned() ? doc.getCleaned() : doc.getRaw();

            Collections.addAll(vocabulary, text.split("\\s+"));
        }
        vocabulary.remove("");

        corpus.setVocabulary(vocabulary);
    }

    public void vectorizeCorpus(Corpus corpus) {
        this.prepareVectorizer(corpus);

        Set<String> vocabulary = corpus.getVocabulary();

        for (Document doc : corpus.getDocuments()) {
            this.vectorizeDocument(doc, vocabulary);
        }
    }

    protected void prepareVectorizer(Corpus corpus) {
        if (!corpus.hasVocabulary())
            BaseVectorizer.constructVocabulary(corpus);
    }

    protected abstract void vectorizeDocument(Document document, Set<String> vocabulary);

}
