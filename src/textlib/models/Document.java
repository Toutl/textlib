package textlib.models;

import java.util.Map;

public class Document {

    protected String fileName;
    protected String raw;
    protected String cleaned = null;
    protected Map<String, Double> vectorized = null;

    public Document(String fileName, String raw) {
        this.fileName = fileName;
        this.raw = raw;
    }

    // getters
    public String getFileName() {
        return this.fileName;
    }

    public String getRaw() {
        return this.raw;
    }

    public String getCleaned() {
        return this.cleaned;
    }

    public Map<String, Double> getVectorized() {
        return this.vectorized;
    }

    // setters
    public void setCleaned(String cleaned) {
        if (!this.isCleaned())
            this.cleaned = cleaned;
    }

    public void setVectorized(Map<String, Double> vectorized) {
        if (!this.isVectorized())
            this.vectorized = vectorized;
    }

    // more
    public boolean isCleaned() {
        return this.cleaned != null;
    }

    public boolean isVectorized() {
        return this.vectorized != null;
    }

    @Override
    public String toString() {
        return String.format(
                "File: %s\n Cleaned: %s\n Vectorized: %s",
                this.fileName,
                this.isCleaned() ? "yes" : "no",
                this.isVectorized() ? "yes" : "no");
    }

}
