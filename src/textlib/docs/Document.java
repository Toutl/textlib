package textlib.docs;

import java.util.Vector;

public class Document {

    protected String fileName;
    protected String raw;
    protected String cleaned = null;
    protected Vector<Integer> vectorized = null;

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

    public Vector<Integer> getVectorized() {
        return getVectorized();
    }

    // setters
    protected void setCleaned(String cleaned) {
        if (!isCleaned())
            this.cleaned = cleaned;
    }

    protected void setVectorized(Vector<Integer> vectorized) {
        if (!isVectorized())
            this.vectorized = vectorized;
    }

    // more
    public boolean isCleaned() {
        return this.cleaned == null;
    }

    public boolean isVectorized() {
        return this.vectorized == null;
    }

    @Override
    public String toString() {
        return String.format(
                "File: %s\n Cleaned: %s\n Vectorized: %s",
                this.fileName,
                isCleaned() ? "yes" : "no",
                isVectorized() ? "yes" : "no");
    }

}
