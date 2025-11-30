package textlib.builders;

import textlib.models.Corpus;

public interface Builder {

    public abstract Corpus readFromDirectory(String filesDirectory);

}
