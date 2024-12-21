package com.emadesko.services;

import java.util.List;

import com.emadesko.entities.Demande;
import com.emadesko.entities.DetailDemande;
import com.emadesko.repositories.DetailDemandeRepository;

public class DetailDemandeService extends DetailMereService<DetailDemande>{
    
    private DetailDemandeRepository detailDemandeRepository;

    public DetailDemandeService(DetailDemandeRepository detailDemandeRepository) {
        super(detailDemandeRepository);
        this.detailDemandeRepository = detailDemandeRepository;
    }

    public List <DetailDemande> getDetailsByDemande(Demande demande){
        return this.detailDemandeRepository.getDetailMeresByDetteMere(demande);
    }
}
