package com.example.labo_2;

public class Produit {

    private int id;
    private String nom;
    private String categorie;
    private double prix;
    private int stock;

    Produit () {
        this.setId(0);
        this.setNom("");
        this.setCategorie("");
        this.setPrix(0.00);
        this.setStock(0);
    }

    public Produit(int id, String nom, String categorie, double prix, int stock) {
        this.setId(id);
        this.setNom(nom);
        this.setCategorie(categorie);
        this.setPrix(prix);
        this.setStock(stock);
    }

    Produit (Produit produit) {
        this.setId(produit.id);
        this.setNom(produit.nom);
        this.setCategorie(produit.categorie);
        this.setPrix(produit.prix);
        this.setStock(produit.stock);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return this.prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String toString() {
        return this.id + " | " + this.nom + " | " + this.categorie + " | " + this.prix + " | " + this.stock;
    }

}
