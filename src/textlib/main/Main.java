package textlib.main;

import textlib.docs.Corpus;
import textlib.readers.CorpusBuilder;
import textlib.readers.TxtCorpusBuilder;

public class Main {

    public static void main(String[] args) {

        String filesDirectory = "/home/toutl/icd/poo/project/files";
        String[] fileNames = { "pg11.txt", "pg1342.txt", "pg2701.txt", "pg8492.txt", "pg84.txt" };

        CorpusBuilder txtBuilder = new TxtCorpusBuilder();

        Corpus corpus1 = txtBuilder.readFromDirectory(filesDirectory);
        Corpus corpus2 = txtBuilder.readFromList(fileNames);

    }

}
