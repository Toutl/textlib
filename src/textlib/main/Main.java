package textlib.main;

import java.util.ArrayList;

import textlib.builders.TxtBuilder;
import textlib.cleaners.SimpleCleaner;
import textlib.models.Corpus;
import textlib.models.Document;
import textlib.vectorizers.OneHotVectorizer;
import textlib.vectorizers.Vectorizer;

public class Main {

    public static void main(String[] args) {

        // path
        String filesDirectory = "./files/sample";

        // build the corpus
        TxtBuilder builder = new TxtBuilder();
        Corpus myCorpus = builder.readFromDirectory(filesDirectory);

        // clean the corpus
        SimpleCleaner cleaner = new SimpleCleaner();
        cleaner.cleanCorpus(myCorpus);

        // vectorize the corpus
        Vectorizer vectorizer = new OneHotVectorizer();
        vectorizer.vectorizeCorpus(myCorpus);

        // some checks
        ArrayList<String> docNames = new ArrayList<>();
        for (Document d : myCorpus.getDocuments()) {
            docNames.add(d.getFileName());
        }
        System.out.printf("\n%s: %s\n\n", myCorpus, docNames);

        System.out.println(myCorpus.get(0));

    }

}
