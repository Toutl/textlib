# textlib


**textlib** is a lightweight Java library designed for text processing and NLP tasks.
It handles the pipeline from raw text files to vectorized corpus models.


## Features

- **Corpus Building:** distinct text files into a structured `Corpus` object.
- **Text Cleaning:** Pipeline for sanitizing and normalizing text.
    - **Gutenberg Project Support:** specialized stripping of Project Gutenberg metadata.
- **Vectorization:** Converts text documents into vector representations.
<!-- - Similarity comparison. -->


## Requirements

- Java JDK 8 or higher


## To Run

Ensure the otuput directory exists, compile the source and execute:

```bash
# Create binary directory
mkdir -p bin

# Compile
javac -d bin src/textlib/**/*.java

# Run
java -cp bin textlib.main.Main
```


## Usage

Below is a standard workflow: **Read** → **Clean** → **Vectorize**

```java
import textlib.builders.TxtBuilder;
import textlib.cleaners.SimpleCleaner;
import textlib.models.Corpus;
import textlib.vectorizers.OneHotVectorizer;
import textlib.vectorizers.Vectorizer;

public class Example {
    public static void main(String[] args) {

        // 1. Define source directory
        String filesDirectory  "./files/sample";

        // 2. Build the corpus
        TxtBuilder builder  new TxtBuilder();
        Corpus myCorpus  builder.readFromDirectory(filesDirectory);

        // 3. Clean the text
        SimpleCleaner cleaner  new SimpleCleaner();
        cleaner.cleanCorpus(myCorpus);

        // 4. Vectorize (One-Hot Encoding)
        Vectorizer vectorizer  new OneHotVectorizer();
        vectorizer.vectorizeCorpus(myCorpus);

        // 5. Inspect results
        System.out.println("Corpus processed: " + myCorpus);
        System.out.println("First document: " + myCorpus.get(0));
    }
}
```

