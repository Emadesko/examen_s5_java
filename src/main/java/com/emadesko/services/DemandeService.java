package com.emadesko.services;

import java.util.List;

import com.emadesko.entities.Client;
import com.emadesko.entities.Demande;
import com.emadesko.enums.Etat;
import com.emadesko.repositories.DemandeRepository;

public class DemandeService extends DetteMereService<Demande>{
    
    private DemandeRepository demandeRepository;

    public DemandeService(DemandeRepository demandeRepository) {
        super(demandeRepository);
        this.demandeRepository = demandeRepository;
    }
    
    public List <Demande> getDemandesByEtat(Etat etat){
        return this.demandeRepository.getDemandesByEtat(etat);
    }
    
    public List <Demande> getDemandesByEtatAndClient(Etat etat, Client client){
        return this.demandeRepository.getDemandesByEtatAndClient(etat,client);
    }

}
