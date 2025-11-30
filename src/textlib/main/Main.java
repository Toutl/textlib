package textlib.main;

import java.util.ArrayList;

import textlib.cleaners.SimpleCleaner;
import textlib.builders.TxtBuilder;
import textlib.models.Corpus;
import textlib.models.Document;

public class Main {

    public static void main(String[] args) {

        // path
        String filesDirectory = "/home/toutl/icd/poo/project/files/";

        // build the corpus
        Corpus myCorpus = null;
        TxtBuilder builder = new TxtBuilder();
        myCorpus = builder.readFromDirectory(filesDirectory);

        // clean the corpus
        SimpleCleaner cleaner = new SimpleCleaner();
        cleaner.cleanCorpus(myCorpus);

        // vectorize the corpus

        // check the corpus
        ArrayList<String> docNames = new ArrayList<>();
        for (Document d : myCorpus.getDocuments())
            docNames.add(d.getFileName());
        System.out.printf("\n%s: %s\n\n", myCorpus, docNames);

        // Document tryDoc = myCorpus.get(5);
        // System.out.println(tryDoc);

        // // System.out.printf("--%s--\n", tryDoc.getRaw());
        // System.out.printf("++%s++\n", tryDoc.getCleaned());

    }

}
