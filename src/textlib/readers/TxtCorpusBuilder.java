package textlib.readers;

import textlib.docs.Corpus;
import textlib.docs.Document;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.util.stream.Stream;

public class TxtCorpusBuilder implements CorpusBuilder {

    public Corpus readFromDirectory(String filesDirectory) {
        Path filesPath = Paths.get(filesDirectory);

        if (!Files.isDirectory(filesPath)) {
            return null;
        }

        try (Stream<Path> pathStream = Files.walk(filesPath)) {
            pathStream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".txt"));
        } catch (IOException e) {
            System.err.println("Error accessing directory: " + e.getMessage());
        }

        Corpus result = new Corpus();

        // File folder = new File(filesDirectory);
        // File[] listOfFiles = folder.listFiles();

        // for (File file : listOfFiles) {
        // if (file.isFile()) {
        // System.out.println(file.getName());
        // }
        // }

        return result;
    }

    public Corpus readFromList(String[] fileNames) {
        return new Corpus();
    }

}
