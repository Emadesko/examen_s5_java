package com.emadesko.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class DetailMere extends Entite{
    protected static int nbrObjet;
    
    @Column(nullable = false)
    protected int qte;
    @Column(nullable = false)
    protected Double prix;
    @Column(nullable = false)
    protected Double total;
    @ManyToOne
    @JoinColumn(nullable = false)
    protected Article article;
    
    @Transient
    protected Button button;

    @Column(nullable = false)
    protected LocalDate createAt;
    @Column(nullable = false)
    protected LocalDate updateAt;

    public DetailMere(int qte, Double prix, Article article, Button button) {
        this.qte = qte;
        this.prix = prix;
        this.total = prix * qte;
        this.article = article;
        this.createAt = LocalDate.now();
        this.updateAt = LocalDate.now();
        this.button = button;
    }

    public DetailMere() {
    }
    
}
