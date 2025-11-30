package textlib.cleaners;

import textlib.models.Corpus;
import textlib.models.Document;

public class SimpleCleaner implements Cleaner {

    private boolean fromGutenberg = true;
    private boolean withWordRemoval = true;

    public SimpleCleaner() {
    }

    public SimpleCleaner(boolean fromGutenberg, boolean withWordRemoval) {
        this.fromGutenberg = fromGutenberg;
        this.withWordRemoval = withWordRemoval;
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
                .replaceAll("'", "")
                .replaceAll("[^a-z]", " ");

        if (this.withWordRemoval)
            cleaned = this.removeCommonWords(cleaned);

        // general clean
        cleaned = cleaned
                .replaceAll("\\s+", " ")
                .trim();

        document.setCleaned(cleaned);

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
        String[] commonWords = {
                "a", "an", "the", "and", "but", "or", "not",
                "is", "are", "was", "were", "isnt", "arent", "wasnt", "werent",
                "be", "been", "being",
                "i", "me", "my", "you", "your", "he", "him", "his", "she", "her", "it", "its", "they", "them", "their",
                "of", "to", "from", "in", "on", "at", "by", "with",
                "this", "that", "those", "these", "what", "when", "where", "why", "how"
        };

        for (String word : commonWords) {
            String pattern = "\\b" + word + "\\b\\s*";
            txt = txt.replaceAll(pattern, " ");
        }

        return txt;
    }

}
