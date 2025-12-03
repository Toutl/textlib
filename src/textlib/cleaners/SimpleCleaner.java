package textlib.cleaners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import textlib.languages.Language;
import textlib.models.Corpus;
import textlib.models.Document;

public class SimpleCleaner implements Cleaner {

    private boolean fromGutenberg = true;
    private boolean withWordRemoval = true;
    private Language language = Language.ENGLISH;
    private Set<String> stopWords = null;

    public SimpleCleaner() {
    }

    public SimpleCleaner(Language language, boolean withWordRemoval) {
        this.language = language;
        this.withWordRemoval = withWordRemoval;
    }

    public SimpleCleaner(Language language, boolean withWordRemoval, boolean fromGutenberg) {
        this(language, withWordRemoval);
        this.fromGutenberg = fromGutenberg;
    }

    public void cleanCorpus(Corpus corpus) {
        for (Document document : corpus.getDocuments()) {
            this.cleanDocument(document);
        }
    }

    public void cleanDocument(Document document) {
        String raw = document.getRaw();

        if (this.fromGutenberg)
            raw = this.removeGutenbergWrappers(raw);

        // first/basic clean
        String cleaned = raw
                .toLowerCase()
                .replaceAll("'", " ")
                .replaceAll("[^a-z]", " ");

        if (this.withWordRemoval)
            cleaned = this.removeCommonWords(cleaned);

        // general clean
        cleaned = cleaned
                .replaceAll("\\s+", " ")
                .trim();

        document.setCleaned(cleaned);
        document.setUniqueTerms(this.calculateUnique(cleaned));
    }

    private String removeGutenbergWrappers(String txt) {
        String beginningPattern = "(?s)^.*?\\*\\*\\* START OF THE PROJECT GUTENBERG EBOOK .*? \\*\\*\\*";
        String endingPattern = "(?s)\\*\\*\\* END OF THE PROJECT GUTENBERG EBOOK .*$";

        String replaced = txt
                .replaceAll(beginningPattern, "")
                .replaceAll(endingPattern, "");

        return replaced;
    }

    private String removeCommonWords(String txt) {
        this.loadStopWords();

        for (String word : this.stopWords) {
            String pattern = "\\b" + word + "\\b\\s*";
            txt = txt.replaceAll(pattern, " ");
        }

        return txt;
    }

    private void loadStopWords() {

        if (this.stopWords != null)
            return;

        Path stopWordsPath = Paths.get("./src/resources/stopwords/stopwords-" + this.language + ".txt");

        if (!Files.exists(stopWordsPath)) {
            System.err.println("Language stop-words does not exists: " + stopWordsPath.toAbsolutePath());
        }

        this.stopWords = new HashSet<>();

        try {
            List<String> lines = new ArrayList<>(Files.readAllLines(stopWordsPath));

            for (String line : lines) {
                stopWords.add(line);
            }

        } catch (IOException e) {
            e.getMessage();
        }

    }

    public void setLanguage(Language language) {
        this.language = language;

        if (this.stopWords == null)
            return;

        this.stopWords = null;
        this.loadStopWords();
    }

    public Set<String> calculateUnique(String text) {
        return new HashSet<>(Arrays.asList(text.split("\\s+")));
    }

}
