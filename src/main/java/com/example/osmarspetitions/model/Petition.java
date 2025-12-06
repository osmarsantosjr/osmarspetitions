package com.example.osmarspetitions.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Petition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    @OneToMany(mappedBy = "petition", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Signature> signatures = new ArrayList<>();

    // Add signatures

    public void addSignature(Signature signature) {
        signatures.add(signature);
        signature.setPetition(this);
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Signature> getSignatures() { return signatures; }
    public void setSignatures(List<Signature> signatures) { this.signatures = signatures; }
}
