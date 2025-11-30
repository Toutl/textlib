package textlib.vectorizers;

import textlib.models.Corpus;
import textlib.models.Document;

public interface Vectorizer {

    public abstract void vectorizeDocument(Document document);

    public abstract void vectorizeCorpus(Corpus corpus);

}
