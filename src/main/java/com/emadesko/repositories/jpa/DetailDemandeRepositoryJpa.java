package com.emadesko.repositories.jpa;

import com.emadesko.entities.DetailDemande;
import com.emadesko.repositories.DetailDemandeRepository;

public class DetailDemandeRepositoryJpa extends DetailMereRepositoryJpa<DetailDemande> implements DetailDemandeRepository{
    
    public DetailDemandeRepositoryJpa(){
        super(DetailDemande.class,"demande_id");
    }
}
