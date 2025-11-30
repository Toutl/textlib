package textlib.builders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import textlib.models.Corpus;
import textlib.models.Document;

public class TxtBuilder implements Builder {

    public Corpus readFromDirectory(String filesDirectory) {
        Path filesPath = Paths.get(filesDirectory);

        if (!Files.isDirectory(filesPath)) {
            System.err.println("Path is not a directory.");
            return null;
        }

        Corpus corpus = new Corpus();

        try (Stream<Path> allFiles = Files.walk(filesPath);) {

            allFiles
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".txt"))
                    .forEach(p -> {
                        try {
                            String content = String.join("\n", Files.readAllLines(p));
                            corpus.add(new Document(p.getFileName().toString(), content));
                        } catch (IOException e) {
                            System.err.println("Error reading file " + p + ": " + e.getMessage());
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error accessing directory: " + e.getMessage());
        }

        return corpus;
    }

}
