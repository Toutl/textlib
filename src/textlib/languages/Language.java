package textlib.languages;

public enum Language {
    ARABIC("ar"),
    CHINESE("zh"),
    ENGLISH("en"),
    FRENCH("fr"),
    GERMAN("de"),
    JAPANESE("ja"),
    RUSSIAN("ru"),
    SPANISH("es");

    private final String abbreviation;

    private Language(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String toString() {
        return this.abbreviation;
    }

}
