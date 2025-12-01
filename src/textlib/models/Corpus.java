package textlib.models;

import java.util.ArrayList;
import java.util.Set;

public class Corpus {

    protected ArrayList<Document> documents;
    protected Set<String> vocabulary = null;

    public Corpus() {
        this.documents = new ArrayList<>();
    }

    public Document get(int index) {
        return documents.get(index);
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public Set<String> getVocabulary() {
        if (this.hasVocabulary())
            return this.vocabulary;

        System.err.println("No vocabulary yet");
        return null;
    }

    public void add(Document newDocument) {
        documents.add(newDocument);
    }

    public void setVocabulary(Set<String> vocabulary) {
        if (!this.hasVocabulary())
            this.vocabulary = vocabulary;
    }

    public static Corpus combine(Corpus c1, Corpus c2) {
        Corpus merged = new Corpus();

        for (Corpus corpus : new Corpus[] { c1, c2 }) {
            for (Document doc : corpus.getDocuments()) {
                merged.add(doc);
            }
        }

        return merged;
    }

    public int size() {
        return documents.size();
    }

    public boolean hasVocabulary() {
        return this.vocabulary != null;
    }

    @Override
    public String toString() {
        return String.format("Corpus with %d files", this.size());
    }

}
