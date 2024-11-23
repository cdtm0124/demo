package org.example;

public class Signature {
    private String name;
    private String email;

    public Signature(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
