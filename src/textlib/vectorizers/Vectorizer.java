package textlib.vectorizers;

import textlib.models.Corpus;

public interface Vectorizer {

    public abstract void vectorizeCorpus(Corpus corpus);

}
