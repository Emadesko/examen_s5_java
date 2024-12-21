package com.emadesko.controllers;

import com.emadesko.core.factory.services.ArticleServiceFactory;
import com.emadesko.core.factory.services.ClientServiceFactory;
import com.emadesko.core.factory.services.DemandeServiceFactory;
import com.emadesko.core.factory.services.DetailDemandeServiceFactory;
import com.emadesko.core.services.impl.YamlServiceImpl;
import com.emadesko.entities.Article;
import com.emadesko.entities.Client;
import com.emadesko.entities.Demande;
import com.emadesko.entities.DetailDemande;
import com.emadesko.services.ArticleService;
import com.emadesko.services.ClientService;
import com.emadesko.services.DemandeService;
import com.emadesko.services.DetailDemandeService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class CommandeController {

    private ArticleService articleService = ArticleServiceFactory.getInstance(new YamlServiceImpl());
    private ClientService clientService = ClientServiceFactory.getInstance(new YamlServiceImpl());
    private DetailDemandeService detailDemandeService = DetailDemandeServiceFactory.getInstance(new YamlServiceImpl());
    private DemandeService demandeService = DemandeServiceFactory.getInstance(new YamlServiceImpl());
    private Client client;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtTelephone;

    @FXML
    private TextField txtAdresse;

    @FXML
    private ComboBox<Article> comboArticle;

    @FXML
    private TextField txtPrix;

    @FXML
    private TextField txtQuantite;

    @FXML
    private TableView<DetailDemande> tableArticles;

    @FXML
    private TableColumn<DetailDemande, Article> colArticle;

    @FXML
    private TableColumn<DetailDemande, Double> colPrix;

    @FXML
    private TableColumn<DetailDemande, Integer> colQuantite;

    @FXML
    private TableColumn<DetailDemande, Double> colMontant;

    @FXML
    private TableColumn<DetailDemande, Button> colAction;

    @FXML
    private Label lblTotal;

    @FXML
    private VBox commandeSection;

    private ObservableList<DetailDemande> articlesCommande;

    public void initialize() {

        if (clientService.getAll().isEmpty()) {
            clientService.create(new Client("BBW", "777669595", "Dakar | Point E | Villa001"));
            clientService.create(new Client("Emadesko", "778632264", "Dakar | Medina | Villa002"));
            for (int index = 3; index < 10; index++) {
                clientService.create(new Client("Client"+index, "77863224"+index, "Dakar | Medina | Villa00"+index));
            }   
        }

        if (articleService.getAll().isEmpty()) {
            articleService.create(new Article("Nourriture", "Spaghetti", 1000, 50));
            articleService.create(new Article("BoissoN", "Pepsi", 350, 23));
            for (int i = 3; i < 10; i++) {
                articleService.create(new Article("Reference"+i, "Libelle"+i, 150*i, i%2==0 ?12*i : 0));
            }
        }
        articlesCommande = FXCollections.observableArrayList();

        colArticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colQuantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        colMontant.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("button"));

        tableArticles.setItems(articlesCommande);

        comboArticle.setItems(FXCollections.observableArrayList(this.articleService.getAvailableArticles()));

        comboArticle.setCellFactory(param -> new ListCell<Article>() {
            @Override
            protected void updateItem(Article item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getLibelle());
                }
            }
        });

        comboArticle.setButtonCell(new ListCell<Article>() {
            @Override
            protected void updateItem(Article item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getLibelle() + " - " + item.getPrix() + " FCFA"); 
                }
            }
        });

        lblTotal.setText("0.0");

        commandeSection.setDisable(true);
    }

    @FXML
    private void rechercherClient(ActionEvent event) {
        String telephone = txtTelephone.getText();
        if (telephone.isEmpty()) {
            showAlert("Veuillez entrer un numéro de téléphone.");
            return;
        }

        this.client = clientService.getClientByTelephone(telephone);

        if (client != null) {
            txtNom.setText(client.getSurname());
            txtAdresse.setText(client.getAdresse());
            commandeSection.setDisable(false);
        } else {
            showAlert("Client introuvable. Veuillez vérifier le numéro de téléphone.");
            txtNom.clear();
            txtAdresse.clear();
            commandeSection.setDisable(true);
        }
    }

    @FXML
    private void ajouterArticle(ActionEvent event) {
        Article article = comboArticle.getValue();
        if (article == null || txtPrix.getText().isEmpty() || txtQuantite.getText().isEmpty()) {
            showAlert("Veuillez remplir tous les champs avant d'ajouter un article.");
            return;
        }

        try {
            double prix = Double.parseDouble(txtPrix.getText());
            int quantite = Integer.parseInt(txtQuantite.getText());
            if (quantite > article.getQteStock()) {
                showAlert("Il ne reste que "+article.getQteStock()+ " " + article.getLibelle() );
                return;
            }

            Button btnSupprimer = new Button("Supprimer");
            DetailDemande DetailDemande = new DetailDemande(quantite, prix, article, btnSupprimer);

            btnSupprimer.setOnAction(e -> supprimerArticle(DetailDemande));
            boolean ok =true;
            for (DetailDemande detail : articlesCommande) {
                if (detail.getArticle() == article) {
                    ok=false;
                    articlesCommande.set(articlesCommande.indexOf(detail),DetailDemande);
                }
            }
            if (ok) {
                articlesCommande.add(DetailDemande);
            }

            calculerTotal();

            txtPrix.clear();
            txtQuantite.clear();
            comboArticle.getSelectionModel().clearSelection();
        } catch (NumberFormatException e) {
            showAlert("Prix et Quantité doivent être des nombres valides.");
        }
    }

    @FXML
    private void validerCommande(ActionEvent event) {
        if (articlesCommande.isEmpty()) {
            showAlert("Veuillez ajouter au moins un article avant de valider la commande.");
            return;
        }
        Demande demande = new Demande(calculerTotal(), client);
        demandeService.create(demande);
        for (DetailDemande detailDemande : articlesCommande) {
            detailDemande.setDemande(demande);
            detailDemandeService.create(detailDemande);
        }
        showAlert("Commande validée avec succès !");
        articlesCommande.clear();
        calculerTotal();
    }

    private void supprimerArticle(DetailDemande article) {
        articlesCommande.remove(article);
        calculerTotal();
    }

    private double calculerTotal() {
        double total = articlesCommande.stream().mapToDouble(DetailDemande::getTotal).sum();
        lblTotal.setText(String.format("%.2f", total));
        return total;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
