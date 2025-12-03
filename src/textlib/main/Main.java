package textlib.main;

import java.util.ArrayList;

import textlib.builders.TxtBuilder;
import textlib.cleaners.SimpleCleaner;
import textlib.models.Corpus;
import textlib.models.Document;
import textlib.vectorizers.BaseVectorizer;
import textlib.vectorizers.CountVectorizer;
import textlib.vectorizers.OneHotVectorizer;
import textlib.vectorizers.TfidfVectorizer;

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
        BaseVectorizer[] vectorizers = {
                new OneHotVectorizer(),
                new CountVectorizer(),
                new TfidfVectorizer(),
        };
        vectorizers[2].vectorizeCorpus(myCorpus);

        // some checks
        ArrayList<String> docNames = new ArrayList<>();
        for (Document d : myCorpus.getDocuments()) {
            docNames.add(d.getFileName());
        }
        System.out.printf("%s: %s\n", myCorpus, docNames);

        // System.out.println(myCorpus.get(0));

        // System.out.println(myCorpus.get(0).getRaw());
        // System.out.println("\n");

        System.out.println(myCorpus.get(0).getCleaned());
        System.out.println("");
        System.out.println(myCorpus.get(1).getCleaned());
        System.out.println("\n");

        System.out.println(myCorpus.get(0).getVectorized());
        System.out.println("");
        System.out.println(myCorpus.get(1).getVectorized());

    }

}
