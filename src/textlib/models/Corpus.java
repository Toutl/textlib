package textlib.models;

import java.util.ArrayList;

public class Corpus {

    protected ArrayList<Document> documents;

    public Corpus() {
        this.documents = new ArrayList<>();
    }

    public Document get(int index) {
        return documents.get(index);
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void add(Document newDocument) {
        documents.add(newDocument);
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

    @Override
    public String toString() {
        return String.format("Corpus with %d files", this.size());
    }

}
