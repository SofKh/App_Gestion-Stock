package com.example.labo_2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Produit> produits;
    private ListView listView;
    private Spinner spinner;
    private ArrayList<String> list;
    private View headerRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Création de la liste de produits
        produits = new ArrayList<>();
        produits.add(new Produit(1, "Noix de cajou", "Fruits secs", 8.99, 50));
        produits.add(new Produit(2, "Abricots séchés", "Fruits secs", 4.99, 30));
        produits.add(new Produit(3, "Eau gazeuse", "Boissons", 1.99, 100));
        produits.add(new Produit(4, "Jus d'orange", "Boissons", 2.99, 80));
        produits.add(new Produit(5, "Steak de boeuf", "Viandes", 12.99, 20));
        produits.add(new Produit(6, "Filet de saumon", "Poissons", 14.99, 15));
        produits.add(new Produit(7, "Noix de pécan", "Fruits secs", 12.99, 40));
        produits.add(new Produit(8, "Soda au cola", "Boissons", 1.49, 120));
        produits.add(new Produit(9, "Côte de porc", "Viandes", 8.99, 25));
        produits.add(new Produit(10, "Thon en conserve", "Poissons", 3.49, 50));

        //Récupération des vues
        listView = findViewById(R.id.listview);
        spinner = findViewById(R.id.spinner);

        //Remplissage du Spinner avec les catégories
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Affichage par défaut de tous les produits dans le tableau
        afficherProduits(produits);

        //bouton effacer
        Button button = findViewById(R.id.button);
        list = new ArrayList<>();
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                effacer();
            }
        });
    }
    public void effacer() {
        list.clear();
        listView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list));
    }
    public void ajouterTitres() {
        headerRow = getLayoutInflater().inflate(R.layout.titres_produits, listView, false);
        TextView idLabel = headerRow.findViewById(R.id.titre_id);
        idLabel.setText("Id");
        TextView nomLabel = headerRow.findViewById(R.id.titre_nom);
        nomLabel.setText("Nom");
        TextView categorieLabel = headerRow.findViewById(R.id.titre_categorie);
        categorieLabel.setText("Catégorie");
        TextView prixLabel = headerRow.findViewById(R.id.titre_prix);
        prixLabel.setText("Prix");
        TextView stockLabel = headerRow.findViewById(R.id.titre_stock);
        stockLabel.setText("Stock");
        listView.addHeaderView(headerRow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lister:
                //Afficher tous les produits dans le tableau
                ajouterTitres();
                afficherProduits(produits);
                return true;
            case R.id.categorie:
                //Afficher les produits de la catégorie sélectionnée dans le Spinner
                String categorie = spinner.getSelectedItem().toString();
                List<Produit> produitsCategorie = new ArrayList<>();
                for (Produit produit : produits) {
                    if (produit.getCategorie().equals(categorie)) {
                        produitsCategorie.add(produit);
                    }
                }
                afficherProduits(produitsCategorie);
                return true;
            case R.id.total:
//Afficher le prix total de tout l'inventaire
                double total = 0;
                for (Produit produit : produits) {
                    total += produit.getPrix() * produit.getStock();
                }
                String message = "Le prix total de l'inventaire est de " + String.format("%.2f", total) + "€";
                afficherMessage(message);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Méthode pour afficher les produits dans la ListView avec 5 colonnes
    private void afficherProduits(List<Produit> produits) {
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_2, android.R.id.text1, produits));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produit produit = (Produit) parent.getItemAtPosition(position);
                String message = produit.getNom() + " - " + produit.getPrix() + "€ - " + produit.getStock() + " unité(s)";
                afficherMessage(message);
            }
        });
    }

    //Méthode pour afficher un message dans un Toast
    private void afficherMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}


