package com.emadesko.repositories;

import java.util.List;

import com.emadesko.entities.Client;
import com.emadesko.entities.Demande;
import com.emadesko.enums.Etat;

public interface DemandeRepository extends DetteMereRepository<Demande>{
    List<Demande> getDemandesByEtat(Etat etat);
    List<Demande> getDemandesByEtatAndClient(Etat etat,Client client);
}