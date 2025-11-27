package textlib.readers;

import textlib.docs.Corpus;

public interface CorpusBuilder {

    public abstract Corpus readFromDirectory(String filesDirectory);

    public abstract Corpus readFromList(String[] filesNames);

}
