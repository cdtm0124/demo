package org.example;

import java.util.ArrayList;
import java.util.List;

public class Petition {
    private String title;
    private String description;
    private String author;
    private List<Signature> signatures = new ArrayList<>(); // Adding signatures

    public Petition(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public List<Signature> getSignatures() {
        return signatures;
    }

    // Add a signature
    public void addSignature(Signature signature) {
        signatures.add(signature);
    }
}
