package textlib.cleaners;

import textlib.models.Corpus;
import textlib.models.Document;

public interface Cleaner {
    
    public abstract void cleanDocument(Document document);

    public abstract void cleanCorpus(Corpus corpus);

}